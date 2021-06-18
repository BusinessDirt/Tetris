package io.github.businessdirt;

import io.github.businessdirt.core.GameClock;
import io.github.businessdirt.core.GameState;
import io.github.businessdirt.core.blocks.Block;
import io.github.businessdirt.core.blocks.BlockType;
import io.github.businessdirt.core.blocks.SingleBlock;
import io.github.businessdirt.core.rendering.Gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static GameState gameState = GameState.START;
    private static List<SingleBlock> focusedBlocks = new ArrayList<>();

    public static void main(String[] args) {
        new Gui();
        gameState = GameState.INGAME;
        /*for (int x = 0; x < SingleBlock.map.length; x++) {
            for (int y = 0; y < SingleBlock.map[0].length; y++) {
                new SingleBlock(x, y, BlockType.random());
            }
        }*/
        new Block();
        new GameClock().start();
    }

    public static GameState getGameState() {
        return gameState;
    }

    public static List<SingleBlock> getFocusedBlocks() {
        return focusedBlocks;
    }
}
