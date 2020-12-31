package me.tofpu.extralobby.listeners;

import me.tofpu.extralobby.ExtraLobby;
import me.tofpu.extralobby.modules.Config;
import me.tofpu.extralobby.modules.Options;
import org.bukkit.Bukkit;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerJoinListener implements Listener {
    private final ExtraLobby extraLobby;

    public PlayerJoinListener(ExtraLobby extraLobby) {
        this.extraLobby = extraLobby;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        final Player player = event.getPlayer();
        final Options welcomeTitle = Config.WELCOME_TITLE.getOptions();
        final Options welcomeFireworks = Config.WELCOME_FIREWORKS.getOptions();
        final Options motdMessage = Config.MOTD_MESSAGE.getOptions();

        if (!welcomeFireworks.isDisable()) {
            final int[] i = new int[1];
            i[0] = welcomeFireworks.getAmount();

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (i[0] == 0) {
                        cancel();
                        return;
                    }
                    i[0]--;
                    player.getWorld().spawn(player.getLocation(), Firework.class);
                }
            }.runTaskTimer(extraLobby, 5, 2);
        }

        if (!welcomeTitle.isDisable()) {
            final String[] args = welcomeTitle.getMessage().split(":");
            player.sendTitle(args[0], args[1]);
        }

        if (!motdMessage.isDisable()) {
            player.sendMessage(motdMessage.getMessage());
        }
    }
}
