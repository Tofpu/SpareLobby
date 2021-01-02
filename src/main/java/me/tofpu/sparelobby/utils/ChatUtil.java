package me.tofpu.sparelobby.utils;

import org.bukkit.ChatColor;

public class ChatUtil {
    public static String colorize(String content){
        return ChatColor.translateAlternateColorCodes('&', content);
    }

    public static String prefixColorize(String content){
        return colorize("&8[&eSpare&6Lobby&8]&r " + content);
    }
}
