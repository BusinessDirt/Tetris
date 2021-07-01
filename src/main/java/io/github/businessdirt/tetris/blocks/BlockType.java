package io.github.businessdirt.tetris.blocks;

import java.util.concurrent.ThreadLocalRandom;

public enum BlockType {

    I, O, S, Z, J, L, T;

    public static BlockType random() {
        return values()[ThreadLocalRandom.current().nextInt(0, values().length)];
    }

    public int getIntValue() {
        switch (this) {
            case I:
                return 1;
            case O:
                return 2;
            case S:
                return 3;
            case Z:
                return 4;
            case J:
                return 5;
            case L:
                return 6;
            case T:
                return 7;
            default:
                return 0;
        }
    }
}
