package cn.sakuratown.jeremyhu.sakuraarchitecture.commands.handlers;

import org.bukkit.entity.Player;

import static cn.sakuratown.jeremyhu.sakuraarchitecture.utils.ConfigUtil.*;
import static cn.sakuratown.jeremyhu.sakuraarchitecture.utils.KeyUtil.PLUGIN;
import static cn.sakuratown.jeremyhu.sakuraarchitecture.utils.MessageUtil.sendColorMsg;

public class ClearHandler extends CommandHandler{
    @Override
    public void handle(Player player, String[] args) {
        if (args.length == 1){
            PLUGIN.selectionMap.get(player).clear();
            sendColorMsg(player, getSelectionClearMessage());
        }
    }
}
