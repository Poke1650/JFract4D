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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import jfract4d.gui.util.DialogUtil;
import jfract4d.jfract.JFract;
import jfract4d.jfract.api.infraction.InfractionCategory;
import jfract4d.jfract.api.infraction.InfractionType;

/**
 * FXML Controller class
 *
 * @author Antoine Gagnon
 */
public class NewInfractionTypeController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextArea desc;
    @FXML
    private Button btnAddType;
    @FXML
    private Button btnCancel;
    @FXML
    private ComboBox<InfractionCategory> types;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            types.getItems().addAll(JFract.getDataManager().getInfractionManager().getInfractionCategories());
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            DialogUtil.exceptionDialog("Error", "Error loading roles", ex.getMessage(), ex).showAndWait();
        }
        
        types.setCellFactory(param -> new ListCell<InfractionCategory>() {
            @Override
            protected void updateItem(InfractionCategory item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%s | %s pts", item.getName(), item.getPoints()));
                }
            }
        });
    }    

    @FXML
    private void AddClick(ActionEvent event) {
    }

    @FXML
    private void CancelClick(ActionEvent event) {
    }
    
}
