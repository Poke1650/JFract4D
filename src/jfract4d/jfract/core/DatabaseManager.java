package jfract4d.jfract.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;

public class DatabaseManager {

    private static String url;
    private static String username;
    private static String password;

    /**
     * Initializes the data source.
     *
     * @param configPath the name of the property file that contains the
     * database driver, URL, username, and password
     */
    public static void init(String driver, String url, String username, String password) throws IOException, ClassNotFoundException {
        DatabaseManager.url = url;
        if (username == null) {
            DatabaseManager.username = "";
        } else {
            DatabaseManager.username = username;
        }
        if (password == null) {
            DatabaseManager.password = "";
        } else {
            DatabaseManager.password = password;
        }
        if (driver != null) {
            Class.forName(driver);
        }
    }
    
    /**
     * Initializes the data source using the ConfigManager
     */
    public static void init() throws IOException, ClassNotFoundException {
        DatabaseManager.init(ConfigManager.get("jdbc.driver"), ConfigManager.get("jdbc.url"), ConfigManager.get("jdbc.username"), ConfigManager.get("jdbc.password"));
    }

    /**
     * Gets a connection to the database.
     *
     * @return the database connection
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
