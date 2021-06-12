package io.github.businessdirt;

import io.github.businessdirt.core.blocks.BlockType;
import io.github.businessdirt.core.blocks.SingleBlock;
import io.github.businessdirt.core.rendering.Gui;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        new Gui();
        for (int x = 0; x < SingleBlock.map.length; x++) {
            for (int y = 0; y < SingleBlock.map[0].length; y++) {
                new SingleBlock(x, y, BlockType.random());
            }
        }
    }
}
