package io.github.businessdirt.core.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomBlock {

    private List<int[]> blocks = new ArrayList<>();

    public RandomBlock() {
        Random random = new Random();
        int i = random.nextInt(7);
        switch (i) {
            case 0:
                new RandomBlock(BlockType.BLOCK_L);
                break;
            case 1:
                new RandomBlock(BlockType.BLOCK_J);
                break;
            case 2:
                new RandomBlock(BlockType.BLOCK_Z);
                break;
            case 3:
                new RandomBlock(BlockType.BLOCK_S);
                break;
            case 4:
                new RandomBlock(BlockType.BLOCK_LINE);
                break;
            case 5:
                new RandomBlock(BlockType.BLOCK_SQUARE);
                break;
            case 6:
                new RandomBlock(BlockType.BLOCK_T);
                break;
            default:
                System.out.println("error");
                break;
        }
    }

    public RandomBlock(BlockType blockType) {
        System.out.println(String.valueOf(blockType));
    }

    public enum BlockType {
        BLOCK_L, BLOCK_J, BLOCK_Z, BLOCK_S, BLOCK_LINE, BLOCK_SQUARE, BLOCK_T
    }

    public enum BlockColor {
        CYAN, BLUE, ORANGE, YELLOW, GREEN, RED, PURPLE
    }
}
