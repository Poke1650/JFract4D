package jfract4d.jfract;

import java.io.IOException;
import jfract4d.jfract.core.ConfigManager;
import jfract4d.jfract.core.DatabaseManager;

/**
 *
 * @author Antoine Gagnon
 */
public class IInfractionManager {
    
    public static void init(String configPath) throws IOException, ClassNotFoundException {
        ConfigManager.load(configPath);
        DatabaseManager.init();
    }
}
