package jfract4d.jfract;

import java.io.IOException;
import jfract4d.jfract.core.ConfigManager;
import jfract4d.jfract.core.DatabaseManager;

/**
 *
 * @author Antoine Gagnon
 */
public class JFract {
    
    boolean init = false;
    
    private static ConfigManager configManager;
    
    private static DatabaseManager databaseManager;
    
    public static void init(String configPath) throws IOException, ClassNotFoundException {
        configManager = new ConfigManager();
        configManager.load(configPath);
        
        databaseManager = new DatabaseManager();
        databaseManager.init();
    }
    
    public static ConfigManager getConfigManager() {
        return configManager;
    }
    
    public static DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
    
}
