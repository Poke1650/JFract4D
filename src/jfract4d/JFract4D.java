package jfract4d;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfract4d.discord.user.DiscordRole;
import jfract4d.discord.user.DiscordUser;
import jfract4d.discord.exception.MalformedDiscordIDException;
import jfract4d.jfract.JFract;

/**
 *
 * @author Antoine Gagnon
 */
public class JFract4D extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("gui/FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            DiscordUser test = new DiscordUser("105317648602042368", new DiscordRole("Test", 1));
        } catch (MalformedDiscordIDException ex) {
            Logger.getLogger(JFract4D.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        try {
            JFract.init("jfract.properties");
            System.out.println("[ConfigManager, GET, \"jdbc.url\"]: " + JFract.getConfigManager().get("jdbc.url"));
            System.out.println("[DatabaseManager]Con URL: " + JFract.getDatabaseManager().getConnection().getMetaData().getURL());
            
        } catch (IOException ex) {
            Logger.getLogger(JFract4D.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JFract4D.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JFract4D.class.getName()).log(Level.SEVERE, null, ex);
        }
        launch(args);
    }

}
