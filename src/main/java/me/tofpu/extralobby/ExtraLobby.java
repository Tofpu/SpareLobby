package me.tofpu.extralobby;

import me.tofpu.extralobby.listeners.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExtraLobby extends JavaPlugin {
    private static boolean isPlaceholderAPIHooked = false;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        isPlaceholderAPIHooked = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;

        if (!isPlaceholderAPIHooked) {
            getLogger().warning("Could not find PlaceholderAPI! This plugin is highly recommended.");
        }
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static boolean isPlaceholderAPIHooked() {
        return isPlaceholderAPIHooked;
    }
}