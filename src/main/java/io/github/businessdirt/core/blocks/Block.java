package io.github.businessdirt.core.blocks;

import io.github.businessdirt.core.rendering.Draw;
import io.github.businessdirt.core.rendering.DyeImage;
import io.github.businessdirt.core.rendering.Gui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Block {

    public static BufferedImage texture = generateTexture();
    public static int[][] map = new int[20][10];

    private Point position;

    public Block(int x, int y) {
        map[y][x] = 1;
        position = ptc(x, y);
    }

    public static BufferedImage generateTexture() {
        BufferedImage texture = new BufferedImage(8 , 8 , BufferedImage.TYPE_INT_ARGB);
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

        return texture;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point ptc(int x, int y) {
        return new Point(Draw.getGameXStart() + x * Gui.getBlockSize(), Draw.getGameYStart() + y * Gui.getBlockSize());
    }
}
