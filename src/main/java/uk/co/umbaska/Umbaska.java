package uk.co.umbaska;

import  ch.njol.skript.Skript;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import uk.co.umbaska.config.ConfigFile;
import uk.co.umbaska.modules.ModuleManager;

import java.io.File;

/**
 * @author Andrew Tran
 */
public class Umbaska extends JavaPlugin{

    private static Umbaska instance;

    public static Umbaska getInstance() {
        return instance;
    }

    private File mainConfigurationFile;
    private ConfigFile mainConfiguration;

    @Override
    public void onEnable(){
        instance = this;

        if (Bukkit.getPluginManager().getPlugin("Skript") == null){
            getLogger().severe("Please make sure you have downloaded and installed Skript");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        if (!getDataFolder().exists()){
            getDataFolder().mkdir();
        }
        ModuleManager.prepare();
        mainConfigurationFile = new File(getDataFolder(), "config.yml");
        mainConfiguration = new ConfigFile(mainConfigurationFile, "config.yml");

        Skript.registerAddon(this);

        ModuleManager.loadAndEnableModules();

    }

    public File getMainConfigurationFile() {
        return mainConfigurationFile;
    }

    public ConfigFile getMainConfiguration() {
        return mainConfiguration;
    }
}
