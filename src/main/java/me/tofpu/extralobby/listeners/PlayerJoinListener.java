package me.tofpu.extralobby.listeners;

import me.tofpu.extralobby.modules.Config;
import me.tofpu.extralobby.modules.Options;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        final Player player = event.getPlayer();
        final Options welcomeTitle = Config.WELCOME_TITLE.getOptions();
        final Options welcomeFireworks = Config.WELCOME_FIREWORKS.getOptions();
        final Options motdMessage = Config.MOTD_MESSAGE.getOptions();

        if (!welcomeFireworks.isDisable()) {
            for (int i = 0; i <= welcomeFireworks.getAmount(); i++) {
                player.getWorld().spawn(player.getLocation(), Firework.class);
            }
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
