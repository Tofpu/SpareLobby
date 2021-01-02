package me.tofpu.sparelobby;

import me.tofpu.sparelobby.commands.CommandManager;
import me.tofpu.sparelobby.listeners.PlayerJoinListener;
import me.tofpu.sparelobby.modules.config.Config;
import me.tofpu.spigotupdater.SpigotUpdater;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpareLobby extends JavaPlugin {
    private static boolean isPlaceholderAPIHooked = false;
    private SpigotUpdater updater;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        isPlaceholderAPIHooked = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;

        new Metrics(this, 9856);
        updater = new SpigotUpdater(this, "https://www.spigotmc.org/resources/sparelobby-an-addon-for-lobby-plugins.", 87363, !Config.UPDATE_NOTIFICATIONS.getOptions().isDisable());

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

    public static boolean isPlaceholderAPIHooked() {
        return isPlaceholderAPIHooked;
    }

    public SpigotUpdater getUpdater() {
        return updater;
    }
}
