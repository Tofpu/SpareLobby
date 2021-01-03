package me.tofpu.sparelobby;

import me.tofpu.sparelobby.commands.CommandManager;
import me.tofpu.sparelobby.listeners.PlayerJoinListener;
import me.tofpu.spigotupdater.updated.Updater;
import me.tofpu.spigotupdater.updated.callback.CallBack;
import me.tofpu.spigotupdater.updated.utils.SpigotUtil;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Level;

public final class SpareLobby extends JavaPlugin {
    private Updater updater;
    private String url;

    private static boolean isPlaceholderAPIHooked = false;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        isPlaceholderAPIHooked = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;

        new Metrics(this, 9856);

        this.updater = new Updater(this, 87363);
        updater.result(new CallBack() {
            @Override
            public void olderVersion(int currentVersion, int latestVersion) {
                getLogger().log(Level.WARNING, String.format("You're running an older version of %s!", getDescription().getName()));
                try {
                    url = SpigotUtil.getResultSync("https://raw.githubusercontent.com/Tofpu/SpareLobby/main/url");
                    getLogger().log(Level.WARNING, String.format("You can download the latest version at: %s", url));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void isUpdated(int latestVersion) {
                getLogger().log(Level.INFO, "You are running the latest version!");
            }
        });

        registerCommands();
        registerListeners();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerCommands(){
        getCommand("sparelobby").setExecutor(new CommandManager(this));
    }

    public void registerListeners(){
        final PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new PlayerJoinListener(this), this);
    }

    public String getUrl() {
        return url;
    }

    public Updater getUpdater() {
        return updater;
    }

    public static boolean isPlaceholderAPIHooked() {
        return isPlaceholderAPIHooked;
    }
}
