package com.gui.controller.user;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import static com.gui.Constants.USER_MENU_FXML;

public class CountTaxesController extends UserMenuController{
    private boolean isEdit;

    @FXML
    private Label objectIDLabel;

    @FXML
    private Label resultLabel;

    @FXML
    private Button countTaxesButton;

    @FXML
    private Button goBackButton;

    @FXML
    private TextField resultField;

    @FXML
    private TextField objectIDField;

    @FXML
    void initialize() {
        isEdit = false;
        this.initMainScene();
        this.addActions();
    }

    public void addActions() {
        objectIDField.setOnMouseClicked(event -> {
            isEdit = true;
        });
    }

    private void countTaxes() {
        if (!isEdit)
            return;
        client.sendData("edit cost taxes");
    }

    @Override
    public void initMainScene() {
        countTaxesButton.setOnMouseClicked(event -> {
            countTaxes();
        });
        goBackButton.setOnMouseClicked(event -> {
            switchScene(event, USER_MENU_FXML);
        });
    }
}
