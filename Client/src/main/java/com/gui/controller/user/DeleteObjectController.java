package com.gui.controller.user;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import static com.gui.Constants.USER_MENU_FXML;

public class DeleteObjectController extends UserMenuController{
    private boolean isEdit;
    private String admin, userStr;

    @FXML
    private Label objectIdLabel;

    @FXML
    private Button deleteButton;

    @FXML
    private Button goBackButton;

    @FXML
    private TextField objectIdField;

    @FXML
    void initialize() {
        isEdit = false;
        this.initMainScene();
        this.addActions();
    }

    public void addActions() {
        objectIdField.setOnMouseClicked(event -> {
            isEdit = true;
        });
    }

    private void deleteObject() {
        if (!isEdit)
            return;
        client.sendData("edit cost taxes");
    }

    @Override
    public void initMainScene() {
        deleteButton.setOnMouseClicked(event -> {
            deleteObject();
        });
        goBackButton.setOnMouseClicked(event -> {
            switchScene(event, USER_MENU_FXML);
        });
    }
}
