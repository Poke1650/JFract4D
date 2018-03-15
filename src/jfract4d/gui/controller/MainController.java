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
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Button;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import jfract4d.gui.util.DialogUtil;

import jfract4d.jfract.JFract;
import jfract4d.jfract.api.infraction.Infraction;
import jfract4d.jfract.api.user.User;
import jfract4d.jfract.helper.DateHelper;

/**
 *
 * @author Antoine Gagnon
 */
public class MainController implements Initializable {

    /* Infraction Tab */
    @FXML
    private ListView<Infraction> infractions;

    @FXML
    private Button btnAddInfraction;

    @FXML
    private void AddInfractionClick(ActionEvent event) {
        try {
            DialogUtil.makeStage(
                    getClass().getClassLoader(),
                    "NewInfraction.fxml",
                    "resources/icons/jfract.png",
                    "New Infraction"
            ).showAndWait();

            loadInfractions();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button btnRemoveInfraction;

    @FXML
    private void RemoveInfractionClick(ActionEvent event) {
        if (infractions.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        try {
            String id = infractions.getSelectionModel().getSelectedItem().getID();
            if (DialogUtil.confirm("Do you really want to remove infraction ID " + id + " ?")) {
                JFract.getDataManager().getInfractionManager().removeInfraction(id);
                infractions.getItems().remove(infractions.getSelectionModel().getSelectedIndex());
            }

        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            DialogUtil.exceptionDialog("JFact4D - Error", "Error removing infraction", ex.getMessage(), ex).showAndWait();
        }
    }

    @FXML
    private Button btnEditInfraction;

    @FXML
    private void EditInfractionClick(ActionEvent event) {

        try {
            Infraction infract = infractions.getSelectionModel().getSelectedItem();

            if (infract == null) {
                new Alert(AlertType.ERROR, "Please select an infraction to edit").showAndWait();
                return;
            }

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("jfract4d/gui/view/EditInfraction.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit Infraction");
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("resources/icons/jfract.png")));
            EditInfractionController controller = fxmlLoader.getController();
            controller.setInfraction(infract);
            stage.showAndWait();

            loadInfractions();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void InfractionmouseClickEvent(MouseEvent event) {
        if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
            btnEditInfraction.fire();
        }
    }

    @FXML
    private Button btnManageTypeCat;

    @FXML
    private void ManageTypeCatClick(ActionEvent event) {
        try {
            DialogUtil.makeStage(
                    getClass().getClassLoader(),
                    "ManageInfractionTC.fxml",
                    "resources/icons/jfract.png",
                    "Manage Infractions"
            ).showAndWait();

            loadUserList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /* Users Tab */
    @FXML
    private Button btnAddUser;

    @FXML
    private void AddUserClick(ActionEvent event) {
        try {
            DialogUtil.makeStage(
                    getClass().getClassLoader(),
                    "NewUser.fxml",
                    "resources/icons/jfract.png",
                    "New User"
            ).showAndWait();

            loadUserList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button btnEditUser;

    @FXML
    private void EditUserClick(ActionEvent event) {
        try {
            User user = list.getSelectionModel().getSelectedItem();

            if (user == null) {
                new Alert(AlertType.ERROR, "Please select a user to edit").showAndWait();
                return;
            }

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("jfract4d/gui/view/EditUser.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit user");
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("resources/icons/jfract.png")));
            EditUserController controller = fxmlLoader.getController();
            controller.setUser(user);
            stage.showAndWait();

            loadUserList();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private Button btnRemoveUser;

    @FXML
    private void RemoveUserClick(ActionEvent event) {
        User user = list.getSelectionModel().getSelectedItem();

        if (user == null) {
            new Alert(AlertType.ERROR, "Please select a user to remove").showAndWait();
            return;
        }
        try {
            String id = user.getID();
            if (DialogUtil.confirm("Do you really want to remove user ID " + id + " ?")) {
                JFract.getDataManager().getUserManager().removeUser(id);
                list.getItems().remove(list.getSelectionModel().getSelectedIndex());
            }

        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            DialogUtil.exceptionDialog("Error", "Error removing user", ex.getMessage(), ex).showAndWait();
        }
    }

    @FXML
    private Button btnManageRoles;

    @FXML
    private void ManageRolesClick(ActionEvent event) {
        try {
            DialogUtil.makeStage(
                    getClass().getClassLoader(),
                    "ManageRoles.fxml",
                    "resources/icons/jfract.png",
                    "Manage Roles"
            ).showAndWait();

            loadUserList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private ListView<User> list;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setCellFactories();

        loadUserList();
        loadInfractions();

    }

    private void setCellFactories() {
        infractions.setCellFactory(param -> new ListCell<Infraction>() {
            @Override
            protected void updateItem(Infraction item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format(
                            "ID: %s\nTarget: %s\nGiver: %s\nType: %s\nCategory: %s pts: %s\nTime: %s\nEffective: %s",
                            item.getID(),
                            item.getTarget().getID(),
                            item.getGiver().getID(),
                            item.getType().getName(),
                            item.getType().getCategory().getName(),
                            item.getType().getCategory().getPoints(),
                            DateHelper.toSQLDateTime(item.getTime()),
                            item.isEffective() ? "Yes" : "No"
                    )
                    );
                }
            }
        });

        list.setCellFactory(param -> new ListCell<User>() {
            @Override
            protected void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("ID: %s\nRole: %s", item.getID(), item.getRole().getName()));
                }
            }
        });
    }

    private void loadInfractions() {
        ObservableList<Infraction> data;
        try {
            data = FXCollections.observableArrayList(JFract.getDataManager().getInfractionManager().getAllInfractions());
            infractions.setEditable(false);
            infractions.getItems().clear();
            infractions.getItems().addAll(data);
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            DialogUtil.exceptionDialog("Error", "Error loading infractions", ex.getMessage(), ex).showAndWait();
        }
    }

    private void loadUserList() {
        ObservableList<User> data;
        try {
            data = FXCollections.observableArrayList(JFract.getDataManager().getUserManager().getUsers());
            list.setEditable(false);
            list.getItems().clear();
            list.getItems().addAll(data);
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            DialogUtil.exceptionDialog("Error", "Error loading users", ex.getMessage(), ex).showAndWait();
        }
    }

    @FXML
    private void userListClicked(MouseEvent event) {
        if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
            btnEditUser.fire();
        }
    }
}
