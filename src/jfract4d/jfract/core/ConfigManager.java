package jfract4d.jfract.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manager for the API configuration file
 *
 * @author Antoine Gagnon
 */
public class ConfigManager {

    /**
     * Configuration data
     */
    private static Properties properties = new Properties();

    /**
     * Path to the configuration file
     */
    private static String path;

    /**
     * Reads and parse a configuration file
     *
     * @param configPath the config path to load
     * @throws FileNotFoundException if the file at configPath does not exist
     * @throws IOException if there's an error white reading the properties file
     */
    public static void load(String configPath) throws FileNotFoundException, IOException {
        FileInputStream in = new FileInputStream(configPath);
        path = configPath;
        properties.load(in);
    }

    /**
     * @param key
     * @return value in config for the given key
     */
    public static String get(String key) {
        return properties.getProperty(key);
    }

    /**
     * Set the value in config for the given key The config is then written to
     * disk see
     * {@link jfract4d.jfract.core.ConfigManager#set(java.lang.String, java.lang.String, boolean)}
     * for setting without writing to disk
     *
     * @param key
     * @param value
     */
    public static void set(String key, String value) throws IOException {
        properties.setProperty(key, value);
        ConfigManager.flush();
    }

    /**
     * Set the value in config for the given key
     *
     * @param key
     * @param value
     * @param writeToDisk indicate if the config file should be written to disk
     * @throws java.io.IOException
     */
    public static void set(String key, String value, boolean writeToDisk) throws IOException {
        properties.setProperty(key, value);
        if (writeToDisk) {
            ConfigManager.flush();
        }
    }

    /**
     * Writes the config file to disk
     *
     * @throws java.io.IOException
     */
    public static void flush() throws IOException {
        File f = new File(path);

        OutputStream out;
        try {
            out = new FileOutputStream(f);
            properties.store(out, "Changed " + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConfigManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
