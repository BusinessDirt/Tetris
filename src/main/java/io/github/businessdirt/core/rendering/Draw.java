package io.github.businessdirt.core.rendering;

import javax.swing.*;
import java.awt.*;

public class Draw extends JLabel {

    private static final long serialVersionUID = 1L;

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //draw///
        g.setColor(new Color(52, 73, 94, 255));
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.white);
        g.drawRect(getWidth() / 2 - 300, 25, 600, getHeight() - 50);
        repaint();
    }
}
