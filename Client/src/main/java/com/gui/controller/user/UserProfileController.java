package com.gui.controller.user;

import com.SQLsupport.DBClass.User;
import com.implementation.client.OwnClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Vector;

import static com.gui.Constants.USER_MENU_FXML;

public class UserProfileController extends UserMenuController{
    private boolean isEdit;

    @FXML
    private Label loginLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Button changeProfileButton;

    @FXML
    private Button goBackButton;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    void initialize() {
        isEdit = false;
        this.initMainScene();
        this.initFields();
        this.addActions();
    }

    public void addActions() {
        loginField.setOnMouseClicked(event -> {
            isEdit = true;
        });
        passwordField.setOnMouseClicked(event -> {
            isEdit = true;
        });
    }

    private void editUserProfile() {
        //if (!isEdit)
          //  return;
        client.sendData("edit user");
        String newProfile =
                client.getUserProfile().getId_user() + "@@@" +
                        loginField.getText() + "@@@" +
                        passwordField.getText();
        client.sendData(newProfile);
        if (client.receiveResult()) {
            client.sendData("select user");
            client.sendData(loginField.getText() + " " + passwordField.getText());
            Vector<User> users = client.receiveUsers();
            if (users != null) {
                client.setUserProfile(users.elementAt(0));
                initFields();
            }
        }
    }

    @Override
    public void initMainScene() {
        client= OwnClient.getInstance();
        changeProfileButton.setOnMouseClicked(event -> {
            editUserProfile();
        });
        goBackButton.setOnMouseClicked(event -> {
            switchScene(event, USER_MENU_FXML);
        });
    }


    public void initFields() {
        User user = client.getUserProfile();
        loginField.setText(user.getLogin());
        passwordField.setText(user.getPassword());
    }
}
