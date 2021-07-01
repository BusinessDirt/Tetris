package io.github.businessdirt.tetris.rendering;

import io.github.businessdirt.Main;
import io.github.businessdirt.tetris.GameClock;
import io.github.businessdirt.tetris.GameState;
import io.github.businessdirt.tetris.actions.KeyBinding;
import io.github.businessdirt.tetris.blocks.Block;
import io.github.businessdirt.tetris.blocks.BlockType;
import io.github.businessdirt.tetris.blocks.SingleBlock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Gui {
    private static JFrame jf;
    private static Draw draw;

    private static final int width = 1280;
    private static final int height = 780;

    private static int blockSize;
    private static int NULL = 69420;

    public Gui() {
        jf = new JFrame();
        jf.setSize(width,height);
        jf.setResizable(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setMinimumSize(new Dimension(width, height));
        jf.setTitle("Tetris");

        jf.add(KeyBinding.getKeyListener());
        registerKeyBinds();

        draw = new Draw();
        jf.setBounds(0, 0, width, height);
        draw.setVisible(true);
        jf.add(draw);

        jf.setVisible(true);
    }

    private void registerKeyBinds() {

        final int[][][] newMap = {new int[SingleBlock.map.length][SingleBlock.map[0].length]};

        new KeyBinding(KeyEvent.VK_ENTER, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (Main.getGameState()) {
                    case PAUSE:
                        Main.setGameState(GameState.INGAME);
                    case GAMEOVER:
                        Main.setGameState(GameState.INGAME);
                        break;
                    case TITLE:
                        Main.setGameState(GameState.START);
                        break;
                    case START:
                        Main.setGameState(GameState.INGAME);
                        break;
                }
            }
        });

        new KeyBinding(KeyEvent.VK_LEFT, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int lowestXValue = 10;
                for (SingleBlock block : Main.getFocusedBlocks()) {
                    if (block.getX() <= lowestXValue) lowestXValue = block.getX();
                }
                if (lowestXValue != 0 && Main.getGameState() == GameState.INGAME) {
                    boolean act = true;
                    List<Point> blockCoordinates = new ArrayList<>();
                    for (SingleBlock block : Main.getFocusedBlocks()) {
                        if (!blockCoordinates.contains(new Point(block.getX(), block.getY()))) blockCoordinates.add(new Point(block.getX(), block.getY()));
                    }

                    for (Point coordinate : blockCoordinates) {
                        //check if space to left is free
                        if (SingleBlock.map[(int) (coordinate.getX() - 1)][(int) coordinate.getY()] > 0) {
                            //check if block to the left belongs to the focused Block
                            if (!blockCoordinates.contains(new Point((int) coordinate.getX() - 1, (int) coordinate.getY()))) {
                                act = false;
                            }
                        }
                    }
                    if (act) {
                        for (SingleBlock block : Main.getFocusedBlocks()) {
                            SingleBlock.map[block.getX()][block.getY()] = 0;
                        }
                        newMap[0] = SingleBlock.map;
                        List<SingleBlock> oldCoordinates = Main.getFocusedBlocks();
                        for (SingleBlock block : Main.getFocusedBlocks()) {
                            newMap[0][block.getX() - 1][block.getY()] = block.getType().getIntValue();
                            block.setX(block.getX() - 1);
                        }
                        SingleBlock.map = newMap[0];
                    }
                }
            }
        });

        new KeyBinding(KeyEvent.VK_RIGHT, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int highestXValue = 0;
                for (SingleBlock block : Main.getFocusedBlocks()) {
                    if (block.getX() >= highestXValue) highestXValue = block.getX() + 1;
                }
                if (highestXValue != 10 && Main.getGameState() == GameState.INGAME) {
                    boolean act = true;
                    List<Point> blockCoordinates = new ArrayList<>();
                    for (SingleBlock block : Main.getFocusedBlocks()) {
                        if (!blockCoordinates.contains(new Point(block.getX(), block.getY()))) blockCoordinates.add(new Point(block.getX(), block.getY()));
                    }

                    for (Point coordinate : blockCoordinates) {
                        //check if space to the right is free
                        if (SingleBlock.map[(int) (coordinate.getX() + 1)][(int) coordinate.getY()] > 0) {
                            //check if block to the right belongs to the focused Block
                            if (!blockCoordinates.contains(new Point((int) coordinate.getX() + 1, (int) coordinate.getY()))) {
                                act = false;
                            }
                        }
                    }
                    if (act) {
                        for (SingleBlock block : Main.getFocusedBlocks()) {
                            SingleBlock.map[block.getX()][block.getY()] = 0;
                        }
                        newMap[0] = SingleBlock.map;
                        List<SingleBlock> oldCoordinates = Main.getFocusedBlocks();
                        for (SingleBlock block : Main.getFocusedBlocks()) {
                            newMap[0][block.getX() + 1][block.getY()] = block.getType().getIntValue();
                            block.setX(block.getX() + 1);
                        }
                        SingleBlock.map = newMap[0];
                    }
                }
            }
        });

        new KeyBinding(KeyEvent.VK_UP, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BlockType type = Main.getFocusedBlocks().size() == 0 ? null : Main.getFocusedBlocks().get(Main.getFocusedBlocks().size() - 1).getType();
                int[][] newMap = SingleBlock.map;
                Point rotationCoordinate = new Point();
                List<Point> coordinates = new ArrayList<>();
                for (SingleBlock block : Main.getFocusedBlocks()) {
                    if (block.isRotationCenter()) rotationCoordinate.setLocation(block.getX(), block.getY());
                }

                if (type != null && coordinates != null && Main.getGameState() == GameState.INGAME) {
                    for (SingleBlock block : Main.getFocusedBlocks()) {
                        SingleBlock.map[block.getX()][block.getY()] = 0;
                    }
                    switch (type) {
                        case I:
                            switch (Main.getBlockRotation()) {
                                case 0:
                                    if (rotationCoordinate.getX() > 0 && SingleBlock.map.length >= rotationCoordinate.getX() + 3) {
                                        if (SingleBlock.map[(int) rotationCoordinate.getX() - 1][(int) (rotationCoordinate.getY())] < 1 && SingleBlock.map[(int) rotationCoordinate.getX() + 1][(int) (rotationCoordinate.getY())] < 1 && SingleBlock.map[(int) rotationCoordinate.getX() + 2][(int) (rotationCoordinate.getY())] < 1) {
                                            Main.setBlockRotation(1);
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                if (!block.isRotationCenter()) {
                                                    if (block.getY() == rotationCoordinate.getY() - 1 && block.getX() == rotationCoordinate.getX()) {
                                                        block.setY((int) rotationCoordinate.getY());
                                                        block.setX((int) rotationCoordinate.getX() - 1);
                                                        newMap[(int) rotationCoordinate.getX() - 1][(int) rotationCoordinate.getY()] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() + 1 && block.getX() == rotationCoordinate.getX()) {
                                                        block.setY((int) rotationCoordinate.getY());
                                                        block.setX((int) rotationCoordinate.getX() + 1);
                                                        newMap[(int) rotationCoordinate.getX() + 1][(int) rotationCoordinate.getY()] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() + 2 && block.getX() == rotationCoordinate.getX()) {
                                                        block.setY((int) rotationCoordinate.getY());
                                                        block.setX((int) rotationCoordinate.getX() + 2);
                                                        newMap[(int) rotationCoordinate.getX() + 2][(int) rotationCoordinate.getY()] = type.getIntValue();
                                                    }
                                                } else {
                                                    newMap[block.getX()][block.getY()] = type.getIntValue();
                                                }
                                            }
                                        } else {
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                newMap[block.getX()][block.getY()] = type.getIntValue();
                                            }
                                        }
                                    } else {
                                        for (SingleBlock block : Main.getFocusedBlocks()) {
                                            newMap[block.getX()][block.getY()] = type.getIntValue();
                                        }
                                    }
                                    break;
                                case 1:
                                    if (rotationCoordinate.getY() - 1 > 0 && SingleBlock.map[0].length >= rotationCoordinate.getY() + 2) {
                                        if (SingleBlock.map[(int) rotationCoordinate.getX()][(int) (rotationCoordinate.getY() + 1)] < 1 && SingleBlock.map[(int) rotationCoordinate.getX()][(int) (rotationCoordinate.getY() - 1)] < 1 && SingleBlock.map[(int) rotationCoordinate.getX()][(int) (rotationCoordinate.getY() - 2)] < 1) {
                                            Main.setBlockRotation(2);
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                if (!block.isRotationCenter()) {
                                                    if (block.getY() == rotationCoordinate.getY() && block.getX() == rotationCoordinate.getX() - 1) {
                                                        block.setY((int) rotationCoordinate.getY() + 1);
                                                        block.setX((int) rotationCoordinate.getX());
                                                        newMap[(int) rotationCoordinate.getX()][(int) rotationCoordinate.getY() + 1] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() && block.getX() == rotationCoordinate.getX() + 1) {
                                                        block.setY((int) rotationCoordinate.getY() - 1);
                                                        block.setX((int) rotationCoordinate.getX());
                                                        newMap[(int) rotationCoordinate.getX()][(int) rotationCoordinate.getY() - 1] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() && block.getX() == rotationCoordinate.getX() + 2) {
                                                        block.setY((int) rotationCoordinate.getY() - 2);
                                                        block.setX((int) rotationCoordinate.getX());
                                                        newMap[(int) rotationCoordinate.getX()][(int) rotationCoordinate.getY() - 2] = type.getIntValue();
                                                    }
                                                } else {
                                                    newMap[block.getX()][block.getY()] = type.getIntValue();
                                                }
                                            }
                                        } else {
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                newMap[block.getX()][block.getY()] = type.getIntValue();
                                            }
                                        }
                                    } else {
                                        for (SingleBlock block : Main.getFocusedBlocks()) {
                                            newMap[block.getX()][block.getY()] = type.getIntValue();
                                        }
                                    }
                                    break;
                                case 2:
                                    if (rotationCoordinate.getX() - 1 > 0 && SingleBlock.map.length >= rotationCoordinate.getX() + 2) {
                                        if (SingleBlock.map[(int) rotationCoordinate.getX() + 1][(int) (rotationCoordinate.getY())] < 1 && SingleBlock.map[(int) rotationCoordinate.getX() - 1][(int) (rotationCoordinate.getY())] < 1 && SingleBlock.map[(int) rotationCoordinate.getX() - 2][(int) (rotationCoordinate.getY())] < 1) {
                                            Main.setBlockRotation(3);
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                if (!block.isRotationCenter()) {
                                                    if (block.getY() == rotationCoordinate.getY() + 1 && block.getX() == rotationCoordinate.getX()) {
                                                        block.setY((int) rotationCoordinate.getY());
                                                        block.setX((int) rotationCoordinate.getX() + 1);
                                                        newMap[(int) rotationCoordinate.getX() + 1][(int) rotationCoordinate.getY()] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() - 1 && block.getX() == rotationCoordinate.getX()) {
                                                        block.setY((int) rotationCoordinate.getY());
                                                        block.setX((int) rotationCoordinate.getX() - 1);
                                                        newMap[(int) rotationCoordinate.getX() - 1][(int) rotationCoordinate.getY()] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() - 2 && block.getX() == rotationCoordinate.getX()) {
                                                        block.setY((int) rotationCoordinate.getY());
                                                        block.setX((int) rotationCoordinate.getX() - 2);
                                                        newMap[(int) rotationCoordinate.getX() - 2][(int) rotationCoordinate.getY()] = type.getIntValue();
                                                    }
                                                } else {
                                                    newMap[block.getX()][block.getY()] = type.getIntValue();
                                                }
                                            }
                                        } else {
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                newMap[block.getX()][block.getY()] = type.getIntValue();
                                            }
                                        }
                                    } else {
                                        for (SingleBlock block : Main.getFocusedBlocks()) {
                                            newMap[block.getX()][block.getY()] = type.getIntValue();
                                        }
                                    }
                                    break;
                                case 3:
                                    if (rotationCoordinate.getY() > 0 && SingleBlock.map[0].length >= rotationCoordinate.getY() + 3) {
                                        if (SingleBlock.map[(int) rotationCoordinate.getX()][(int) (rotationCoordinate.getY() - 1)] < 1 && SingleBlock.map[(int) rotationCoordinate.getX()][(int) (rotationCoordinate.getY() + 1)] < 1 && SingleBlock.map[(int) rotationCoordinate.getX()][(int) (rotationCoordinate.getY() + 2)] < 1) {
                                            Main.setBlockRotation(0);
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                if (!block.isRotationCenter()) {
                                                    if (block.getY() == rotationCoordinate.getY() && block.getX() == rotationCoordinate.getX() + 1) {
                                                        block.setY((int) rotationCoordinate.getY() - 1);
                                                        block.setX((int) rotationCoordinate.getX());
                                                        newMap[(int) rotationCoordinate.getX()][(int) rotationCoordinate.getY() - 1] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() && block.getX() == rotationCoordinate.getX() - 1) {
                                                        block.setY((int) rotationCoordinate.getY() + 1);
                                                        block.setX((int) rotationCoordinate.getX());
                                                        newMap[(int) rotationCoordinate.getX()][(int) rotationCoordinate.getY() + 1] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() && block.getX() == rotationCoordinate.getX() - 2) {
                                                        block.setY((int) rotationCoordinate.getY() + 2);
                                                        block.setX((int) rotationCoordinate.getX());
                                                        newMap[(int) rotationCoordinate.getX()][(int) rotationCoordinate.getY() + 2] = type.getIntValue();
                                                    }
                                                } else {
                                                    newMap[block.getX()][block.getY()] = type.getIntValue();
                                                }
                                            }
                                        } else {
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                newMap[block.getX()][block.getY()] = type.getIntValue();
                                            }
                                        }
                                    } else {
                                        for (SingleBlock block : Main.getFocusedBlocks()) {
                                            newMap[block.getX()][block.getY()] = type.getIntValue();
                                        }
                                    }
                                    break;
                            }
                            break;
                        case S:
                            switch (Main.getBlockRotation()) {
                                case 0:
                                    if ((rotationCoordinate.getX() > 0 && SingleBlock.map.length >= rotationCoordinate.getX() + 2) &&
                                            (rotationCoordinate.getY() > 0 && SingleBlock.map[0].length >= rotationCoordinate.getY() + 1)) {
                                        if (SingleBlock.map[(int) rotationCoordinate.getX()][(int) (rotationCoordinate.getY() - 1)] < 1 && SingleBlock.map[(int) rotationCoordinate.getX() - 1][(int) (rotationCoordinate.getY() - 1)] < 1 && SingleBlock.map[(int) rotationCoordinate.getX() - 1][(int) (rotationCoordinate.getY())] < 1) {
                                            Main.setBlockRotation(1);
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                if (!block.isRotationCenter()) {
                                                    if (block.getY() == rotationCoordinate.getY() && block.getX() == rotationCoordinate.getX() - 1) {
                                                        block.setY((int) rotationCoordinate.getY() + 1);
                                                        block.setX((int) rotationCoordinate.getX());
                                                        newMap[(int) rotationCoordinate.getX()][(int) rotationCoordinate.getY() + 1] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() - 1 && block.getX() == rotationCoordinate.getX()) {
                                                        block.setY((int) rotationCoordinate.getY());
                                                        block.setX((int) rotationCoordinate.getX() - 1);
                                                        newMap[(int) rotationCoordinate.getX() - 1][(int) rotationCoordinate.getY()] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() - 1 && block.getX() == rotationCoordinate.getX() + 1) {
                                                        block.setY((int) rotationCoordinate.getY() - 1);
                                                        block.setX((int) rotationCoordinate.getX() - 1);
                                                        newMap[(int) rotationCoordinate.getX() - 1][(int) rotationCoordinate.getY() - 1] = type.getIntValue();
                                                    }
                                                } else {
                                                    newMap[block.getX()][block.getY()] = type.getIntValue();
                                                }
                                            }
                                        } else {
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                newMap[block.getX()][block.getY()] = type.getIntValue();
                                            }
                                        }
                                    } else {
                                        for (SingleBlock block : Main.getFocusedBlocks()) {
                                            newMap[block.getX()][block.getY()] = type.getIntValue();
                                        }
                                    }
                                    break;
                                case 1:
                                    if ((rotationCoordinate.getX() > 0 && SingleBlock.map.length >= rotationCoordinate.getX() + 2) &&
                                            (rotationCoordinate.getY() > 0 && SingleBlock.map[0].length >= rotationCoordinate.getY() + 2)) {
                                        if (SingleBlock.map[(int) rotationCoordinate.getX() - 1][(int) (rotationCoordinate.getY())] < 1 && SingleBlock.map[(int) rotationCoordinate.getX()][(int) (rotationCoordinate.getY() - 1)] < 1 && SingleBlock.map[(int) rotationCoordinate.getX() + 1][(int) (rotationCoordinate.getY() - 1)] < 1) {
                                            Main.setBlockRotation(0);
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                if (!block.isRotationCenter()) {
                                                    if (block.getY() == rotationCoordinate.getY() && block.getX() == rotationCoordinate.getX() - 1) {
                                                        block.setY((int) rotationCoordinate.getY() - 1);
                                                        block.setX((int) rotationCoordinate.getX());
                                                        newMap[(int) rotationCoordinate.getX()][(int) rotationCoordinate.getY() - 1] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() - 1 && block.getX() == rotationCoordinate.getX() - 1) {
                                                        block.setY((int) rotationCoordinate.getY() - 1);
                                                        block.setX((int) rotationCoordinate.getX() + 1);
                                                        newMap[(int) rotationCoordinate.getX() + 1][(int) rotationCoordinate.getY() - 1] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() + 1 && block.getX() == rotationCoordinate.getX()) {
                                                        block.setY((int) rotationCoordinate.getY());
                                                        block.setX((int) rotationCoordinate.getX() - 1);
                                                        newMap[(int) rotationCoordinate.getX() - 1][(int) rotationCoordinate.getY()] = type.getIntValue();
                                                    }
                                                } else {
                                                    newMap[block.getX()][block.getY()] = type.getIntValue();
                                                }
                                            }
                                        } else {
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                newMap[block.getX()][block.getY()] = type.getIntValue();
                                            }
                                        }
                                    } else {
                                        for (SingleBlock block : Main.getFocusedBlocks()) {
                                            newMap[block.getX()][block.getY()] = type.getIntValue();
                                        }
                                    }
                                    break;
                            }
                            break;
                        case Z:
                            switch (Main.getBlockRotation()) {
                                case 0:
                                    if ((rotationCoordinate.getX() > 0 && SingleBlock.map.length >= rotationCoordinate.getX() + 2) &&
                                            (rotationCoordinate.getY() > 0 && SingleBlock.map[0].length >= rotationCoordinate.getY() + 1)) {
                                        if (SingleBlock.map[(int) rotationCoordinate.getX()][(int) (rotationCoordinate.getY() + 1)] < 1 && SingleBlock.map[(int) rotationCoordinate.getX() + 1][(int) (rotationCoordinate.getY())] < 1 && SingleBlock.map[(int) rotationCoordinate.getX() + 1][(int) (rotationCoordinate.getY() - 1)] < 1) {
                                            Main.setBlockRotation(1);
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                if (!block.isRotationCenter()) {
                                                    if (block.getY() == rotationCoordinate.getY() && block.getX() == rotationCoordinate.getX() + 1) {
                                                        block.setY((int) rotationCoordinate.getY() + 1);
                                                        block.setX((int) rotationCoordinate.getX());
                                                        newMap[(int) rotationCoordinate.getX()][(int) rotationCoordinate.getY() + 1] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() - 1 && block.getX() == rotationCoordinate.getX()) {
                                                        block.setY((int) rotationCoordinate.getY());
                                                        block.setX((int) rotationCoordinate.getX() + 1);
                                                        newMap[(int) rotationCoordinate.getX() + 1][(int) rotationCoordinate.getY()] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() - 1 && block.getX() == rotationCoordinate.getX() - 1) {
                                                        block.setY((int) rotationCoordinate.getY() - 1);
                                                        block.setX((int) rotationCoordinate.getX() + 1);
                                                        newMap[(int) rotationCoordinate.getX() + 1][(int) rotationCoordinate.getY() - 1] = type.getIntValue();
                                                    }
                                                } else {
                                                    newMap[block.getX()][block.getY()] = type.getIntValue();
                                                }
                                            }
                                        } else {
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                newMap[block.getX()][block.getY()] = type.getIntValue();
                                            }
                                        }
                                    } else {
                                        for (SingleBlock block : Main.getFocusedBlocks()) {
                                            newMap[block.getX()][block.getY()] = type.getIntValue();
                                        }
                                    }
                                    break;
                                case 1:
                                    if ((rotationCoordinate.getX() > 0 && SingleBlock.map.length >= rotationCoordinate.getX() + 2) &&
                                            (rotationCoordinate.getY() > 0 && SingleBlock.map[0].length >= rotationCoordinate.getY() + 2)) {
                                        if (SingleBlock.map[(int) rotationCoordinate.getX() - 1][(int) (rotationCoordinate.getY() - 1)] < 1 && SingleBlock.map[(int) rotationCoordinate.getX()][(int) (rotationCoordinate.getY() - 1)] < 1 && SingleBlock.map[(int) rotationCoordinate.getX() + 1][(int) (rotationCoordinate.getY())] < 1) {
                                            Main.setBlockRotation(0);
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                if (!block.isRotationCenter()) {
                                                    if (block.getY() == rotationCoordinate.getY() && block.getX() == rotationCoordinate.getX() + 1) {
                                                        block.setY((int) rotationCoordinate.getY() - 1);
                                                        block.setX((int) rotationCoordinate.getX());
                                                        newMap[(int) rotationCoordinate.getX()][(int) rotationCoordinate.getY() - 1] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() - 1 && block.getX() == rotationCoordinate.getX() + 1) {
                                                        block.setY((int) rotationCoordinate.getY() - 1);
                                                        block.setX((int) rotationCoordinate.getX() - 1);
                                                        newMap[(int) rotationCoordinate.getX() - 1][(int) rotationCoordinate.getY() - 1] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() + 1 && block.getX() == rotationCoordinate.getX()) {
                                                        block.setY((int) rotationCoordinate.getY());
                                                        block.setX((int) rotationCoordinate.getX() + 1);
                                                        newMap[(int) rotationCoordinate.getX() + 1][(int) rotationCoordinate.getY()] = type.getIntValue();
                                                    }
                                                } else {
                                                    newMap[block.getX()][block.getY()] = type.getIntValue();
                                                }
                                            }
                                        } else {
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                newMap[block.getX()][block.getY()] = type.getIntValue();
                                            }
                                        }
                                    } else {
                                        for (SingleBlock block : Main.getFocusedBlocks()) {
                                            newMap[block.getX()][block.getY()] = type.getIntValue();
                                        }
                                    }
                                    break;
                            }
                            break;
                        case J:
                            switch (Main.getBlockRotation()) {
                                case 0:
                                    if ((rotationCoordinate.getX() > 0 && SingleBlock.map.length >= rotationCoordinate.getX() + 2) &&
                                            (rotationCoordinate.getY() > 0 && SingleBlock.map[0].length >= rotationCoordinate.getY() + 1)) {
                                        if (SingleBlock.map[(int) rotationCoordinate.getX() - 1][(int) (rotationCoordinate.getY())] < 1 && SingleBlock.map[(int) rotationCoordinate.getX() + 1][(int) (rotationCoordinate.getY())] < 1 && SingleBlock.map[(int) rotationCoordinate.getX() - 1][(int) (rotationCoordinate.getY() - 1)] < 1) {
                                            Main.setBlockRotation(1);
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                if (!block.isRotationCenter()) {
                                                    if (block.getY() == rotationCoordinate.getY() - 1 && block.getX() == rotationCoordinate.getX()) {
                                                        block.setY((int) rotationCoordinate.getY());
                                                        block.setX((int) rotationCoordinate.getX() + 1);
                                                        newMap[(int) rotationCoordinate.getX() + 1][(int) rotationCoordinate.getY()] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() + 1 && block.getX() == rotationCoordinate.getX()) {
                                                        block.setY((int) rotationCoordinate.getY());
                                                        block.setX((int) rotationCoordinate.getX() - 1);
                                                        newMap[(int) rotationCoordinate.getX() - 1][(int) rotationCoordinate.getY()] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() + 1 && block.getX() == rotationCoordinate.getX() - 1) {
                                                        block.setY((int) rotationCoordinate.getY() - 1);
                                                        block.setX((int) rotationCoordinate.getX() - 1);
                                                        newMap[(int) rotationCoordinate.getX() - 1][(int) rotationCoordinate.getY() - 1] = type.getIntValue();
                                                    }
                                                } else {
                                                    newMap[block.getX()][block.getY()] = type.getIntValue();
                                                }
                                            }
                                        } else {
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                newMap[block.getX()][block.getY()] = type.getIntValue();
                                            }
                                        }
                                    } else {
                                        for (SingleBlock block : Main.getFocusedBlocks()) {
                                            newMap[block.getX()][block.getY()] = type.getIntValue();
                                        }
                                    }
                                    break;
                                case 1:
                                    if ((rotationCoordinate.getX() > 0 && SingleBlock.map.length >= rotationCoordinate.getX() + 2) &&
                                            (rotationCoordinate.getY() > 0 && SingleBlock.map[0].length >= rotationCoordinate.getY() + 1)) {
                                        if (SingleBlock.map[(int) rotationCoordinate.getX()][(int) (rotationCoordinate.getY() + 1)] < 1 && SingleBlock.map[(int) rotationCoordinate.getX()][(int) (rotationCoordinate.getY() - 1)] < 1 && SingleBlock.map[(int) rotationCoordinate.getX() + 1][(int) (rotationCoordinate.getY() - 1)] < 1) {
                                            Main.setBlockRotation(2);
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                if (!block.isRotationCenter()) {
                                                    if (block.getY() == rotationCoordinate.getY() && block.getX() == rotationCoordinate.getX() + 1) {
                                                        block.setY((int) rotationCoordinate.getY() + 1);
                                                        block.setX((int) rotationCoordinate.getX());
                                                        newMap[(int) rotationCoordinate.getX()][(int) rotationCoordinate.getY() + 1] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() && block.getX() == rotationCoordinate.getX() - 1) {
                                                        block.setY((int) rotationCoordinate.getY() - 1);
                                                        block.setX((int) rotationCoordinate.getX());
                                                        newMap[(int) rotationCoordinate.getX()][(int) rotationCoordinate.getY() - 1] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() - 1 && block.getX() == rotationCoordinate.getX() - 1) {
                                                        block.setY((int) rotationCoordinate.getY() - 1);
                                                        block.setX((int) rotationCoordinate.getX() + 1);
                                                        newMap[(int) rotationCoordinate.getX() + 1][(int) rotationCoordinate.getY() - 1] = type.getIntValue();
                                                    }
                                                } else {
                                                    newMap[block.getX()][block.getY()] = type.getIntValue();
                                                }
                                            }
                                        } else {
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                newMap[block.getX()][block.getY()] = type.getIntValue();
                                            }
                                        }
                                    } else {
                                        for (SingleBlock block : Main.getFocusedBlocks()) {
                                            newMap[block.getX()][block.getY()] = type.getIntValue();
                                        }
                                    }
                                    break;
                                case 2:
                                    if ((rotationCoordinate.getX() > 0 && SingleBlock.map.length >= rotationCoordinate.getX() + 2) &&
                                            (rotationCoordinate.getY() > 0 && SingleBlock.map[0].length >= rotationCoordinate.getY() + 1)) {
                                        if (SingleBlock.map[(int) rotationCoordinate.getX() - 1][(int) (rotationCoordinate.getY())] < 1 && SingleBlock.map[(int) rotationCoordinate.getX() + 1][(int) (rotationCoordinate.getY())] < 1 && SingleBlock.map[(int) rotationCoordinate.getX() + 1][(int) (rotationCoordinate.getY() + 1)] < 1) {
                                            Main.setBlockRotation(3);
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                if (!block.isRotationCenter()) {
                                                    if (block.getY() == rotationCoordinate.getY() - 1 && block.getX() == rotationCoordinate.getX()) {
                                                        block.setY((int) rotationCoordinate.getY());
                                                        block.setX((int) rotationCoordinate.getX() + 1);
                                                        newMap[(int) rotationCoordinate.getX() + 1][(int) rotationCoordinate.getY()] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() + 1 && block.getX() == rotationCoordinate.getX()) {
                                                        block.setY((int) rotationCoordinate.getY());
                                                        block.setX((int) rotationCoordinate.getX() - 1);
                                                        newMap[(int) rotationCoordinate.getX() - 1][(int) rotationCoordinate.getY()] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() - 1 && block.getX() == rotationCoordinate.getX() + 1) {
                                                        block.setY((int) rotationCoordinate.getY() + 1);
                                                        block.setX((int) rotationCoordinate.getX() + 1);
                                                        newMap[(int) rotationCoordinate.getX() + 1][(int) rotationCoordinate.getY() + 1] = type.getIntValue();
                                                    }
                                                } else {
                                                    newMap[block.getX()][block.getY()] = type.getIntValue();
                                                }
                                            }
                                        } else {
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                newMap[block.getX()][block.getY()] = type.getIntValue();
                                            }
                                        }
                                    } else {
                                        for (SingleBlock block : Main.getFocusedBlocks()) {
                                            newMap[block.getX()][block.getY()] = type.getIntValue();
                                        }
                                    }
                                    break;
                                case 3:
                                    if ((rotationCoordinate.getX() > 0 && SingleBlock.map.length >= rotationCoordinate.getX() + 2) &&
                                            (rotationCoordinate.getY() > 0 && SingleBlock.map[0].length >= rotationCoordinate.getY() + 1)) {
                                        if (SingleBlock.map[(int) rotationCoordinate.getX()][(int) (rotationCoordinate.getY() + 1)] < 1 && SingleBlock.map[(int) rotationCoordinate.getX()][(int) (rotationCoordinate.getY() - 1)] < 1 && SingleBlock.map[(int) rotationCoordinate.getX() - 1][(int) (rotationCoordinate.getY() + 1)] < 1) {
                                            Main.setBlockRotation(0);
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                if (!block.isRotationCenter()) {
                                                    if (block.getY() == rotationCoordinate.getY() && block.getX() == rotationCoordinate.getX() + 1) {
                                                        block.setY((int) rotationCoordinate.getY() + 1);
                                                        block.setX((int) rotationCoordinate.getX());
                                                        newMap[(int) rotationCoordinate.getX()][(int) rotationCoordinate.getY() + 1] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() && block.getX() == rotationCoordinate.getX() - 1) {
                                                        block.setY((int) rotationCoordinate.getY() - 1);
                                                        block.setX((int) rotationCoordinate.getX());
                                                        newMap[(int) rotationCoordinate.getX()][(int) rotationCoordinate.getY() - 1] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() + 1 && block.getX() == rotationCoordinate.getX() + 1) {
                                                        block.setY((int) rotationCoordinate.getY() + 1);
                                                        block.setX((int) rotationCoordinate.getX() - 1);
                                                        newMap[(int) rotationCoordinate.getX() - 1][(int) rotationCoordinate.getY() + 1] = type.getIntValue();
                                                    }
                                                } else {
                                                    newMap[block.getX()][block.getY()] = type.getIntValue();
                                                }
                                            }
                                        } else {
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                newMap[block.getX()][block.getY()] = type.getIntValue();
                                            }
                                        }
                                    } else {
                                        for (SingleBlock block : Main.getFocusedBlocks()) {
                                            newMap[block.getX()][block.getY()] = type.getIntValue();
                                        }
                                    }
                                    break;
                            }
                            break;
                        case L:
                            switch (Main.getBlockRotation()) {
                                case 0:
                                    if ((rotationCoordinate.getX() > 0 && SingleBlock.map.length >= rotationCoordinate.getX() + 2) &&
                                            (rotationCoordinate.getY() > 0 && SingleBlock.map[0].length >= rotationCoordinate.getY() + 1)) {
                                        if (SingleBlock.map[(int) rotationCoordinate.getX() - 1][(int) (rotationCoordinate.getY())] < 1 && SingleBlock.map[(int) rotationCoordinate.getX() + 1][(int) (rotationCoordinate.getY())] < 1 && SingleBlock.map[(int) rotationCoordinate.getX() + 1][(int) (rotationCoordinate.getY() - 1)] < 1) {
                                            Main.setBlockRotation(1);
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                if (!block.isRotationCenter()) {
                                                    if (block.getY() == rotationCoordinate.getY() - 1 && block.getX() == rotationCoordinate.getX()) {
                                                        block.setY((int) rotationCoordinate.getY());
                                                        block.setX((int) rotationCoordinate.getX() + 1);
                                                        newMap[(int) rotationCoordinate.getX() + 1][(int) rotationCoordinate.getY()] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() + 1 && block.getX() == rotationCoordinate.getX()) {
                                                        block.setY((int) rotationCoordinate.getY());
                                                        block.setX((int) rotationCoordinate.getX() - 1);
                                                        newMap[(int) rotationCoordinate.getX() - 1][(int) rotationCoordinate.getY()] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() + 1 && block.getX() == rotationCoordinate.getX() + 1) {
                                                        block.setY((int) rotationCoordinate.getY() - 1);
                                                        block.setX((int) rotationCoordinate.getX() + 1);
                                                        newMap[(int) rotationCoordinate.getX() + 1][(int) rotationCoordinate.getY() - 1] = type.getIntValue();
                                                    }
                                                } else {
                                                    newMap[block.getX()][block.getY()] = type.getIntValue();
                                                }
                                            }
                                        } else {
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                newMap[block.getX()][block.getY()] = type.getIntValue();
                                            }
                                        }
                                    } else {
                                        for (SingleBlock block : Main.getFocusedBlocks()) {
                                            newMap[block.getX()][block.getY()] = type.getIntValue();
                                        }
                                    }
                                    break;
                                case 1:
                                    if ((rotationCoordinate.getX() > 0 && SingleBlock.map.length >= rotationCoordinate.getX() + 2) &&
                                            (rotationCoordinate.getY() > 0 && SingleBlock.map[0].length >= rotationCoordinate.getY() + 1)) {
                                        if (SingleBlock.map[(int) rotationCoordinate.getX()][(int) (rotationCoordinate.getY() + 1)] < 1 && SingleBlock.map[(int) rotationCoordinate.getX()][(int) (rotationCoordinate.getY() - 1)] < 1 && SingleBlock.map[(int) rotationCoordinate.getX() - 1][(int) (rotationCoordinate.getY() - 1)] < 1) {
                                            Main.setBlockRotation(2);
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                if (!block.isRotationCenter()) {
                                                    if (block.getY() == rotationCoordinate.getY() && block.getX() == rotationCoordinate.getX() + 1) {
                                                        block.setY((int) rotationCoordinate.getY() + 1);
                                                        block.setX((int) rotationCoordinate.getX());
                                                        newMap[(int) rotationCoordinate.getX()][(int) rotationCoordinate.getY() + 1] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() && block.getX() == rotationCoordinate.getX() - 1) {
                                                        block.setY((int) rotationCoordinate.getY() - 1);
                                                        block.setX((int) rotationCoordinate.getX());
                                                        newMap[(int) rotationCoordinate.getX()][(int) rotationCoordinate.getY() - 1] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() - 1 && block.getX() == rotationCoordinate.getX() + 1) {
                                                        block.setY((int) rotationCoordinate.getY() - 1);
                                                        block.setX((int) rotationCoordinate.getX() - 1);
                                                        newMap[(int) rotationCoordinate.getX() - 1][(int) rotationCoordinate.getY() - 1] = type.getIntValue();
                                                    }
                                                } else {
                                                    newMap[block.getX()][block.getY()] = type.getIntValue();
                                                }
                                            }
                                        } else {
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                newMap[block.getX()][block.getY()] = type.getIntValue();
                                            }
                                        }
                                    } else {
                                        for (SingleBlock block : Main.getFocusedBlocks()) {
                                            newMap[block.getX()][block.getY()] = type.getIntValue();
                                        }
                                    }
                                    break;
                                case 2:
                                    if ((rotationCoordinate.getX() > 0 && SingleBlock.map.length >= rotationCoordinate.getX() + 2) &&
                                            (rotationCoordinate.getY() > 0 && SingleBlock.map[0].length >= rotationCoordinate.getY() + 1)) {
                                        if (SingleBlock.map[(int) rotationCoordinate.getX() - 1][(int) (rotationCoordinate.getY())] < 1 && SingleBlock.map[(int) rotationCoordinate.getX() + 1][(int) (rotationCoordinate.getY())] < 1 && SingleBlock.map[(int) rotationCoordinate.getX() - 1][(int) (rotationCoordinate.getY() + 1)] < 1) {
                                            Main.setBlockRotation(3);
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                if (!block.isRotationCenter()) {
                                                    if (block.getY() == rotationCoordinate.getY() - 1 && block.getX() == rotationCoordinate.getX()) {
                                                        block.setY((int) rotationCoordinate.getY());
                                                        block.setX((int) rotationCoordinate.getX() + 1);
                                                        newMap[(int) rotationCoordinate.getX() + 1][(int) rotationCoordinate.getY()] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() + 1 && block.getX() == rotationCoordinate.getX()) {
                                                        block.setY((int) rotationCoordinate.getY());
                                                        block.setX((int) rotationCoordinate.getX() - 1);
                                                        newMap[(int) rotationCoordinate.getX() - 1][(int) rotationCoordinate.getY()] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() - 1 && block.getX() == rotationCoordinate.getX() - 1) {
                                                        block.setY((int) rotationCoordinate.getY() + 1);
                                                        block.setX((int) rotationCoordinate.getX() - 1);
                                                        newMap[(int) rotationCoordinate.getX() - 1][(int) rotationCoordinate.getY() + 1] = type.getIntValue();
                                                    }
                                                } else {
                                                    newMap[block.getX()][block.getY()] = type.getIntValue();
                                                }
                                            }
                                        } else {
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                newMap[block.getX()][block.getY()] = type.getIntValue();
                                            }
                                        }
                                    } else {
                                        for (SingleBlock block : Main.getFocusedBlocks()) {
                                            newMap[block.getX()][block.getY()] = type.getIntValue();
                                        }
                                    }
                                    break;
                                case 3:
                                    if ((rotationCoordinate.getX() > 0 && SingleBlock.map.length >= rotationCoordinate.getX() + 2) &&
                                            (rotationCoordinate.getY() > 0 && SingleBlock.map[0].length >= rotationCoordinate.getY() + 1)) {
                                        if (SingleBlock.map[(int) rotationCoordinate.getX()][(int) (rotationCoordinate.getY() + 1)] < 1 && SingleBlock.map[(int) rotationCoordinate.getX()][(int) (rotationCoordinate.getY() - 1)] < 1 && SingleBlock.map[(int) rotationCoordinate.getX() + 1][(int) (rotationCoordinate.getY() + 1)] < 1) {
                                            Main.setBlockRotation(0);
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                if (!block.isRotationCenter()) {
                                                    if (block.getY() == rotationCoordinate.getY() && block.getX() == rotationCoordinate.getX() + 1) {
                                                        block.setY((int) rotationCoordinate.getY() + 1);
                                                        block.setX((int) rotationCoordinate.getX());
                                                        newMap[(int) rotationCoordinate.getX()][(int) rotationCoordinate.getY() + 1] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() && block.getX() == rotationCoordinate.getX() - 1) {
                                                        block.setY((int) rotationCoordinate.getY() - 1);
                                                        block.setX((int) rotationCoordinate.getX());
                                                        newMap[(int) rotationCoordinate.getX()][(int) rotationCoordinate.getY() - 1] = type.getIntValue();
                                                    }
                                                    if (block.getY() == rotationCoordinate.getY() + 1 && block.getX() == rotationCoordinate.getX() - 1) {
                                                        block.setY((int) rotationCoordinate.getY() + 1);
                                                        block.setX((int) rotationCoordinate.getX() + 1);
                                                        newMap[(int) rotationCoordinate.getX() + 1][(int) rotationCoordinate.getY() + 1] = type.getIntValue();
                                                    }
                                                } else {
                                                    newMap[block.getX()][block.getY()] = type.getIntValue();
                                                }
                                            }
                                        } else {
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                newMap[block.getX()][block.getY()] = type.getIntValue();
                                            }
                                        }
                                    } else {
                                        for (SingleBlock block : Main.getFocusedBlocks()) {
                                            newMap[block.getX()][block.getY()] = type.getIntValue();
                                        }
                                    }
                                    break;
                            }
                            break;
                        case T:
                            switch (Main.getBlockRotation()) {
                                case 0:
                                    if ((rotationCoordinate.getX() > 0 && SingleBlock.map.length >= rotationCoordinate.getX() + 2) &&
                                            (rotationCoordinate.getY() > 0 && SingleBlock.map[0].length >= rotationCoordinate.getY() + 1)) {
                                        if (SingleBlock.map[(int) rotationCoordinate.getX()][(int) (rotationCoordinate.getY() - 1)] < 1) {
                                            Main.setBlockRotation(1);
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                if (!block.isRotationCenter()) {
                                                    if (block.getY() == rotationCoordinate.getY() && block.getX() == rotationCoordinate.getX() + 1) {
                                                        block.setY((int) rotationCoordinate.getY() - 1);
                                                        block.setX((int) rotationCoordinate.getX());
                                                        newMap[(int) rotationCoordinate.getX()][(int) rotationCoordinate.getY() - 1] = type.getIntValue();
                                                    } else {
                                                        newMap[block.getX()][block.getY()] = type.getIntValue();
                                                    }
                                                } else {
                                                    newMap[block.getX()][block.getY()] = type.getIntValue();
                                                }
                                            }
                                        } else {
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                newMap[block.getX()][block.getY()] = type.getIntValue();
                                            }
                                        }
                                    } else {
                                        for (SingleBlock block : Main.getFocusedBlocks()) {
                                            newMap[block.getX()][block.getY()] = type.getIntValue();
                                        }
                                    }
                                    break;
                                case 1:
                                    if ((rotationCoordinate.getX() > 0 && SingleBlock.map.length >= rotationCoordinate.getX() + 2) &&
                                            (rotationCoordinate.getY() > 0 && SingleBlock.map[0].length >= rotationCoordinate.getY() + 1)) {
                                        if (SingleBlock.map[(int) rotationCoordinate.getX() + 1][(int) (rotationCoordinate.getY())] < 1) {
                                            Main.setBlockRotation(2);
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                if (!block.isRotationCenter()) {
                                                    if (block.getY() == rotationCoordinate.getY() + 1 && block.getX() == rotationCoordinate.getX()) {
                                                        block.setY((int) rotationCoordinate.getY());
                                                        block.setX((int) rotationCoordinate.getX() + 1);
                                                        newMap[(int) rotationCoordinate.getX() + 1][(int) rotationCoordinate.getY()] = type.getIntValue();
                                                    } else {
                                                        newMap[block.getX()][block.getY()] = type.getIntValue();
                                                    }
                                                } else {
                                                    newMap[block.getX()][block.getY()] = type.getIntValue();
                                                }
                                            }
                                        } else {
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                newMap[block.getX()][block.getY()] = type.getIntValue();
                                            }
                                        }
                                    } else {
                                        for (SingleBlock block : Main.getFocusedBlocks()) {
                                            newMap[block.getX()][block.getY()] = type.getIntValue();
                                        }
                                    }
                                    break;
                                case 2:
                                    if ((rotationCoordinate.getX() > 0 && SingleBlock.map.length >= rotationCoordinate.getX() + 2) &&
                                            (rotationCoordinate.getY() > 0 && SingleBlock.map[0].length >= rotationCoordinate.getY() + 1)) {
                                        if (SingleBlock.map[(int) rotationCoordinate.getX() - 1][(int) (rotationCoordinate.getY())] < 1) {
                                            Main.setBlockRotation(3);
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                if (!block.isRotationCenter()) {
                                                    if (block.getY() == rotationCoordinate.getY() && block.getX() == rotationCoordinate.getX() - 1) {
                                                        block.setY((int) rotationCoordinate.getY() + 1);
                                                        block.setX((int) rotationCoordinate.getX());
                                                        newMap[(int) rotationCoordinate.getX()][(int) rotationCoordinate.getY() + 1] = type.getIntValue();
                                                    } else {
                                                        newMap[block.getX()][block.getY()] = type.getIntValue();
                                                    }
                                                } else {
                                                    newMap[block.getX()][block.getY()] = type.getIntValue();
                                                }
                                            }
                                        } else {
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                newMap[block.getX()][block.getY()] = type.getIntValue();
                                            }
                                        }
                                    } else {
                                        for (SingleBlock block : Main.getFocusedBlocks()) {
                                            newMap[block.getX()][block.getY()] = type.getIntValue();
                                        }
                                    }
                                    break;
                                case 3:
                                    if ((rotationCoordinate.getX() > 0 && SingleBlock.map.length >= rotationCoordinate.getX() + 2) &&
                                            (rotationCoordinate.getY() > 0 && SingleBlock.map[0].length >= rotationCoordinate.getY() + 1)) {
                                        if (SingleBlock.map[(int) rotationCoordinate.getX() + 1][(int) (rotationCoordinate.getY())] < 1) {
                                            Main.setBlockRotation(0);
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                if (!block.isRotationCenter()) {
                                                    if (block.getY() == rotationCoordinate.getY() - 1 && block.getX() == rotationCoordinate.getX()) {
                                                        block.setY((int) rotationCoordinate.getY());
                                                        block.setX((int) rotationCoordinate.getX() - 1);
                                                        newMap[(int) rotationCoordinate.getX() - 1][(int) rotationCoordinate.getY()] = type.getIntValue();
                                                    } else {
                                                        newMap[block.getX()][block.getY()] = type.getIntValue();
                                                    }
                                                } else {
                                                    newMap[block.getX()][block.getY()] = type.getIntValue();
                                                }
                                            }
                                        } else {
                                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                                newMap[block.getX()][block.getY()] = type.getIntValue();
                                            }
                                        }
                                    } else {
                                        for (SingleBlock block : Main.getFocusedBlocks()) {
                                            newMap[block.getX()][block.getY()] = type.getIntValue();
                                        }
                                    }
                                    break;
                            }
                            break;
                        default:
                            for (SingleBlock block : Main.getFocusedBlocks()) {
                                newMap[block.getX()][block.getY()] = type.getIntValue();
                            }
                            SingleBlock.map = newMap;
                            break;
                    }
                    SingleBlock.map = newMap;
                }
            }
        });

        new KeyBinding(KeyEvent.VK_DOWN, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[][] newMap = new int[SingleBlock.map.length][SingleBlock.map[0].length];
                if (!GameClock.checkForBottom(Main.getFocusedBlocks()) && !GameClock.checkForBlock(Main.getFocusedBlocks(), newMap, SingleBlock.map) && Main.getGameState() == GameState.INGAME) {
                    for (SingleBlock block : Main.getFocusedBlocks()) {
                        SingleBlock.map[block.getX()][block.getY()] = 0;
                    }
                    newMap = SingleBlock.map;
                    for (SingleBlock block : Main.getFocusedBlocks()) {
                        newMap[block.getX()][block.getY() + 1] = block.getType().getIntValue();
                        block.setY(block.getY() + 1);
                    }
                    SingleBlock.map = newMap;
                }
            }
        });

        new KeyBinding(KeyEvent.VK_ESCAPE, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (Main.getGameState()) {
                    case GAMEOVER:
                    case START:
                        Main.setGameState(GameState.TITLE);
                        break;
                    case PAUSE:
                        Main.setGameState(GameState.INGAME);
                        break;
                    case INGAME:
                        Main.setGameState(GameState.PAUSE);
                        break;
                }
            }
        });
    }

    public static JFrame getJf() {
        return jf;
    }
    public static Draw getDraw() {
        return draw;
    }

    public static int getWidth() {
        return jf.getWidth();
    }
    public static int getHeight() {
        return jf.getHeight();
    }
    public static int getBlockSize() {
        return (getHeight() - 50 - 49) / 20;
    }
}
