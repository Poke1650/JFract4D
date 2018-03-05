package jfract4d;

import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfract4d.discord.data.DiscordDataManager;
import jfract4d.discord.exception.MalformedDiscordIDException;
import jfract4d.discord.user.DiscordUser;

import jfract4d.jfract.JFract;
import jfract4d.jfract.States;
import jfract4d.jfract.api.data.InfractionManager;
import jfract4d.jfract.api.data.UserManager;
import jfract4d.jfract.api.user.User;

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
            JFract.init("jfract.properties");
            
            JFract.registerDataManager(new DiscordDataManager());
            
            UserManager uManager = JFract.getDataManager().getUserManager();
            InfractionManager iManager = JFract.getDataManager().getInfractionManager();
            
            //User test = new DiscordUser("132517202308890624");
            
            //uManager.addUser(new DiscordUser("258005651816054784"));
            //uManager.addUser(test);
            
            //uManager.removeUser(test.getID());
            
            for (User u : uManager.getUsers()) {
                System.out.printf("Id: %s, Role: %s\n", u.getID(), u.getRole() != null ? u.getRole().getName():"NONE");
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(JFract4D.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JFract4D.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedDiscordIDException ex) {
            Logger.getLogger(JFract4D.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(JFract4D.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        launch(args);
    }

}
