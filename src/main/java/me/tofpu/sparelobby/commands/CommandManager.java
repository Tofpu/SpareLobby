package me.tofpu.sparelobby.commands;

import me.tofpu.sparelobby.SpareLobby;
import me.tofpu.sparelobby.modules.config.Config;
import me.tofpu.sparelobby.utils.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandManager implements CommandExecutor {
    public final SpareLobby spareLobby;

    public CommandManager(SpareLobby spareLobby) {
        this.spareLobby = spareLobby;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0){
            sender.sendMessage(ChatUtil.prefixColorize("&eYou have to provide an argument!"));
            sender.sendMessage(ChatUtil.prefixColorize("&eType &6/sparelobby help &efor further help!"));
            return false;
        }
        arguments(sender, args);

        return false;
    }

    public void arguments(CommandSender sender, String[] args){
        if (args[0].equalsIgnoreCase("help")){
            sender.sendMessage(ChatUtil.prefixColorize("&eExtraLobby Commands:"));
            sender.sendMessage(ChatUtil.prefixColorize(" &8» &e/sparelobby reload"));
            return;
        }

        if (args[0].equalsIgnoreCase("reload")){
            spareLobby.reloadConfig();
            for (Config config : Config.values()){
                config.reload();
            }
            sender.sendMessage(ChatUtil.prefixColorize("&eYou have successfully reloaded the config.yml!"));
            return;
        }
    }
}
