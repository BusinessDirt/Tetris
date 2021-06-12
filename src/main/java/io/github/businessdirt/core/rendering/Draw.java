package io.github.businessdirt.core.rendering;

import io.github.businessdirt.core.blocks.*;

import javax.swing.*;
import java.awt.*;

public class Draw extends JLabel {

    private static final long serialVersionUID = 1L;
    private static int gameXStart, gameYStart;

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

        for (int x = 0; x < Block.map[0].length; x++) {
            for (int y = 0; y < Block.map.length; y++) {
                if (Block.map[y][x] == 1) {
                    g.drawImage(Block.texture.getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), getGameXStart() + x * Gui.getBlockSize(), getGameYStart() + y * Gui.getBlockSize(), null);
                }
            }
        }

        repaint();
    }

    public static int getGameXStart() {
        return gameXStart;
    }

    public static int getGameYStart() {
        return gameYStart;
    }
}
