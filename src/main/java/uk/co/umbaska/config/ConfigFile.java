package uk.co.umbaska.config;

import ch.njol.skript.util.FileUtils;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import uk.co.umbaska.Umbaska;

import java.io.File;
import java.io.IOException;

/**
 * @author Andrew Tran
 */
public class ConfigFile {
    File file;
    YamlConfiguration configuration;

    public ConfigFile(File file) {
        this.file = file;
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                Umbaska.getInstance().getLogger().severe(String.format("Failed to create new empty file %s", file.getAbsolutePath()));
            }
        }
    }

    public ConfigFile(File file, String pathWithinJar){
        this.file = file;
        Umbaska.getInstance().saveResource(pathWithinJar, false);
    }

    public File getFile() {
        return file;
    }

    public void read(){
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    public void save(){
        try {
            configuration.save(file);
        } catch (IOException e) {
            Umbaska.getInstance().getLogger().severe(String.format("Could not save configuration file %s", file.getAbsolutePath()));
        }
    }

    public YamlConfiguration getConfiguration() {
        return configuration;
    }
}
