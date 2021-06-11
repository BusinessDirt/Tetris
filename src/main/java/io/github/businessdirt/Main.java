package io.github.businessdirt;

import io.github.businessdirt.core.blocks.Block;
import io.github.businessdirt.core.rendering.Gui;

public class Main {
    public static void main(String[] args) {
        Block.generateTexture(false);
        new Gui();
    }
}
