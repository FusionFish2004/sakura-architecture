package cn.sakuratown.jeremyhu.sakuraarchitecture.commands.handlers;

import org.bukkit.entity.Player;

import static cn.sakuratown.jeremyhu.sakuraarchitecture.utils.KeyUtil.PLUGIN;

public class TestHandler extends CommandHandler{

    @Override
    public void handle(Player player, String[] args) {
        PLUGIN.selectionMap.get(player).test();
    }
}
