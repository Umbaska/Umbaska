package uk.co.umbaska;

import  ch.njol.skript.Skript;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import uk.co.umbaska.config.ConfigFile;
import uk.co.umbaska.modules.ModuleManager;

import java.io.File;

/**
 * The main plugin class for Umbaska
 * @author Andrew Tran
 */
public class Umbaska extends JavaPlugin{

    private static Umbaska instance;

    /**
     * Gets the currently running instance of Umbaska
     * @return the currently running instance of Umbaska
     */
    public static Umbaska getInstance() {
        return instance;
    }

    private File mainConfigurationFile;
    private ConfigFile mainConfiguration;

    /**
     * Ran when Umbaska is being loaded by Bukkit
     */
    @Override
    public void onEnable(){
        instance = this;

        if (Bukkit.getPluginManager().getPlugin("Skript") == null){
            getLogger().severe("Please make sure you have downloaded and installed Skript");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
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

    /**
     * Gets the main configuration file (config.yml)
     * @return the main configuration file
     */
    public File getMainConfigurationFile() {
        return mainConfigurationFile;
    }

    /**
     * Gets the main configuration
     * @return the main configuration
     */
    public ConfigFile getMainConfiguration() {
        return mainConfiguration;
    }
}
