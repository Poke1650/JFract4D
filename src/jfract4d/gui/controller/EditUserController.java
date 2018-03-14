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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jfract4d.discord.exception.MalformedDiscordIDException;
import jfract4d.discord.user.DiscordUser;
import jfract4d.discord.util.FormatHelper;
import jfract4d.gui.util.DialogUtil;
import jfract4d.gui.util.FormatUtil;
import jfract4d.jfract.JFract;
import jfract4d.jfract.api.user.Role;
import jfract4d.jfract.api.user.User;

/**
 * FXML Controller class
 *
 * @author Antoine Gagnon
 */
public class EditUserController implements Initializable {

    /**
     * User being edited
     */
    private User user;
    
    @FXML
    private TextField id;
    @FXML
    private Button editUser;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button addRoleBtn;
    @FXML
    private ComboBox<Role> role;

    @FXML
    private void onIDKeyTyped(KeyEvent event) {
        if (id.getText().length() == FormatHelper.DISCORD_ID_LENGTH && FormatHelper.isNumeric(event.getCharacter())) {
            event.consume();
        }
    }

    @FXML
    private void handleCancelBtn(ActionEvent event) {
        ((Stage) cancelBtn.getScene().getWindow()).close();
    }

    @FXML
    private void onEditUserClick(ActionEvent event) {

        if (!FormatHelper.isValidID(id.getText())) {
            new Alert(Alert.AlertType.ERROR, "Please enter a valid ID", ButtonType.OK).showAndWait();
            event.consume();
        } else if (role.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Please select a role", ButtonType.OK).showAndWait();
            event.consume();
        }

        if (event.isConsumed()) {
            return;
        }

        try {
            JFract.getDataManager().getUserManager().updateUserRole(
                    user.getID(),
                    role.getValue()
            );
            ((Stage) cancelBtn.getScene().getWindow()).close();
        } catch (SQLException ex) {
            DialogUtil.exceptionDialog("Error", "Error adding user", ex.getMessage(), ex).showAndWait();
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setUser(User user) {
        this.user = user;
        loadUser();
    }
    
    public void loadUser() {
        id.setText(user.getID());
        role.setValue(user.getRole());
    }

    @FXML
    private void onBtnAddRoleAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("jfract4d/gui/view/NewRole.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("New Role");
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("resources/icons/jfract.png")));
            stage.showAndWait();

            role.getItems().clear();
            role.getItems().addAll(JFract.getDataManager().getUserManager().getRoles());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setTextFormatter(FormatUtil.getIntegerTextFormatter());

        try {
            role.getItems().addAll(JFract.getDataManager().getUserManager().getRoles());
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            DialogUtil.exceptionDialog("Error", "Error loading roles", ex.getMessage(), ex).showAndWait();
        }
    }    
}
