package io.github.businessdirt.tetris.blocks;

import io.github.businessdirt.Main;
import io.github.businessdirt.tetris.GameState;
import io.github.businessdirt.tetris.rendering.Draw;

public class Block {

    public Block() {
        new Block(BlockType.random());
    }

    public Block(BlockType type) {
        Main.setBlockRotation(0);
        Main.getFocusedBlocks().clear();
        int x = 5, y = 1;
        if (SingleBlock.map[5][1] < 1) {
            new SingleBlock(x, y, type, true);
            switch (type) {
                case I:
                    new SingleBlock(x, y - 1, BlockType.I);
                    new SingleBlock(x, y + 1, BlockType.I);
                    new SingleBlock(x, y + 2, BlockType.I);
                    break;
                case O:
                    new SingleBlock(x, y - 1, BlockType.O);
                    new SingleBlock(x - 1, y - 1, BlockType.O);
                    new SingleBlock(x - 1, y, BlockType.O);
                    break;
                case S:
                    new SingleBlock(x, y - 1, BlockType.S);
                    new SingleBlock(x + 1, y - 1, BlockType.S);
                    new SingleBlock(x - 1, y, BlockType.S);
                    break;
                case Z:
                    new SingleBlock(x, y - 1, BlockType.Z);
                    new SingleBlock(x - 1, y - 1, BlockType.Z);
                    new SingleBlock(x + 1, y, BlockType.Z);
                    break;
                case J:
                    new SingleBlock(x, y - 1, BlockType.J);
                    new SingleBlock(x, y + 1, BlockType.J);
                    new SingleBlock(x - 1, y + 1, BlockType.J);
                    break;
                case L:
                    new SingleBlock(x, y - 1, BlockType.L);
                    new SingleBlock(x, y + 1, BlockType.L);
                    new SingleBlock(x + 1, y + 1, BlockType.L);
                    break;
                case T:
                    new SingleBlock(x, y + 1, BlockType.T);
                    new SingleBlock(x - 1, y, BlockType.T);
                    new SingleBlock(x + 1, y, BlockType.T);
                    break;
            }
        } else {
            Main.setGameState(GameState.GAMEOVER);
            for (int x2 = 0; x2 < SingleBlock.map.length; x2++) {
                for (int y2 = 0; y2 < SingleBlock.map[x2].length; y2++) {
                    SingleBlock.map[x2][y2] = 0;
                }
            }
            Main.setCurrentBlock(new Block(Main.getNextBlock()));
            Main.setNextBlock(BlockType.random());
            Draw.setNewBlockMap(Main.getNextBlock());
            Main.setScore(0);
        }
    }
}
