/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfract4d.gui;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jfract4d.gui.util.AlertUtil;

import jfract4d.jfract.JFract;
import jfract4d.jfract.api.user.User;

/**
 *
 * @author Antoine Gagnon
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button btnAddClick;

    @FXML
    private Button btnAddUser;

    @FXML
    private ListView<User> list;

    @FXML
    private void handleAddRoleClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("jfract4d/gui/view/NewRole.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("New Role");
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("resources/icons/jfract.png")));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddUserClick(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("jfract4d/gui/view/NewUser.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("New Role");
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("resources/icons/jfract.png")));
            stage.showAndWait();
            loadUserList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadUserList();
    }

    private void loadUserList() {
        ObservableList<User> data;
        try {
            data = FXCollections.observableArrayList(JFract.getDataManager().getUserManager().getUsers());
            list.setEditable(false);
            list.getItems().clear();
            list.getItems().addAll(data);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            AlertUtil.exceptionDialog("Error", "Error loading users", ex.getMessage(), ex).showAndWait();
        }
    }
}
