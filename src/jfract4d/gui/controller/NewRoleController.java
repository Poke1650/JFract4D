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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import jfract4d.discord.exception.MalformedDiscordIDException;

import jfract4d.discord.user.DiscordRole;
import jfract4d.discord.util.FormatHelper;
import jfract4d.gui.FXMLDocumentController;
import jfract4d.gui.util.AlertUtil;

import jfract4d.gui.util.FormatUtil;
import jfract4d.jfract.JFract;

/**
 * FXML Controller class
 *
 * @author Antoine Gagnon
 */
public class NewRoleController implements Initializable {

    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    private TextField level;
    
    @FXML
    private Button cancelBtn;

    @FXML
    Button addBtn;

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
    private void OnBtnAddAction(ActionEvent event) {

        if (!FormatHelper.isValidID(id.getText())) {
            new Alert(Alert.AlertType.ERROR, "Please enter a valid ID", ButtonType.OK).showAndWait();
            event.consume();
        } else if (name.getText().length() == 0) {
            new Alert(Alert.AlertType.ERROR, "Please enter a name", ButtonType.OK).showAndWait();
            event.consume();
        } else if (level.getText().length() == 0) {
            new Alert(Alert.AlertType.ERROR, "Please enter a level", ButtonType.OK).showAndWait();
            event.consume();
        }

        if (event.isConsumed()) {
            return;
        }

        try {
            JFract.getDataManager().getUserManager().addRole(new DiscordRole(id.getText(), name.getText(), Integer.valueOf(level.getText())));
            ((Stage) cancelBtn.getScene().getWindow()).close();
        } catch (MalformedDiscordIDException e) {
            AlertUtil.exceptionDialog("Error adding role", "The ID is malformed", e.getMessage(), e).showAndWait();
        } catch (SQLException ex) {
            Logger.getLogger(NewRoleController.class.getName()).log(Level.SEVERE, null, ex);
             AlertUtil.exceptionDialog("Error", "Error adding role", ex.getCause().getMessage(), ex).showAndWait();
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id.setTextFormatter(FormatUtil.getIntegerTextFormatter());
        level.setTextFormatter(FormatUtil.getIntegerTextFormatter());
    }
}
