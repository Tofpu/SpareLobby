package me.tofpu.sparelobby.listeners;

import me.tofpu.sparelobby.SpareLobby;
import me.tofpu.sparelobby.modules.config.Config;
import me.tofpu.sparelobby.modules.config.options.Options;
import me.tofpu.sparelobby.utils.ChatUtil;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerJoinListener implements Listener {
    private final SpareLobby spareLobby;

    public PlayerJoinListener(SpareLobby spareLobby) {
        this.spareLobby = spareLobby;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        final Player player = event.getPlayer();
        final Options updateNotfi = Config.UPDATE_NOTIFICATIONS.getOptions();
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
            }.runTaskTimer(spareLobby, 5, 2);
        }

        if (!welcomeTitle.isDisable()) {
            final String[] args = welcomeTitle.getMessage().split(":");
            player.sendTitle(args[0], args[1]);
        }

        if (!motdMessage.isDisable()) {
            player.sendMessage(motdMessage.getMessage());
        }

        if (player.isOp() && spareLobby.isUpdateAvailable()){
            player.sendMessage(ChatUtil.prefixColorize("&eYou are using an older version of SpareLobby &8(&7https://www.spigotmc.org/resources/sparelobby-an-addon-for-lobby-plugins.87363/&8)"));
            player.sendMessage(ChatUtil.prefixColorize("&eIt's highly recommended to update as there may be new fixes/features."));
        }
    }
}
