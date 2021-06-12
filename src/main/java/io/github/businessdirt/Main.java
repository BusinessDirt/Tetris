package io.github.businessdirt;

import io.github.businessdirt.core.blocks.Block;
import io.github.businessdirt.core.rendering.Gui;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        new Gui();
        for (int x = 0; x < Block.map[0].length; x++) {
            for (int y = 0; y < Block.map.length; y++) {
                new Block(x, y);
            }
        }
    }
}
