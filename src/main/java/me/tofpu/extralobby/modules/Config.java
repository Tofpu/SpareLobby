package me.tofpu.extralobby.modules;

import me.tofpu.extralobby.OptionsType;

public enum Config {

    WELCOME_FIREWORKS(new Options("welcome-fireworks", OptionsType.INTEGER)),
    WELCOME_TITLE(new Options("welcome-title")),
    MOTD_MESSAGE(new Options("motd-message"));

    private final Options options;
    private final static String CONFIG_PATH = "settings.";

    Config(Options options) {
        this.options = options;
    }

    public static String getConfigPath() {
        return CONFIG_PATH;
    }

    public Options getOptions() {
        return options;
    }
}
