package me.tofpu.extralobby.utils;

import org.bukkit.ChatColor;

public class ChatUtil {
    public static String colorize(String content){
        return ChatColor.translateAlternateColorCodes('&', content);
    }
}
