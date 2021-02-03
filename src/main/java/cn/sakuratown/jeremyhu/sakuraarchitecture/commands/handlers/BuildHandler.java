package cn.sakuratown.jeremyhu.sakuraarchitecture.commands.handlers;

import org.bukkit.entity.Player;

import static cn.sakuratown.jeremyhu.sakuraarchitecture.utils.KeyUtil.PLUGIN;

public class BuildHandler extends CommandHandler{
    @Override
    public void handle(Player player, String[] args) {
        if (args.length == 1){
            PLUGIN.selectionMap.get(player).build(player.getLocation());

        }
    }
}
