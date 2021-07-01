package io.github.businessdirt.tetris;

import io.github.businessdirt.Main;
import io.github.businessdirt.tetris.blocks.Block;
import io.github.businessdirt.tetris.blocks.BlockType;
import io.github.businessdirt.tetris.blocks.SingleBlock;
import io.github.businessdirt.tetris.rendering.Draw;

import java.awt.*;
import java.util.*;
import java.util.List;

public class GameClock extends Thread {

    private static int[][] newMap = new int[SingleBlock.map.length][SingleBlock.map[0].length];
    private static final int gameSpeed = 400;

    public void run() {
        while (true) {
            try {
                sleep(gameSpeed);
                Draw.setTitleScreenColorID(Draw.getTitleScreenColorID() + 1);
                if (Draw.getTitleScreenColorID() > 6) {
                    Draw.setTitleScreenColorID(0);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (Main.getGameState() == GameState.INGAME) {
                //move block down
                if (!checkForBottom(Main.getFocusedBlocks()) && !checkForBlock(Main.getFocusedBlocks(), newMap, SingleBlock.map)) {
                    for (SingleBlock block : Main.getFocusedBlocks()) {
                        SingleBlock.map[block.getX()][block.getY()] = 0;
                    }

                    newMap = SingleBlock.map;
                    for (SingleBlock block : Main.getFocusedBlocks()) {
                        newMap[block.getX()][block.getY() + 1] = block.getType().getIntValue();
                        block.setY(block.getY() + 1);
                    }
                    SingleBlock.map = newMap;
                } else {
                    checkRow(1);
                    for (SingleBlock block : Main.getFocusedBlocks()) {
                        Main.getAllBlocks().add(block);
                        if (block.isRotationCenter()) block.setRotationCenter(false);
                    }
                    Main.setCurrentBlock(new Block(Main.getNextBlock()));
                    Main.setNextBlock(BlockType.random());
                    Draw.setNewBlockMap(Main.getNextBlock());
                }
            }
        }
    }

    public static boolean checkForBottom(List<SingleBlock> blockList) {
        int lowestYValue = 0;
        for (SingleBlock block : blockList) {
            if (block.getY() >= lowestYValue) lowestYValue = block.getY() + 1;
        }
        if (lowestYValue == SingleBlock.map[0].length) {
            return true;
        }
        return false;
    }

    public static void removeRow(int row, int multiplier) {
        for (int i = 0; i < SingleBlock.map.length; i++) {
            SingleBlock.map[i][row] = 0;
        }

        for (int y = row; y > 1; y--) {
            for (int x = 0; x < SingleBlock.map.length; x++) {
                SingleBlock.map[x][y] = SingleBlock.map[x][y - 1];
            }

        }
        checkRow(multiplier + 1);
    }

    public static void checkRow(int multiplier) {
        int blocksInRow = 0;
        int scoreToAdd = 0;

        for (int y = SingleBlock.map[0].length - 1; y > 0; y--) {
            for (int x = 0; x < SingleBlock.map.length; x++) {

                if (SingleBlock.map[x][y] > 0) {
                    blocksInRow++;
                }
            }
            if (blocksInRow == 10) {
                switch (multiplier) {
                    case 1:
                        scoreToAdd += 40;
                        break;
                    case 2:
                        scoreToAdd += 60;
                        break;
                    case 3:
                        scoreToAdd += 200;
                        break;
                    case 4:
                        scoreToAdd += 900;
                        Config.tetrisCount += 1;
                        Main.getConfig().markDirty();
                        Main.getConfig().writeData();
                        break;
                    default:
                        break;
                }
                removeRow(y, multiplier);
                break;
            } else {
                blocksInRow = 0;
            }

        }

        Main.setScore(Main.getScore() + scoreToAdd);
        scoreToAdd = 0;

        if (Main.getScore() > Config.highScore) {
            Config.highScore = Main.getScore();
            Main.getConfig().markDirty();
            Main.getConfig().writeData();
        }
    }

    public static boolean checkForBlock(List<SingleBlock> blockList, int[][] newMap, int[][] oldMap) {
        List<Point> blockCoordinates = new ArrayList<>();
        for (SingleBlock block : blockList) {
            if (!blockCoordinates.contains(new Point(block.getX(), block.getY()))) blockCoordinates.add(new Point(block.getX(), block.getY()));
        }

        for (Point coordinate : blockCoordinates) {
            //check if space below is free
            if (oldMap[(int) (coordinate.getX())][(int) coordinate.getY() + 1] > 0) {
                //check if block below belongs to the focused Block
                if (!blockCoordinates.contains(new Point((int) coordinate.getX(), (int) coordinate.getY() + 1))) {
                    return true;
                }
            }
        }

        return false;
    }
}
