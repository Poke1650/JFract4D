package jfract4d.jfract;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jfract4d.jfract.api.data.DataManager;
import jfract4d.jfract.core.ConfigManager;
import jfract4d.jfract.core.DatabaseManager;

/**
 * Interface to the JFract API
 * 
 * @author Antoine Gagnon
 */
public class JFract {

    /**
     * Logger for the JFract API
     */
    public static final Logger LOGGER = Logger.getLogger("JFract");
    
    /**
     * Current state of the API
     */
    private static States state = States.NOT_READY;

    /**
     * ConfigManager for the API
     */
    private static ConfigManager configManager;

    /**
     * Database manager for the API
     */
    private static DatabaseManager databaseManager;

    /**
     * Data manager registered to the API
     */
    private static DataManager dataManager;

    /**
     * Initialize the components of the API
     * You MUST call that before using anything else
     * @param configPath
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static void init(String configPath) throws IOException, ClassNotFoundException {
        JFract.LOGGER.log(Level.INFO, "Initialising JFract with config at {0}", configPath);
        state = States.INITIALISING;

        configManager = new ConfigManager();
        configManager.load(configPath);
        
        databaseManager = new DatabaseManager();
        databaseManager.init();

        state = States.READY;
    }

    /**
     * 
     * @return the configuration manager
     */
    public static ConfigManager getConfigManager() {
        if(state == States.NOT_READY) {
            throw new RuntimeException("JFract is not ready! Be sure to call JFract.init first. Current state: " + state);
        }
        return configManager;
    }

    /**
     * 
     * @return the database manager
     */
    public static DatabaseManager getDatabaseManager() {
        if(state == States.NOT_READY) {
            throw new RuntimeException("Attempt to call JFract.getDatabaseManager() when not ready. Be sure to call JFract.init first. Current state: " + state);
        }
        return databaseManager;
    }

    /**
     * 
     * @return the data manager
     */
    public static DataManager getDataManager() {
        if(state != States.READY) {
            throw new RuntimeException("Attempt to call JFract.getDataManager() when not ready. Be sure to call JFract.init first. Current state: " + state);
        }
        return dataManager;
    }

    /**
     * Registers a datamanager to be used by JFract
     * @param dm the datamanager
     */
    public static void registerDataManager(DataManager dm) {
        if (state != States.READY) {
            throw new RuntimeException("Attempted to register DataManager when not ready. Be sure to call JFract.init first. Current state: " + state);
        }
        dataManager = dm;
    }

}
