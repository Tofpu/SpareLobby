package me.tofpu.sparelobby.modules.config.options;

import me.clip.placeholderapi.PlaceholderAPI;
import me.tofpu.sparelobby.SpareLobby;
import me.tofpu.sparelobby.modules.config.Config;
import me.tofpu.sparelobby.modules.config.options.type.OptionsType;
import me.tofpu.sparelobby.utils.ChatUtil;

public class Options {
    private boolean disable = false;
    private String message = "";
    private int amount = 0;

    private final OptionsType optionsType;
    private final String path;

    private final static SpareLobby EXTRA_LOBBY = SpareLobby.getPlugin(SpareLobby.class);

    public Options(String path, OptionsType optionsType){
        this.optionsType = optionsType;
        this.path = path;
        reload();
    }

    public void reload() {
        this.disable = EXTRA_LOBBY.getConfig().getBoolean(Config.getConfigPath() + path + ".disable");

        if (optionsType == null){
            return;
        }

        switch (optionsType){
            case STRING:
                String cache = ChatUtil.colorize(EXTRA_LOBBY.getConfig().getString(Config.getConfigPath() + path + ".message"));
                if (SpareLobby.isPlaceholderAPIHooked()){
                    this.message = PlaceholderAPI.setPlaceholders(null, cache);
                    return;
                }
                this.message = cache;
                break;
            case INTEGER:
                this.amount = EXTRA_LOBBY.getConfig().getInt(Config.getConfigPath() + path + ".amount");
                break;
        }
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
