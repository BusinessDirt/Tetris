package io.github.businessdirt.tetris.blocks;

import io.github.businessdirt.Main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SingleBlock {

    private int x, y;
    private static BufferedImage texture;
    private final BlockType type;
    public static int[][] map = new int[10][20];
    private boolean rotationCenter;

    public SingleBlock(int x, int y, BlockType type) {
        this(x, y, type, false);
    }

    public SingleBlock(int x, int y, BlockType type, boolean rotationCenter) {
        Main.getFocusedBlocks().add(this);

        this.rotationCenter = rotationCenter;
        this.type = type;
        this.x = x;
        this.y = y;

        switch (type) {
            case I:
                map[x][y] = 1;
                break;
            case O:
                map[x][y] = 2;
                break;
            case S:
                map[x][y] = 3;
                break;
            case Z:
                map[x][y] = 4;
                break;
            case J:
                map[x][y] = 5;
                break;
            case L:
                map[x][y] = 6;
                break;
            case T:
                map[x][y] = 7;
                break;
        }
    }

    public static BufferedImage getTexture() {
        if (texture == null) {
            texture = new BufferedImage(8, 8, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = texture.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillRect(0, 0, 7, 1);
            g2d.fillRect(0, 1, 1, 6);
            g2d.setColor(Color.GRAY);
            g2d.fillRect(1, 1, 6, 6);
            g2d.setColor(Color.DARK_GRAY);
            g2d.fillRect(0, 7, 8, 1);
            g2d.fillRect(7, 0, 1, 7);
            g2d.dispose();
        }
        return texture;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public BlockType getType() {
        return type;
    }

    public boolean isRotationCenter() {
        return rotationCenter;
    }
    public void setRotationCenter(boolean rotationCenter) {
        this.rotationCenter = rotationCenter;
    }
}
