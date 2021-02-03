package cn.sakuratown.jeremyhu.sakuraarchitecture.utils;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.TranslatableComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Map;

import static cn.sakuratown.jeremyhu.sakuraarchitecture.utils.ConfigUtil.getMaterialListRaw;

public class MessageUtil {

    public static void sendColorMsg(Player player, String msg){
        msg = ChatColor.translateAlternateColorCodes('&',msg);
        player.sendMessage(msg);
    }

    public static void sendMaterialListMsg(Player player, Map<Material, Integer> materialMap){
        String raw = getMaterialListRaw();
        materialMap.keySet().forEach(material -> {
            String msg = raw.replace("%MATERIAL",material.name())
                    .replace("%AMOUNT",String.valueOf(materialMap.get(material)));
            msg = ChatColor.translateAlternateColorCodes('&',msg);
            sendColorMsg(player, msg);

        });
    }
}
