package io.github.businessdirt.tetris;

import com.github.businessdirt.config.ConfigHandler;
import com.github.businessdirt.config.data.Property;
import com.github.businessdirt.config.data.PropertyType;
import io.github.businessdirt.Main;

import java.io.File;

public class Config extends ConfigHandler {

    @Property(
            type = PropertyType.SLIDER,
            name = "High Score",
            category = "High Scores",
            hidden = true
    )
    public static Integer highScore = 0;

    @Property(
            type = PropertyType.SLIDER,
            name = "Tetris",
            category = "High Scores",
            hidden = true
    )
    public static Integer tetrisCount = 0;

    public Config() {
        super(new File(Main.getConfigFolder(), "\\config.toml"));
        initialize();
    }
}
