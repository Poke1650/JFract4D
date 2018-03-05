package jfract4d.jfract;

import java.io.IOException;
import jfract4d.jfract.api.data.DataManager;
import jfract4d.jfract.core.ConfigManager;
import jfract4d.jfract.core.DatabaseManager;

/**
 *
 * @author Antoine Gagnon
 */
public class JFract {

    private static States state = States.NOT_READY;

    private static ConfigManager configManager;

    private static DatabaseManager databaseManager;

    private static DataManager dataManager;

    public static void init(String configPath) throws IOException, ClassNotFoundException {
        state = States.INITIALISING;

        //Do some check here if first launch -> setup database
        configManager = new ConfigManager();
        configManager.load(configPath);

        databaseManager = new DatabaseManager();
        databaseManager.init();

        state = States.READY;
    }

    public static ConfigManager getConfigManager() {
        return configManager;
    }

    public static DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public static DataManager getDataManager() {
        return dataManager;
    }

    public static void registerDataManager(DataManager dm) throws Exception {

        if (state != States.READY) {
            throw new Exception("Attempted to register DataManager before initialising JFraft, call JFract.init first! Current state = " + state);
        }
        dataManager = dm;
    }

}
