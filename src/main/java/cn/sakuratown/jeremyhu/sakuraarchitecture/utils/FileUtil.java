package cn.sakuratown.jeremyhu.sakuraarchitecture.utils;

import java.io.File;

import static cn.sakuratown.jeremyhu.sakuraarchitecture.utils.KeyUtil.PLUGIN;

public class FileUtil {
    public static final File SELECTION_JSON_FOLDER = new File(PLUGIN.getDataFolder(), "selections");

    public static void initSelectionSaves(){
        PLUGIN.getLogger().info("Initializing selections...");
        if (!SELECTION_JSON_FOLDER.exists()){
            PLUGIN.getLogger().info("Selection folder missing.");
            PLUGIN.getLogger().info("Generating a new one for the plugin...");
            SELECTION_JSON_FOLDER.mkdir();
            PLUGIN.getLogger().info("Selection folder generated.");
        }
    }
}
