package cn.sakuratown.jeremyhu.sakuraarchitecture.utils;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;

import static cn.sakuratown.jeremyhu.sakuraarchitecture.utils.ConfigUtil.CONFIG_FILE;
import static cn.sakuratown.jeremyhu.sakuraarchitecture.utils.KeyUtil.PLUGIN;

public class WorldUtil {

    public static World getPreviewWorld(){
        FileConfiguration config = PLUGIN.getConfig();
        World world;

        if (config.getString("preview-world") == null){

            PLUGIN.getLogger().info("Preview world missing.");
            PLUGIN.getLogger().info("Generating a new one for the plugin...");

            WorldCreator worldCreator = new WorldCreator("world_preview");
            world = worldCreator
                    .type(WorldType.FLAT)
                    .generateStructures(false)
                    .createWorld();

            assert world != null;
            world.setDifficulty(Difficulty.PEACEFUL);
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
            world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
            world.setTime(1000L);
            config.set("preview-world", world.getName());

            try {
                config.save(CONFIG_FILE);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }

            PLUGIN.getLogger().info("Preview world generated.");

        }else{
            String worldName = config.getString("preview-world");
            assert worldName != null;
            world = Bukkit.getWorld(worldName);
        }
        return world;
    }
}
