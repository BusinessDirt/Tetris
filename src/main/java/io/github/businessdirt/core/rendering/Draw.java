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

        for (int x = 0; x < SingleBlock.map.length; x++) {
            for (int y = 0; y < SingleBlock.map[0].length; y++) {
                if (SingleBlock.map[x][y] == 1) {
                    g.drawImage(DyeImage.dye(SingleBlock.getTexture(), new Color(88, 242, 242, 128)).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), getGameXStart() + x * Gui.getBlockSize(), getGameYStart() + y * Gui.getBlockSize(), null);
                }
                if (SingleBlock.map[x][y] == 2) {
                    g.drawImage(DyeImage.dye(SingleBlock.getTexture(), new Color(254, 231, 92, 128)).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), getGameXStart() + x * Gui.getBlockSize(), getGameYStart() + y * Gui.getBlockSize(), null);
                }
                if (SingleBlock.map[x][y] == 3) {
                    g.drawImage(DyeImage.dye(SingleBlock.getTexture(), new Color(87, 242, 135, 128)).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), getGameXStart() + x * Gui.getBlockSize(), getGameYStart() + y * Gui.getBlockSize(), null);
                }
                if (SingleBlock.map[x][y] == 4) {
                    g.drawImage(DyeImage.dye(SingleBlock.getTexture(), new Color(237, 66, 69, 128)).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), getGameXStart() + x * Gui.getBlockSize(), getGameYStart() + y * Gui.getBlockSize(), null);
                }
                if (SingleBlock.map[x][y] == 5) {
                    g.drawImage(DyeImage.dye(SingleBlock.getTexture(), new Color(88, 101, 242, 128)).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), getGameXStart() + x * Gui.getBlockSize(), getGameYStart() + y * Gui.getBlockSize(), null);
                }
                if (SingleBlock.map[x][y] == 6) {
                    g.drawImage(DyeImage.dye(SingleBlock.getTexture(), new Color(237, 121, 35, 128)).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), getGameXStart() + x * Gui.getBlockSize(), getGameYStart() + y * Gui.getBlockSize(), null);
                }
                if (SingleBlock.map[x][y] == 7) {
                    g.drawImage(DyeImage.dye(SingleBlock.getTexture(), new Color(235, 69, 158, 128)).getScaledInstance(Gui.getBlockSize(), Gui.getBlockSize(), Image.SCALE_SMOOTH), getGameXStart() + x * Gui.getBlockSize(), getGameYStart() + y * Gui.getBlockSize(), null);
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
