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
import javafx.fxml.Initializable;

import javafx.scene.control.Button;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;

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
        try {
            String id = infractions.getSelectionModel().getSelectedItem().getID();
            if (DialogUtil.confirm("Do you really want to remove infraction ID " + id + " ?")) {
                JFract.getDataManager().getInfractionManager().removeInfraction(id);
                infractions.getItems().remove(infractions.getSelectionModel().getSelectedIndex());
            }

        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            DialogUtil.exceptionDialog("Error", "Error removing infraction", ex.getMessage(), ex).showAndWait();
        }
    }

    @FXML
    private Button btnEditInfraction;

    @FXML
    private void EditInfractionClick(ActionEvent event) {

    }

    @FXML
    private MenuItem btnManageTypes;

    @FXML
    private void ManageTypesClick(ActionEvent event) {

    }

    @FXML
    private MenuItem btnManageCategories;

    @FXML
    private void ManageCategoriesClick(ActionEvent event) {

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

    }

    @FXML
    private Button btnRemoveUser;

    @FXML
    private void RemoveUserClick(ActionEvent event) {
        try {
            String id = list.getSelectionModel().getSelectedItem().getID();
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
                            "ID: %s\nTarget: %s\nGiver: %s\nType: %s\nCategory: %s pts: %s\nTime: %s",
                            item.getID(),
                            item.getTarget().getID(),
                            item.getGiver().getID(),
                            item.getType().getName(),
                            item.getType().getCategory().getName(),
                            item.getType().getCategory().getPoints(),
                            DateHelper.toSQLDateTime(item.getTime())
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
            DialogUtil.exceptionDialog("Error", "Error loading users", ex.getMessage(), ex).showAndWait();
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
}
