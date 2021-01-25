package cn.sakuratown.jeremyhu.sakuraarchitecture.utils;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;

import static cn.sakuratown.jeremyhu.sakuraarchitecture.utils.ConfigUtil.CONFIG_FILE;
import static cn.sakuratown.jeremyhu.sakuraarchitecture.utils.KeyUtil.PLUGIN;

public class WorldUtil {

    public static World getPreviewWorld(){
        FileConfiguration config = PLUGIN.getConfig();
        World world;
        if (config.getString("preview-world") == null){
            WorldCreator worldCreator = new WorldCreator("world-preview");
            world = worldCreator
                    .type(WorldType.FLAT)
                    .generateStructures(false)
                    .createWorld();
            config.set("preview-world", world.getName());

            try {
                config.save(CONFIG_FILE);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

        }else{
            String worldName = config.getString("preview-world");
            world = Bukkit.getWorld(worldName);
        }
        return world;
    }
}
