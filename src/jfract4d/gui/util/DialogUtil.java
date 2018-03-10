package jfract4d.gui.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Antoine Gagnon
 */
public class DialogUtil {

    public static Alert exceptionDialog(String title, String header, String content, Exception ex) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("Stacktrace:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        return alert;
    }
    
    public static Alert yesNoDialog(String title, String message, String sub) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(message);
        alert.setContentText(sub);
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        return alert;
    }
    
    public static Stage makeStage(ClassLoader classLoader, String fxmlDocument, String icon, String title) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(classLoader.getResource("jfract4d/gui/view/" + fxmlDocument));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(title);
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image(classLoader.getResourceAsStream("resources/icons/jfract.png")));
            return stage;
    }
    
    public static boolean confirm(String message) {
        return new Alert(Alert.AlertType.CONFIRMATION, message).showAndWait().get() == ButtonType.OK;
    }
}
