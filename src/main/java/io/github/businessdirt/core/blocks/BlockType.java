package io.github.businessdirt.core.blocks;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public enum BlockType {
    I, O, S, Z, J, L, T;

    public static BlockType random() {
        return values()[ThreadLocalRandom.current().nextInt(0, values().length)];
    }
}
