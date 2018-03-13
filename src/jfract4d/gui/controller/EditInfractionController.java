/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfract4d.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jfract4d.discord.infraction.DiscordInfraction;
import jfract4d.gui.util.DialogUtil;
import jfract4d.jfract.JFract;
import jfract4d.jfract.api.infraction.Infraction;
import jfract4d.jfract.api.infraction.InfractionType;
import jfract4d.jfract.api.user.User;
import jfract4d.jfract.helper.DateHelper;

/**
 * FXML Controller class
 *
 * @author Antoine Gagnon
 */
public class EditInfractionController implements Initializable {

    /**
     * The infraction that is being edited
     */
    private Infraction infraction;

    @FXML
    private TextField id;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnEditInfraction;
    @FXML
    private Label lblTime;
    @FXML
    private Label lblDate;
    @FXML
    private TextField second;
    @FXML
    private TextField minute;
    @FXML
    private TextField hour;
    @FXML
    private Button btnNewType;
    @FXML
    private Button btnNewUser;
    @FXML
    private DatePicker DatePicker;
    @FXML
    private ComboBox<InfractionType> type;
    @FXML
    private ComboBox<User> giver;
    @FXML
    private ComboBox<User> target;
    @FXML
    private CheckBox effective;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setInfraction(Infraction infraction) {
        this.infraction = infraction;
        loadInfraction();
        loadTypes();
        loadUsers();
    }

    private void loadInfraction() {
        id.setText(infraction.getID());
        target.setValue(infraction.getTarget());
        giver.setValue(infraction.getGiver());
        DatePicker.setValue(LocalDate.from(Instant.ofEpochMilli(infraction.getTime().getTime()).atZone(ZoneId.systemDefault())));

        Calendar cal = Calendar.getInstance();
        cal.setTime(infraction.getTime());
        hour.setText(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)));
        minute.setText(String.valueOf(cal.get(Calendar.MINUTE)));
        second.setText(String.valueOf(cal.get(Calendar.SECOND)));

        type.setValue(infraction.getType());

        effective.setSelected(infraction.isEffective());
    }

    private void loadUsers() {
        ObservableList<User> users;
        try {
            users = FXCollections.observableArrayList(JFract.getDataManager().getUserManager().getUsers());

            target.getItems().clear();
            target.getItems().addAll(users);

            giver.getItems().clear();
            giver.getItems().addAll(users);
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            DialogUtil.exceptionDialog("Error", "Error loading users", ex.getMessage(), ex).showAndWait();
        }
    }

    private void loadTypes() {
        ObservableList<InfractionType> types;
        try {
            types = FXCollections.observableArrayList(JFract.getDataManager().getInfractionManager().getInfractionTypes());
            type.getItems().clear();
            type.getItems().addAll(types);
        } catch (SQLException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            DialogUtil.exceptionDialog("Error", "Error loading types", ex.getMessage(), ex).showAndWait();
        }
    }

    @FXML
    private void BtnCancelClick(ActionEvent event) {
        ((Stage) btnCancel.getScene().getWindow()).close();
    }

    @FXML
    private void EditnfractionClick(ActionEvent event) {
        if (target.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Please select a target", ButtonType.OK).showAndWait();
            event.consume();
        } else if (giver.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Please select a giver", ButtonType.OK).showAndWait();
            event.consume();
        } else if (type.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Please select a type", ButtonType.OK).showAndWait();
            event.consume();
        }

        if (event.isConsumed()) {
            return;
        }

        Date time;

        if (DatePicker.getValue() == null || !(DateHelper.isValid24hour(hour.getText()) && DateHelper.isValidMinSec(minute.getText()) && DateHelper.isValidMinSec(second.getText()))) {
            new Alert(Alert.AlertType.ERROR, "Please enter a valid date", ButtonType.OK).showAndWait();
            event.consume();
            return;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(Date.from(DatePicker.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        cal.set(Calendar.HOUR_OF_DAY, Integer.valueOf(hour.getText()));
        cal.set(Calendar.MINUTE, Integer.valueOf(minute.getText()));
        cal.set(Calendar.SECOND, Integer.valueOf(second.getText()));
        cal.set(Calendar.MILLISECOND, 0);
        time = cal.getTime();

        try {
            JFract.getDataManager().getInfractionManager().updateInfraction(
                    infraction.getID(),
                    new DiscordInfraction(infraction.getID(), giver.getValue(), target.getValue(), time, type.getValue(), effective.isSelected())
            );
            ((Stage) btnCancel.getScene().getWindow()).close();
        } catch (SQLException ex) {
            Logger.getLogger(NewInfractionController.class.getName()).log(Level.SEVERE, null, ex);
            DialogUtil.exceptionDialog("Error", "Error updating infraction", ex.getMessage(), ex).showAndWait();
        }
    }

    @FXML
    private void NewTypeClick(ActionEvent event) {
        try {
            DialogUtil.makeStage(
                    getClass().getClassLoader(),
                    "NewInfractionType.fxml",
                    "resources/icons/jfract.png",
                    "New Infraction Type"
            ).showAndWait();

            loadTypes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void NewUserClick(ActionEvent event) {
        try {
            DialogUtil.makeStage(
                    getClass().getClassLoader(),
                    "NewUser.fxml",
                    "resources/icons/jfract.png",
                    "New User"
            ).showAndWait();

            loadTypes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
