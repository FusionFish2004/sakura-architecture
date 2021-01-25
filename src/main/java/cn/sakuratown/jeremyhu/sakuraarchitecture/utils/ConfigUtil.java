package cn.sakuratown.jeremyhu.sakuraarchitecture.utils;

import cn.sakuratown.jeremyhu.sakuraarchitecture.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static cn.sakuratown.jeremyhu.sakuraarchitecture.utils.KeyUtil.PLUGIN;

public class ConfigUtil {

    public static final File CONFIG_FILE = new File(PLUGIN.getDataFolder(),"config.yml");
    public static final FileConfiguration CONFIG = PLUGIN.getConfig();

    public static FileConfiguration loadYML(File file){
        if(!(file.exists())){
            try{
                file.createNewFile();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    public static void initConfig(Main main){
        PLUGIN.getLogger().info("Initializing config...");
        File file = new File(main.getDataFolder(),"config.yml");

        if(!main.getDataFolder().exists()){//判断目录是否存在 这里的目录是 plugin/插件名称/
            PLUGIN.getLogger().info("Plugin data folder missing.");
            PLUGIN.getLogger().info("Generating a new one for the plugin...");
            main.getDataFolder().mkdir();//不存在则创建这个文件夹
            PLUGIN.getLogger().info("Data folder generated.");
        }
        if(!file.exists()){//判断文件是否存在
            PLUGIN.getLogger().info("Plugin config file missing.");
            PLUGIN.getLogger().info("Generating a new one for the plugin...");
            main.saveDefaultConfig();//不存在则创建默认的config.yml文件
            PLUGIN.getLogger().info("Config file generated.");
        }
        main.reloadConfig();//重载配置
        PLUGIN.getLogger().info("Config initialized.");
    }

    public static int getMaxX(){
        return PLUGIN.getConfig().getInt("selection.max-x");
    }

    public static int getMaxY(){
        return PLUGIN.getConfig().getInt("selection.max-y");
    }

    public static int getMaxZ(){
        return PLUGIN.getConfig().getInt("selection.max-z");
    }

    public static String getFirstSelectionMessage(){
        return CONFIG.getString("messages.first-point-selection");
    }

    public static String getSecondSelectionMessage(){
        return CONFIG.getString("messages.second-point-selection");
    }
}
