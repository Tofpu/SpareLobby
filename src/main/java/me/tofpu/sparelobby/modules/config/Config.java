package me.tofpu.sparelobby.modules.config;

import me.tofpu.sparelobby.modules.config.options.Options;
import me.tofpu.sparelobby.modules.config.options.type.OptionsType;

public enum Config {

    UPDATE_NOTIFICATIONS(new Options("update-notifications", null)),
    VANILLA_JOIN_MESSAGE(new Options("vanilla-join-message", null)),
    DEFAULT_JOIN_MESSAGE(new Options("default-join-message", OptionsType.STRING)),
    WELCOME_FIREWORKS(new Options("welcome-fireworks", OptionsType.INTEGER)),
    WELCOME_TITLE(new Options("welcome-title", OptionsType.STRING)),
    MOTD_MESSAGE(new Options("motd-message", OptionsType.STRING));

    private final Options options;
    private final static String CONFIG_PATH = "settings.";

    Config(Options options) {
        this.options = options;
    }

    public void reload(){
        options.reload();
    }

    public static String getConfigPath() {
        return CONFIG_PATH;
    }

    public Options getOptions() {
        return options;
    }
}
