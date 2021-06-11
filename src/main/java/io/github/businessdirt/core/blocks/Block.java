package io.github.businessdirt.core.blocks;

import io.github.businessdirt.core.rendering.DyeImage;
import io.github.businessdirt.core.rendering.Gui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Block {

    public static BufferedImage texture = new BufferedImage(8 , 8 , BufferedImage.TYPE_INT_ARGB);

    private int x;
    private int y;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawSingleBlock(Graphics g, int relX, int relY, Color color) {
        g.drawImage(DyeImage.dye(texture, new Color(color.getRed(), color.getGreen(), color.getBlue(), 64)).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), relX, relY, null);
    }

    public static void generateTexture(boolean save) {
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

        if (save) {
            try {
                File file = new File("texture.png");
                ImageIO.write(texture, "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Block Texture generated!");
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
}
