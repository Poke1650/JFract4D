/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfract4d.gui.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jfract4d.jfract.api.infraction.impl.InfractionCategoryImpl;
import jfract4d.gui.util.DialogUtil;
import jfract4d.jfract.JFract;
import jfract4d.jfract.api.infraction.InfractionCategory;

/**
 * FXML Controller class
 *
 * @author Antoine Gagnon
 */
public class EditInfractionCategoryController implements Initializable {

    /**
     * The category that is being edited
     */
    private InfractionCategory cat;

    @FXML
    private Button edit;
    @FXML
    private TextField name;
    @FXML
    private TextField pts;
    @FXML
    private Button cancelBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void onEdit(ActionEvent event) {
        if (name.getText().length() == 0) {
            new Alert(Alert.AlertType.ERROR, "Please enter a name", ButtonType.OK).showAndWait();
            event.consume();
        } else if (pts.getText().length() == 0) {
            new Alert(Alert.AlertType.ERROR, "Please a point value", ButtonType.OK).showAndWait();
            event.consume();
        }

        if (event.isConsumed()) {
            return;
        }

        try {
            JFract.getDataManager().getInfractionManager().updateInfractionCategory(cat.getName(), 
                    new InfractionCategoryImpl(name.getText(), Integer.valueOf(pts.getText()))
            );
            ((Stage) cancelBtn.getScene().getWindow()).close();
        } catch (SQLException ex) {
            Logger.getLogger(NewRoleController.class.getName()).log(Level.SEVERE, null, ex);
            DialogUtil.exceptionDialog("Error", "Error adding category", ex.getMessage(), ex).showAndWait();
        }
    }

    @FXML
    private void CancelClick(ActionEvent event) {
        ((Stage) cancelBtn.getScene().getWindow()).close();
    }

    private void loadCat() {
        name.setText(cat.getName());
        pts.setText(String.valueOf(cat.getPoints()));
    }

    public void setInfractionCategory(InfractionCategory cat) {
        this.cat = cat;
        loadCat();
    }

}
