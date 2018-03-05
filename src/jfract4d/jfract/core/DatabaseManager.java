package jfract4d.jfract.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import jfract4d.jfract.JFract;

public class DatabaseManager {

    private String url;
    private String username;
    private String password;

    /**
     * Initializes the data source.
     *
     * @param configPath the name of the property file that contains the
     * database driver, URL, username, and password
     */
    public void init(String driver, String url, String username, String password) throws IOException, ClassNotFoundException {
        this.url = url;
        if (username == null) {
            this.username = "";
        } else {
            this.username = username;
        }
        if (password == null) {
            this.password = "";
        } else {
            this.password = password;
        }
        if (driver != null) {
            Class.forName(driver);
        }
    }

    /**
     * Initializes the data source using the ConfigManager
     */
    public void init() throws IOException, ClassNotFoundException {

        ConfigManager cf = JFract.getConfigManager();

        this.init(cf.get("jdbc.driver"), cf.get("jdbc.url"), cf.get("jdbc.username"), cf.get("jdbc.password"));
    }

    /**
     * Gets a connection to the database.
     *
     * @return the database connection
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
