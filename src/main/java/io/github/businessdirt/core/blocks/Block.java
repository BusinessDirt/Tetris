package io.github.businessdirt.core.blocks;

import io.github.businessdirt.Main;

import java.util.ArrayList;
import java.util.List;

public class Block {

    public Block() {
        new Block(BlockType.random());
    }

    public Block(BlockType type) {
        Main.getFocusedBlocks().clear();
        int x = 5, y = 3;
        new SingleBlock(x, y, type, true);
        switch (type) {
            case I:
                new SingleBlock(x, y - 1, BlockType.I, true);
                new SingleBlock(x, y + 1, BlockType.I, true);
                new SingleBlock(x, y + 2, BlockType.I, true);
                break;
            case O:
                new SingleBlock(x, y - 1, BlockType.O, true);
                new SingleBlock(x - 1, y -1, BlockType.O, true);
                new SingleBlock(x - 1, y, BlockType.O, true);
                break;
            case S:
                new SingleBlock(x, y - 1, BlockType.S, true);
                new SingleBlock(x + 1, y - 1, BlockType.S, true);
                new SingleBlock(x - 1, y, BlockType.S, true);
                break;
            case Z:
                new SingleBlock(x, y - 1, BlockType.Z, true);
                new SingleBlock(x - 1, y - 1, BlockType.Z, true);
                new SingleBlock(x + 1, y, BlockType.Z, true);
                break;
            case J:
                new SingleBlock(x, y - 1, BlockType.J, true);
                new SingleBlock(x, y + 1, BlockType.J, true);
                new SingleBlock(x - 1, y + 1, BlockType.J, true);
                break;
            case L:
                new SingleBlock(x, y - 1, BlockType.L, true);
                new SingleBlock(x, y + 1, BlockType.L, true);
                new SingleBlock(x + 1, y + 1, BlockType.L, true);
                break;
            case T:
                new SingleBlock(x, y + 1, BlockType.T, true);
                new SingleBlock(x - 1, y, BlockType.T, true);
                new SingleBlock(x + 1, y, BlockType.T, true);
                break;
        }
    }
}
