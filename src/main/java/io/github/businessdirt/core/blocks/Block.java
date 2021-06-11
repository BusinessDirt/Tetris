package io.github.businessdirt.core.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Block {

    private List<int[]> blocks = new ArrayList<>();

    public Block() {
        Random random = new Random();
        int i = random.nextInt(7);
        switch (i) {
            case 0:
                new Block(BlockType.BLOCK_L);
                break;
            case 1:
                new Block(BlockType.BLOCK_L_INVERTED);
                break;
            case 2:
                new Block(BlockType.BLOCK_Z);
                break;
            case 3:
                new Block(BlockType.BLOCK_Z_INVERTED);
                break;
            case 4:
                new Block(BlockType.BLOCK_LINE);
                break;
            case 5:
                new Block(BlockType.BLOCK_SQUARE);
                break;
            case 6:
                new Block(BlockType.BLOCK_T);
                break;
            default:
                System.out.println("error");
                break;
        }
    }

    public Block(BlockType blockType) {
        System.out.println(String.valueOf(blockType));
    }

    public enum BlockType {
        BLOCK_L, BLOCK_L_INVERTED, BLOCK_Z, BLOCK_Z_INVERTED, BLOCK_LINE, BLOCK_SQUARE, BLOCK_T
    }
}
