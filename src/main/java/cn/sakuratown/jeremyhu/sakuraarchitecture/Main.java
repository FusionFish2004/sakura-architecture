package cn.sakuratown.jeremyhu.sakuraarchitecture;

import cn.sakuratown.jeremyhu.sakuraarchitecture.commands.MainCommandExecuter;
import cn.sakuratown.jeremyhu.sakuraarchitecture.listeners.PlayerInteractListener;
import cn.sakuratown.jeremyhu.sakuraarchitecture.selection.Selection;
import static cn.sakuratown.jeremyhu.sakuraarchitecture.utils.FileUtil.*;
import static cn.sakuratown.jeremyhu.sakuraarchitecture.utils.ConfigUtil.*;

import com.google.common.collect.Maps;
import org.bukkit.*;
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

        initConfig(this);
        initSelectionSaves();
        Bukkit.getPluginCommand("sa").setExecutor(new MainCommandExecuter());
        getPreviewWorld();

        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);

    }
}
