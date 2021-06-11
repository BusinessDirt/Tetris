package io.github.businessdirt.core.blocks;

import io.github.businessdirt.core.rendering.Gui;

import java.awt.*;

public class OBlock extends Block {
    private int[][] layout = new int[][] {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {1, 1, 0, 0},
            {1, 1, 0, 0}
    };

    public OBlock() {
        super(5, 18);
    }

    public void drawBlock(Graphics g) {
        for (int x2 = 0; x2 < layout.length; x2++) {
            for (int y2 = 0; y2 < layout.length; y2++) {
                if (layout[y2][x2] == 1) {
                    drawSingleBlock(g, 40 + 8 * Gui.getBlockSize() + x2 * Gui.getBlockSize(), 10 + 4 * Gui.getBlockSize() + y2 * Gui.getBlockSize(), Color.YELLOW);
                }
            }
        }
    }
}
