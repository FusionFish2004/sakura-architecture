package cn.sakuratown.jeremyhu.sakuraarchitecture.commands.handlers;

import org.bukkit.entity.Player;

public abstract class CommandHandler {
    public abstract void handle(Player player, String[] args);
}
