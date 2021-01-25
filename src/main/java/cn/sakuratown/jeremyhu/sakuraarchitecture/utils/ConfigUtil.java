package cn.sakuratown.jeremyhu.sakuraarchitecture.utils;

import cn.sakuratown.jeremyhu.sakuraarchitecture.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

import static cn.sakuratown.jeremyhu.sakuraarchitecture.utils.KeyUtil.PLUGIN;

public class ConfigUtil {

    public static final File CONFIG_FILE = new File(PLUGIN.getDataFolder(),"config.yml");

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
        PLUGIN.getLogger().info("initializing config...");
        File file = new File(main.getDataFolder(),"config.yml");

        if(!main.getDataFolder().exists()){//判断目录是否存在 这里的目录是 plugin/插件名称/
            main.getDataFolder().mkdir();//不存在则创建这个文件夹
        }
        if(!file.exists()){//判断文件是否存在
            main.saveDefaultConfig();//不存在则创建默认的config.yml文件
        }
        main.reloadConfig();//重载配置
        PLUGIN.getLogger().info("config initialized.");
    }
}
