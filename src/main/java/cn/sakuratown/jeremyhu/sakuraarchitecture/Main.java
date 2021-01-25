package cn.sakuratown.jeremyhu.sakuraarchitecture;

import cn.sakuratown.jeremyhu.sakuraarchitecture.listeners.PlayerInteractListener;
import cn.sakuratown.jeremyhu.sakuraarchitecture.selection.Selection;
import cn.sakuratown.jeremyhu.sakuraarchitecture.utils.ConfigUtil;
import com.google.common.collect.Maps;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;

import static cn.sakuratown.jeremyhu.sakuraarchitecture.utils.WorldUtil.getPreviewWorld;

/**
 * 插件主类
 * @author JeremyHu
 */

public class Main extends JavaPlugin {

    public final Map<Player, Selection> selectionMap = Maps.newHashMap();

    @Override
    public void onEnable(){

        ConfigUtil.initConfig(this);
        Bukkit.getPluginCommand("sa").setExecutor(this);
        getPreviewWorld();

        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ///fill -107 4 252 -103 9 248

        Player player = (Player) sender;
        Selection selection = selectionMap.get(player);
        selection.getBlocks();
        getLogger().info(selection.toString());


        /*
        Player player = (Player) sender;
        Location location = player.getLocation();
        location.setWorld(getPreviewWorld());
        player.teleport(location);


         */
        return true;
    }
}
