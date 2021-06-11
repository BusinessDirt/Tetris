package io.github.businessdirt.core.rendering;

import javax.swing.*;
import java.awt.*;

public class Gui {
    private static JFrame jf;
    private static Draw draw;

    private static final int width = 1280;
    private static final int height = 780;

    public Gui() {

        jf = new JFrame();
        jf.setSize(width,height);
        jf.setResizable(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setMinimumSize(new Dimension(width, height));
        jf.setTitle("Tetris");

        draw = new Draw();
        jf.setBounds(0, 0, width, height);
        draw.setVisible(true);
        jf.add(draw);

        jf.setVisible(true);
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
}
