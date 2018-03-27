package jfract4d;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import jfract4d.discord.data.DiscordDataManager;

import jfract4d.jfract.JFract;



/**
 * @author Antoine Gagnon
 */
public class JFract4D extends Application {
    
    public static final Logger LOGGER = Logger.getLogger("JFract4D");

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("gui/view/Main.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("JFract4D");

        stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("resources/icons/jfract.png")));

        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            
            JFract.init("jfract.properties");

            JFract.registerDataManager(new DiscordDataManager());
            
        } catch (IOException | ClassNotFoundException  ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }

        launch(args);
    }

}
