package jfract4d;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfract4d.jfract.IInfractionManager;

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
            IInfractionManager.init("jfract.properties");
        } catch (IOException ex) {
            Logger.getLogger(JFract4D.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JFract4D.class.getName()).log(Level.SEVERE, null, ex);
        }
        launch(args);
    }

}
