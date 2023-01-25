package com.gui.controller.admin;

import com.SQLsupport.DBClass.User;
import com.implementation.client.OwnClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Vector;

import static com.gui.Constants.ADMIN_MENU_FXML;
import static com.gui.Constants.CHANGE_ALL_TAXES_FXML;

public class DeleteUserController extends AdminMenuController{
    private boolean isEdit;
    private String admin, userStr;

    @FXML
    private Label userIdLabel;

    @FXML
    private Button deleteButton;

    @FXML
    private Button goBackButton;

    @FXML
    private TextField userIdField;

    @FXML
    void initialize() {
        isEdit = false;
        this.initMainScene();
        this.addActions();
    }

    public void addActions() {
        userIdField.setOnMouseClicked(event -> {
            isEdit = true;
        });
    }

    private void deleteUser() {
        try{
            //if (!isEdit)
              //  return;
            client.sendData("delete user");
            client.sendData(userIdField.getText());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initMainScene() {
        client = OwnClient.getInstance();
        deleteButton.setOnMouseClicked(event -> {
            deleteUser();
        });
        goBackButton.setOnMouseClicked(event -> {
            switchScene(event, ADMIN_MENU_FXML);
        });
    }
}
