package me.tofpu.extralobby.modules;

import me.clip.placeholderapi.PlaceholderAPI;
import me.tofpu.extralobby.ExtraLobby;
import me.tofpu.extralobby.utils.ChatUtil;

public class Options {
    private final boolean disable;
    private final String message;
    private final int amount;

    private final static ExtraLobby EXTRA_LOBBY = ExtraLobby.getPlugin(ExtraLobby.class);

    public Options(String path){
        this.disable = EXTRA_LOBBY.getConfig().getBoolean(Config.getConfigPath() + path + ".disable");
        this.amount = 0;

        String cache = ChatUtil.colorize(EXTRA_LOBBY.getConfig().getString(Config.getConfigPath() + path + ".message"));
        if (ExtraLobby.isPlaceholderAPIHooked()){
            this.message = PlaceholderAPI.setPlaceholders(null, cache);
            return;
        }
        this.message = cache;
    }

    public Options(String path, OptionsType optionsType){
        this.disable = EXTRA_LOBBY.getConfig().getBoolean(Config.getConfigPath() + path + ".disable");
        if (optionsType == OptionsType.INTEGER){
            message = null;
            this.amount = EXTRA_LOBBY.getConfig().getInt(Config.getConfigPath() + path + ".amount");
            return;
        }
        this.amount = 0;

        String cache = ChatUtil.colorize(EXTRA_LOBBY.getConfig().getString(Config.getConfigPath() + path + ".message"));
        if (ExtraLobby.isPlaceholderAPIHooked()){
            this.message = PlaceholderAPI.setPlaceholders(null, cache);
            return;
        }
        this.message = cache;
    }

    public boolean isDisable() {
        return disable;
    }

    public String getMessage() {
        return message;
    }

    public int getAmount() {
        return amount;
    }
}
