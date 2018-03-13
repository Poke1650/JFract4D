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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jfract4d.gui.util.DialogUtil;
import jfract4d.jfract.JFract;
import jfract4d.jfract.api.infraction.Infraction;

import jfract4d.jfract.api.infraction.InfractionCategory;
import jfract4d.jfract.api.infraction.InfractionType;

/**
 * FXML Controller class
 *
 * @author Antoine Gagnon
 */
public class ManageInfractionController implements Initializable {

    @FXML
    private ListView<InfractionType> types;
    @FXML
    private Button btnAddType;
    @FXML
    private Button btnEditIType;
    @FXML
    private Button btnRemoveType;
    @FXML
    private ListView<InfractionCategory> categories;
    @FXML
    private Button btnAddCat;
    @FXML
    private Button btnEditCat;
    @FXML
    private Button btnRemoveCat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        types.setEditable(false);
        categories.setEditable(false);

        loadCategories();
        loadTypes();
    }

    private void loadTypes() {
        ObservableList<InfractionType> data;
        try {
            data = FXCollections.observableArrayList(JFract.getDataManager().getInfractionManager().getInfractionTypes());

            types.getItems().clear();
            types.getItems().addAll(data);
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            DialogUtil.exceptionDialog("Error", "Error loading types", ex.getMessage(), ex).showAndWait();
        }
    }

    private void loadCategories() {
        ObservableList<InfractionCategory> data;
        try {
            data = FXCollections.observableArrayList(JFract.getDataManager().getInfractionManager().getInfractionCategories());

            categories.getItems().clear();
            categories.getItems().addAll(data);
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            DialogUtil.exceptionDialog("Error", "Error loading infractions", ex.getMessage(), ex).showAndWait();
        }
    }

    @FXML
    private void InfractionmouseClickEvent(MouseEvent event) {
        if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
            btnEditIType.fire();
        }
    }

    @FXML
    private void AddTypeClick(ActionEvent event) {
        try {
            DialogUtil.makeStage(
                    getClass().getClassLoader(),
                    "NewInfractionType.fxml",
                    "resources/icons/jfract.png",
                    "New Type"
            ).showAndWait();

            loadTypes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void EditTypeClick(ActionEvent event) {

    }

    @FXML
    private void RemoveTypeClick(ActionEvent event) {
        InfractionType type = types.getSelectionModel().getSelectedItem();

        if (type == null) {
            new Alert(Alert.AlertType.ERROR, "Please select a type to remove").showAndWait();
            return;
        }
        try {
            int id = type.getID();
            if (DialogUtil.confirm("Do you really want to remove type " + type.getName() + " ?")) {
                JFract.getDataManager().getInfractionManager().removeInfractionType(id);
                types.getItems().remove(types.getSelectionModel().getSelectedIndex());
            }

        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            DialogUtil.exceptionDialog("Error", "Error removing type", ex.getMessage(), ex).showAndWait();
        }
    }

    @FXML
    private void AddCatClick(ActionEvent event) {
        try {
            DialogUtil.makeStage(
                    getClass().getClassLoader(),
                    "NewInfractionCategory.fxml",
                    "resources/icons/jfract.png",
                    "New Type"
            ).showAndWait();

            loadCategories();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void EditCatClick(ActionEvent event) {
        try {
            InfractionCategory cat = categories.getSelectionModel().getSelectedItem();

            if ( categories == null) {
                new Alert(Alert.AlertType.ERROR, "Please select a category to edit").showAndWait();
                return;
            }

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("jfract4d/gui/view/EditInfractionCategory.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit Category");
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("resources/icons/jfract.png")));
            EditInfractionCategoryController controller = fxmlLoader.getController();
            controller.setInfractionCategory(cat);
            stage.showAndWait();

            //Reload data
            loadCategories();
            loadTypes();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void RemoveCatClick(ActionEvent event) {
        InfractionCategory cat = categories.getSelectionModel().getSelectedItem();

        if (cat == null) {
            new Alert(Alert.AlertType.ERROR, "Please select a category to remove").showAndWait();
            return;
        }
        try {

            if (DialogUtil.confirm("Do you really want to remove category " + cat.getName() + " ?")) {
                JFract.getDataManager().getInfractionManager().removeInfractionCategory(cat.getName());
                categories.getItems().remove(categories.getSelectionModel().getSelectedIndex());
            }

        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            DialogUtil.exceptionDialog("Error", "Error removing category", ex.getMessage(), ex).showAndWait();
        }
    }

    @FXML
    private void categoriesClick(MouseEvent event) {
        if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
            btnEditCat.fire();
        }
    }

}
