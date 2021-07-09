package io.github.businessdirt;

import io.github.businessdirt.tetris.Config;
import io.github.businessdirt.tetris.GameClock;
import io.github.businessdirt.tetris.GameState;
import io.github.businessdirt.tetris.blocks.Block;
import io.github.businessdirt.tetris.blocks.BlockType;
import io.github.businessdirt.tetris.blocks.SingleBlock;
import io.github.businessdirt.tetris.rendering.Draw;
import io.github.businessdirt.tetris.rendering.Gui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static GameState gameState = GameState.START;
    private static int focusedBlockRotation = 0;
    private static List<SingleBlock> focusedBlocks = new ArrayList<>();
    private static List<SingleBlock> allBlocks = new ArrayList<>();
    private static int score = 0;
    private static Block current;
    private static BlockType next = BlockType.random();
    private static File configFolder;

    public static void main(String[] args) {
        configFolder = new File(System.getenv("Appdata"), "java\\applications\\tetris");
        System.out.println("Config Files: " + configFolder.getAbsolutePath());
        configFolder.mkdirs();

        Config.getConfig().preload();

        new Gui();
        gameState = GameState.TITLE;

        Draw.setNewBlockMap(next);
        setCurrentBlock(new Block());
        new GameClock().start();
    }

    public static GameState getGameState() {
        return gameState;
    }
    public static void setGameState(GameState gameState) {
        Main.gameState = gameState;
    }

    public static List<SingleBlock> getFocusedBlocks() {
        return focusedBlocks;
    }

    public static List<SingleBlock> getAllBlocks() {
        return allBlocks;
    }

    public static int getBlockRotation() {
        return focusedBlockRotation;
    }
    public static void setBlockRotation(int focusedBlockRotation) {
        Main.focusedBlockRotation = focusedBlockRotation;
    }

    public static int getScore() {
        return score;
    }
    public static void setScore(int score) {
        Main.score = score;
    }

    public static Block getCurrentBlock() {
        return current;
    }
    public static void setCurrentBlock(Block current) {
        Main.current = current;
    }

    public static BlockType getNextBlock() {
        return next;
    }
    public static void setNextBlock(BlockType next) {
        Main.next = next;
    }

    public static File getConfigFolder() {
        return configFolder;
    }
}
