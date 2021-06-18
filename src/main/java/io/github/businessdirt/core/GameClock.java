package io.github.businessdirt.core;

import io.github.businessdirt.Main;
import io.github.businessdirt.core.blocks.SingleBlock;

import java.awt.*;
import java.util.concurrent.ThreadPoolExecutor;

public class GameClock extends Thread {

    public void run() {
        while (true) {
            if (Main.getGameState() == GameState.INGAME) {
                try {
                    sleep(200);
                    for (SingleBlock block : Main.getFocusedBlocks()) {
                        block.setPosition(new Point(block.getPosition().x, block.getPosition().y + 1));
                        SingleBlock.map[block.getX()][block.getY()] = 0;
                        SingleBlock.map[block.getX()][block.getY() + 1] = 1;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
