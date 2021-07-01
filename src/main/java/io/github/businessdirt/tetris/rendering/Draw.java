package io.github.businessdirt.tetris.rendering;

import io.github.businessdirt.Main;
import io.github.businessdirt.tetris.Config;
import io.github.businessdirt.tetris.GameState;
import io.github.businessdirt.tetris.blocks.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Draw extends JLabel {

    private static final long serialVersionUID = 1L;
    private static int gameXStart, gameYStart;
    private static int[][] newBlockMap;
    private static Font font;
    private static int titleScreenColorID = 0;

    static {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT , new File("res/font/8-bit.ttf"));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        gameXStart = getWidth() / 2 - 5 * Gui.getBlockSize();
        gameYStart = getHeight() / 2 - 10 * Gui.getBlockSize();

        //draw///
        g.setColor(new Color(52, 73, 94, 255));
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.white);
        g.drawRect(Draw.getGameXStart() - 1, Draw.getGameYStart() - 1, Gui.getBlockSize() * 10 + 1, Gui.getBlockSize() * 20 +1);
        g.drawRect(Draw.getGameXStart() + Gui.getBlockSize() * 12 - 1, Draw.getGameYStart() - 1, Gui.getBlockSize() * 4 + 1, Gui.getBlockSize() * 4 +1);

        for (int x = 0; x < newBlockMap.length; x++) {
            for (int y = 0; y < newBlockMap[x].length; y++) {
                if (newBlockMap[y][x] == 0) {
                    g.setColor(new Color(35, 39, 42));
                    g.fillRect(Draw.getGameXStart() + Gui.getBlockSize() * 12 + x * Gui.getBlockSize(), getGameYStart() + y * Gui.getBlockSize(), Gui.getBlockSize(), Gui.getBlockSize());
                } else if (newBlockMap[y][x] == 1) {
                    g.drawImage(DyeImage.dye(SingleBlock.getTexture(), new Color(88, 242, 242, 128)).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), Draw.getGameXStart() + Gui.getBlockSize() * 12 + x * Gui.getBlockSize(), getGameYStart() + y * Gui.getBlockSize(), null);
                } else if (newBlockMap[y][x] == 2) {
                    g.drawImage(DyeImage.dye(SingleBlock.getTexture(), new Color(254, 231, 92, 128)).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), Draw.getGameXStart() + Gui.getBlockSize() * 12 + x * Gui.getBlockSize(), getGameYStart() + y * Gui.getBlockSize(), null);
                } else if (newBlockMap[y][x] == 3) {
                    g.drawImage(DyeImage.dye(SingleBlock.getTexture(), new Color(87, 242, 135, 128)).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), Draw.getGameXStart() + Gui.getBlockSize() * 12 + x * Gui.getBlockSize(), getGameYStart() + y * Gui.getBlockSize(), null);
                } else if (newBlockMap[y][x] == 4) {
                    g.drawImage(DyeImage.dye(SingleBlock.getTexture(), new Color(237, 66, 69, 128)).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), Draw.getGameXStart() + Gui.getBlockSize() * 12 + x * Gui.getBlockSize(), getGameYStart() + y * Gui.getBlockSize(), null);
                } else if (newBlockMap[y][x] == 5) {
                    g.drawImage(DyeImage.dye(SingleBlock.getTexture(), new Color(88, 101, 242, 128)).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), Draw.getGameXStart() + Gui.getBlockSize() * 12 + x * Gui.getBlockSize(), getGameYStart() + y * Gui.getBlockSize(), null);
                } else if (newBlockMap[y][x] == 6) {
                    g.drawImage(DyeImage.dye(SingleBlock.getTexture(), new Color(237, 121, 35, 128)).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), Draw.getGameXStart() + Gui.getBlockSize() * 12 + x * Gui.getBlockSize(), getGameYStart() + y * Gui.getBlockSize(), null);
                } else if (newBlockMap[y][x] == 7) {
                    g.drawImage(DyeImage.dye(SingleBlock.getTexture(), new Color(235, 69, 158, 128)).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), Draw.getGameXStart() + Gui.getBlockSize() * 12 + x * Gui.getBlockSize(), getGameYStart() + y * Gui.getBlockSize(), null);
                }
            }
        }

        for (int x = 0; x < SingleBlock.map.length; x++) {
            for (int y = 0; y < SingleBlock.map[0].length; y++) {
                if (SingleBlock.map[x][y] == 0) {
                    g.setColor(new Color(35, 39, 42));
                    g.fillRect(getGameXStart() + x * Gui.getBlockSize(), getGameYStart() + y * Gui.getBlockSize(), Gui.getBlockSize(), Gui.getBlockSize());
                } else if (SingleBlock.map[x][y] == 1) {
                    g.drawImage(DyeImage.dye(SingleBlock.getTexture(), new Color(88, 242, 242, 128)).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), getGameXStart() + x * Gui.getBlockSize(), getGameYStart() + y * Gui.getBlockSize(), null);
                } else if (SingleBlock.map[x][y] == 2) {
                    g.drawImage(DyeImage.dye(SingleBlock.getTexture(), new Color(254, 231, 92, 128)).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), getGameXStart() + x * Gui.getBlockSize(), getGameYStart() + y * Gui.getBlockSize(), null);
                } else if (SingleBlock.map[x][y] == 3) {
                    g.drawImage(DyeImage.dye(SingleBlock.getTexture(), new Color(87, 242, 135, 128)).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), getGameXStart() + x * Gui.getBlockSize(), getGameYStart() + y * Gui.getBlockSize(), null);
                } else if (SingleBlock.map[x][y] == 4) {
                    g.drawImage(DyeImage.dye(SingleBlock.getTexture(), new Color(237, 66, 69, 128)).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), getGameXStart() + x * Gui.getBlockSize(), getGameYStart() + y * Gui.getBlockSize(), null);
                } else if (SingleBlock.map[x][y] == 5) {
                    g.drawImage(DyeImage.dye(SingleBlock.getTexture(), new Color(88, 101, 242, 128)).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), getGameXStart() + x * Gui.getBlockSize(), getGameYStart() + y * Gui.getBlockSize(), null);
                } else if (SingleBlock.map[x][y] == 6) {
                    g.drawImage(DyeImage.dye(SingleBlock.getTexture(), new Color(237, 121, 35, 128)).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), getGameXStart() + x * Gui.getBlockSize(), getGameYStart() + y * Gui.getBlockSize(), null);
                } else if (SingleBlock.map[x][y] == 7) {
                    g.drawImage(DyeImage.dye(SingleBlock.getTexture(), new Color(235, 69, 158, 128)).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), getGameXStart() + x * Gui.getBlockSize(), getGameYStart() + y * Gui.getBlockSize(), null);
                }
            }
        }

        switch (Main.getGameState()) {
            case GAMEOVER:
                displayScreen(g, "Game Over!", Gui.getWidth() / 2, Gui.getHeight() / 2, Color.WHITE, new Color(240, 71, 71, 248), get8BitFont());
                break;
            case START:
                displayScreen(g, "Press ENTER to start!", Gui.getWidth() / 2, Gui.getHeight() / 2, Color.WHITE, new Color(67, 181, 129, 248), get8BitFont());
                break;
            case PAUSE:
                displayScreen(g, "Pause", Gui.getWidth() / 2, Gui.getHeight() / 2, Color.WHITE, new Color(250, 166, 26, 248), get8BitFont());
                break;
            case INGAME:
                break;
            case TITLE:
                g.setColor(new Color(35, 39, 42));
                g.fillRect(0, 0, Gui.getWidth(), Gui.getHeight());
                tetrisTitle(g, SingleBlock.getTexture());
                break;
        }

        if (Main.getGameState() == GameState.INGAME) {
            g.setColor(Color.WHITE);
            g.setFont(font.deriveFont((float) Gui.getHeight() / 50));
            g.drawString("Score: " + Main.getScore(), 25, 35);
            g.drawString("Highscore: " + Config.highScore, 25, 35 + 15 + Gui.getHeight() / 50);
            g.drawString("Tetris: " + Config.tetrisCount, 25, 35 + 15 * 2 + Gui.getHeight() / 50 * 2);
        }

        repaint();
    }

    public static int getGameXStart() {
        return gameXStart;
    }
    public static int getGameYStart() {
        return gameYStart;
    }

    public static int[][] getNewBlockMap() {
        return newBlockMap;
    }
    public static void setNewBlockMap(BlockType type) {
        switch (type) {
            case I:
                newBlockMap = new int[][] {
                        {0, 0, 0, 0},
                        {1, 1, 1, 1},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0}
                };
                break;
            case O:
                newBlockMap = new int[][] {
                        {0, 0, 0, 0},
                        {0, 2, 2, 0},
                        {0, 2, 2, 0},
                        {0, 0, 0, 0}
                };
                break;
            case S:
                newBlockMap = new int[][] {
                        {0, 0, 0, 0},
                        {0, 3, 3, 0},
                        {3, 3, 0, 0},
                        {0, 0, 0, 0}
                };
                break;
            case Z:
                newBlockMap = new int[][] {
                        {0, 0, 0, 0},
                        {0, 4, 4, 0},
                        {0, 0, 4, 4},
                        {0, 0, 0, 0}
                };
                break;
            case J:
                newBlockMap = new int[][] {
                        {0, 0, 0, 0},
                        {0, 0, 5, 0},
                        {0, 0, 5, 0},
                        {0, 5, 5, 0}
                };
                break;
            case L:
                newBlockMap = new int[][] {
                        {0, 0, 0, 0},
                        {0, 6, 0, 0},
                        {0, 6, 0, 0},
                        {0, 6, 6, 0}
                };
                break;
            case T:
                newBlockMap = new int[][] {
                        {0, 0, 0, 0},
                        {7, 7, 7, 0},
                        {0, 7, 0, 0},
                        {0, 0, 0, 0}
                };
                break;
        }
    }

    public static Font get8BitFont() {
        if (font != null) {
            return font;
        } else {
            return new Font("Arial", Font.PLAIN, 16);
        }
    }

    public static int getTitleScreenColorID() {
        return titleScreenColorID;
    }
    public static void setTitleScreenColorID(int titleScreenColorID) {
        Draw.titleScreenColorID = titleScreenColorID;
    }

    private static void displayScreen(Graphics g, String title, int width, int height, Color stringColor, Color bgColor, Font font) {
        font = font.deriveFont((float) Gui.getHeight() / 50);

        g.setColor(bgColor);
        g.fillRect(Gui.getWidth() / 2 - width / 2, Gui.getHeight() / 2 - height / 2, width, height);

        g.setColor(stringColor);
        g.setFont(font);
        g.drawString(title, Gui.getWidth() / 2 - g.getFontMetrics(font).stringWidth(title) / 2, Gui.getHeight() / 2 - font.getSize() / 2);
    }

    private static void tetrisTitle(Graphics g, BufferedImage blockImage) {
        int xStart = Gui.getWidth() / 2 - Gui.getBlockSize() * 21 / 2;
        int yStart = Gui.getHeight() / 2 - Gui.getBlockSize() * 21 / 2;
        int id = getTitleScreenColorID();
        
        Color[] colors = new Color[] {
                new Color(88, 242, 242, 128),
                new Color(254, 231, 92, 128),
                new Color(87, 242, 135, 128),
                new Color(237, 66, 69, 128),
                new Color(88, 101, 242, 128),
                new Color(237, 121, 35, 128),
                new Color(235, 69, 158, 128)
        };

        //T
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart, yStart, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize(), yStart, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize() * 2, yStart, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize(), yStart + Gui.getBlockSize(), null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize(), yStart + Gui.getBlockSize() * 2, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize(), yStart + Gui.getBlockSize() * 3, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize(), yStart + Gui.getBlockSize() * 4, null);

        //E
        xStart = xStart + Gui.getBlockSize() * 4;
        id++;
        if (id > 6) id = 0;
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart, yStart, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize(), yStart, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize() * 2, yStart, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart, yStart + Gui.getBlockSize(), null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart, yStart + Gui.getBlockSize() * 2, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize(), yStart + Gui.getBlockSize() * 2, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize() * 2, yStart + Gui.getBlockSize() * 2, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart, yStart + Gui.getBlockSize() * 3, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize(), yStart + Gui.getBlockSize() * 4, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize() * 2, yStart + Gui.getBlockSize() * 4, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart, yStart + Gui.getBlockSize() * 4, null);

        //T
        xStart = xStart + Gui.getBlockSize() * 4;
        id++;
        if (id > 6) id = 0;
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart, yStart, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize(), yStart, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize() * 2, yStart, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize(), yStart + Gui.getBlockSize(), null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize(), yStart + Gui.getBlockSize() * 2, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize(), yStart + Gui.getBlockSize() * 3, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize(), yStart + Gui.getBlockSize() * 4, null);

        //R
        xStart = xStart + Gui.getBlockSize() * 4;
        id++;
        if (id > 6) id = 0;
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart, yStart, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize(), yStart, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart, yStart + Gui.getBlockSize(), null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize() * 2, yStart + Gui.getBlockSize(), null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart, yStart + Gui.getBlockSize() * 2, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize(), yStart + Gui.getBlockSize() * 2, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart, yStart + Gui.getBlockSize() * 3, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize() * 2, yStart + Gui.getBlockSize() * 3, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart, yStart + Gui.getBlockSize() * 4, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize() * 2, yStart + Gui.getBlockSize() * 4, null);

        //I
        xStart = xStart + Gui.getBlockSize() * 3;
        id++;
        if (id > 6) id = 0;
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize(), yStart, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize(), yStart + Gui.getBlockSize(), null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize(), yStart + Gui.getBlockSize() * 2, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize(), yStart + Gui.getBlockSize() * 3, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize(), yStart + Gui.getBlockSize() * 4, null);

        //S
        xStart = xStart + Gui.getBlockSize() * 3;
        id++;
        if (id > 6) id = 0;
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart, yStart, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize(), yStart, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize() * 2, yStart, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart, yStart + Gui.getBlockSize(), null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart, yStart + Gui.getBlockSize() * 2, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize(), yStart + Gui.getBlockSize() * 2, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize() * 2, yStart + Gui.getBlockSize() * 2, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize() * 2, yStart + Gui.getBlockSize() * 3, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize() * 2, yStart + Gui.getBlockSize() * 4, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart + Gui.getBlockSize(), yStart + Gui.getBlockSize() * 4, null);
        g.drawImage(DyeImage.dye(blockImage, colors[id]).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), xStart, yStart + Gui.getBlockSize() * 4, null);

        Font tFont = font.deriveFont((float) Gui.getHeight() / 35);
        g.setFont(tFont);
        g.setColor(Color.WHITE);
        g.drawString("Press ENTER to Start!", Gui.getWidth() / 2 - g.getFontMetrics(tFont).stringWidth("Press ENTER to Start!") / 2, Gui.getHeight() / 2 - tFont.getSize() / 2);
    }
}
