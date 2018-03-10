/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfract4d.gui.controller;

import java.io.IOException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jfract4d.discord.infraction.DiscordInfractionType;
import jfract4d.gui.util.DialogUtil;
import jfract4d.jfract.JFract;
import jfract4d.jfract.api.infraction.InfractionCategory;

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
    private ComboBox<InfractionCategory> category;
    @FXML
    private Button newCat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadCategories();
        category.setCellFactory(param -> new ListCell<InfractionCategory>() {
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

    private void loadCategories() {
        try {
            category.getItems().addAll(JFract.getDataManager().getInfractionManager().getInfractionCategories());
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            DialogUtil.exceptionDialog("Error", "Error loading categories", ex.getMessage(), ex).showAndWait();
        }
    }

    @FXML
    private void AddClick(ActionEvent event) {
        if (category.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Please select a category", ButtonType.OK).showAndWait();
            event.consume();
        } else if (name.getText().length() == 0) {
            new Alert(Alert.AlertType.ERROR, "Please enter a name", ButtonType.OK).showAndWait();
            event.consume();
        } else if (desc.getText().length() == 0) {
            new Alert(Alert.AlertType.ERROR, "Please enter a description", ButtonType.OK).showAndWait();
            event.consume();
        }

        if (event.isConsumed()) {
            return;
        }

        try {
            JFract.getDataManager().getInfractionManager().addInfractionType(new DiscordInfractionType(name.getText(), desc.getText(), category.getValue()));
            ((Stage) btnCancel.getScene().getWindow()).close();
        } catch (SQLException ex) {
            DialogUtil.exceptionDialog("Error", "Error adding category", ex.getMessage(), ex).showAndWait();
            Logger.getLogger(NewRoleController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void CancelClick(ActionEvent event) {
        ((Stage) btnCancel.getScene().getWindow()).close();
    }

    @FXML
    private void NewCatClick(ActionEvent event) {
        try {
            DialogUtil.makeStage(
                    getClass().getClassLoader(),
                    "NewInfractionCategory.fxml",
                    "resources/icons/jfract.png",
                    "New Infraction Category"
            ).showAndWait();
            loadCategories();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
