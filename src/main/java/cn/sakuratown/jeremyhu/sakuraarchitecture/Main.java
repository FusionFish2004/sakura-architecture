package cn.sakuratown.jeremyhu.sakuraarchitecture;

import cn.sakuratown.jeremyhu.sakuraarchitecture.selection.Selection;
import cn.sakuratown.jeremyhu.sakuraarchitecture.utils.ConfigUtil;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

import static cn.sakuratown.jeremyhu.sakuraarchitecture.utils.ConfigUtil.CONFIG_FILE;
import static cn.sakuratown.jeremyhu.sakuraarchitecture.utils.WorldUtil.getPreviewWorld;

/**
 * 插件主类
 * @author JeremyHu
 */

public class Main extends JavaPlugin {

    @Override
    public void onEnable(){
        ConfigUtil.initConfig(this);
        Bukkit.getPluginCommand("sa").setExecutor(this);
        getPreviewWorld();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ///fill -107 4 252 -103 9 248
        World world = ((Player) sender).getWorld();
        Location pos1 = new Location(world, -103, 4, 252);
        Location pos2 = new Location(world, -107, 7, 248);
        Selection selection = new Selection(pos1,pos2);

        return true;
    }
}
