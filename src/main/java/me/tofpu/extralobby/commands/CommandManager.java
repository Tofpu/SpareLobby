package me.tofpu.extralobby.commands;

import me.tofpu.extralobby.ExtraLobby;
import me.tofpu.extralobby.utils.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandManager implements CommandExecutor {
    public final ExtraLobby extraLobby;

    public CommandManager(ExtraLobby extraLobby) {
        this.extraLobby = extraLobby;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0){
            sender.sendMessage(ChatUtil.prefixColorize("&eYou have to provide an argument!"));
            sender.sendMessage(ChatUtil.prefixColorize("&eType &6/extralobby help &efor further help!"));
            return false;
        }
        arguments(sender, args);

        return false;
    }

    public void arguments(CommandSender sender, String[] args){
        if (args[0].equalsIgnoreCase("help")){
            sender.sendMessage(ChatUtil.prefixColorize("&eExtraLobby Commands:"));
            sender.sendMessage(ChatUtil.prefixColorize(" &8» &e/extralobby reload"));
            return;
        }

        if (args[0].equalsIgnoreCase("reload")){
            extraLobby.reloadConfig();
            sender.sendMessage(ChatUtil.prefixColorize("&eYou have successfully reloaded the config.yml!"));
            return;
        }
    }
}
