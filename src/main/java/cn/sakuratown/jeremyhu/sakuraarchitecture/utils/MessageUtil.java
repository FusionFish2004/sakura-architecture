package cn.sakuratown.jeremyhu.sakuraarchitecture.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageUtil {
    public static void sendColorMsg(Player player, String msg){
        msg = ChatColor.translateAlternateColorCodes('&',msg);
        player.sendMessage(msg);
    }
}
