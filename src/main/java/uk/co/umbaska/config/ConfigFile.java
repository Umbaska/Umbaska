package uk.co.umbaska.config;

import org.bukkit.configuration.file.YamlConfiguration;
import uk.co.umbaska.Umbaska;

import java.io.File;
import java.io.IOException;

/**
 * Represents a file that is a YAML configuration
 * @author Andrew Tran
 */
public class ConfigFile {
    File file;
    YamlConfiguration configuration;

    /**
     * Constructs a Config File for {@code file}
     * @param file the file to create a Config File for
     */
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

    /**
     * Constructs a Config File for {@code file} copying the file from within Umbaska if {@code file} is not found
     * @param file the file to create a Config File for
     * @param pathWithinJar the path within the Umbaska jar to copy if the file {@code file} does not exist
     */
    public ConfigFile(File file, String pathWithinJar){
        this.file = file;
        if (!file.exists()){
            Umbaska.getInstance().saveResource(pathWithinJar, false);
        }
    }

    /**
     * Gets the file that the ConfigFile represents
     * @return the file that the ConfigFile represents
     */
    public File getFile() {
        return file;
    }

    /**
     * Reads the configuration to memory
     */
    public void read(){
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    /**
     * Writes the configuration to the disk
     */
    public void save(){
        try {
            configuration.save(file);
        } catch (IOException e) {
            Umbaska.getInstance().getLogger().severe(String.format("Could not save configuration file %s", file.getAbsolutePath()));
        }
    }

    /**
     * Gets the {@link org.bukkit.configuration.file.YamlConfiguration} for the file
     * @return the {@link org.bukkit.configuration.file.YamlConfiguration} for the file
     */
    public YamlConfiguration getConfiguration() {
        return configuration;
    }
}
