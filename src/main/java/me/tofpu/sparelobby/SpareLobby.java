package me.tofpu.sparelobby;

import me.tofpu.sparelobby.commands.CommandManager;
import me.tofpu.sparelobby.listeners.PlayerJoinListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.inventivetalent.update.spiget.SpigetUpdate;
import org.inventivetalent.update.spiget.UpdateCallback;
import org.inventivetalent.update.spiget.comparator.VersionComparator;

public final class SpareLobby extends JavaPlugin {
    private static boolean isPlaceholderAPIHooked = false;
    private boolean updateAvailable;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        isPlaceholderAPIHooked = Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null;

        new Metrics(this, 9856);
        SpigetUpdate updateChecker = new SpigetUpdate(this, 87363);
        updateChecker.setVersionComparator(VersionComparator.SEM_VER);

        updateChecker.checkForUpdate(new UpdateCallback() {
            @Override
            public void updateAvailable(String s, String s1, boolean b) {
                updateAvailable = true;
                getLogger().warning("You are using an older version of SpareLobby (" + s1 + ")");
                getLogger().warning("It's highly recommended to update as there may be new fixes/features!");
            }

            @Override
            public void upToDate() {
                updateAvailable = false;
            }
        });

        if (!isPlaceholderAPIHooked) {
            getLogger().warning("Could not find PlaceholderAPI! It's recommended to install this plugin for extra functionality.");
        }
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
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);
    }

    public static boolean isPlaceholderAPIHooked() {
        return isPlaceholderAPIHooked;
    }

    public boolean isUpdateAvailable() {
        return updateAvailable;
    }
}
