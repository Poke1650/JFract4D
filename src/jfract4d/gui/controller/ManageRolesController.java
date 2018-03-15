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
import jfract4d.jfract.api.user.Role;
import jfract4d.jfract.api.user.User;

/**
 * FXML Controller class
 *
 * @author Antoine Gagnon
 */
public class ManageRolesController implements Initializable {

    @FXML
    private ListView<Role> roles;
    @FXML
    private Button btnAddRole;
    @FXML
    private Button btnEditRole;
    @FXML
    private Button btnRemoveRole;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadRoles();
    }

    private void loadRoles() {
        roles.getItems().clear();
        try {
            roles.getItems().addAll(JFract.getDataManager().getUserManager().getRoles());
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            DialogUtil.exceptionDialog("Error", "Error loading roles", ex.getMessage(), ex).showAndWait();
        }
    }

    @FXML
    private void rolesClicked(MouseEvent event) {
        if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
            btnEditRole.fire();
        }
    }

    @FXML
    private void addRoleClick(ActionEvent event) {
        try {
            DialogUtil.makeStage(
                    getClass().getClassLoader(),
                    "NewRole.fxml",
                    "resources/icons/jfract.png",
                    "Manage Infractions"
            ).showAndWait();
            loadRoles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void editRoleClick(ActionEvent event) {
        try {
            Role role = roles.getSelectionModel().getSelectedItem();

            if (role == null) {
                new Alert(Alert.AlertType.ERROR, "Please select a role to edit").showAndWait();
                return;
            }

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("jfract4d/gui/view/EditRole.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit role");
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("resources/icons/jfract.png")));
            EditRoleController controller = fxmlLoader.getController();
            controller.setRole(role);
            stage.showAndWait();

            loadRoles();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void removeRoleClick(ActionEvent event) {
        Role role = roles.getSelectionModel().getSelectedItem();

        if (role == null) {
            new Alert(Alert.AlertType.ERROR, "Please select a role to remove").showAndWait();
            return;
        }
        try {
            String id = role.getID();
            if (DialogUtil.confirm("Do you really want to remove role ID " + id + " ?")) {
                JFract.getDataManager().getUserManager().removeRole(id);
                roles.getItems().remove(roles.getSelectionModel().getSelectedIndex());
            }

        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            DialogUtil.exceptionDialog("Error", "Error removing role", ex.getMessage(), ex).showAndWait();
        }
    }

}
