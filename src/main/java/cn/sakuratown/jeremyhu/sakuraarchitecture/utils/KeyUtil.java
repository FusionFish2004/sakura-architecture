package cn.sakuratown.jeremyhu.sakuraarchitecture.utils;

import cn.sakuratown.jeremyhu.sakuraarchitecture.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public class KeyUtil {
    public static final Main PLUGIN = JavaPlugin.getPlugin(Main.class);

    public static final NamespacedKey UUID = new NamespacedKey(PLUGIN,"uuid");
    public static final NamespacedKey NAME = new NamespacedKey(PLUGIN, "name");
    public static final NamespacedKey CREATOR = new NamespacedKey(PLUGIN, "creator");
    public static final NamespacedKey SELECTION = new NamespacedKey(PLUGIN, "selection");
}
