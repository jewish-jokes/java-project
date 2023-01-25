package com.gui.controller.user;

import com.SQLsupport.DBClass.User;
import com.implementation.client.OwnClient;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import static com.gui.Constants.USER_MENU_FXML;

public class AddObjectController extends UserMenuController{

    @FXML
    protected Button goBackButton;

    @FXML
    protected Button addObjectButton;

    @FXML
    protected TextField objectNameField;

    @FXML
    protected TextField objectCostField;

    @FXML
    protected ChoiceBox typeChoiceBox;

    @FXML
    protected ChoiceBox ImportExportChoiceBox;

    @FXML
    protected Label addObjectLabel;

    @FXML
    void initialize() {
        this.initMainScene();
    }

    private void addObject() {
        User user = client.getUserProfile();
        client.sendData("add object");
        int for_export = 0, for_import = 0;
        if (ImportExportChoiceBox.getValue()=="На экспорт") for_export=1;
            else for_import=1;
        String newProfile =
                objectNameField.getText() + "@@@" +
                        objectCostField.getText() + "@@@" +
                        Integer.toString(for_export) + "@@@" +
                        Integer.toString(for_import) + "@@@" +
                        typeChoiceBox.getValue() + "@@@" +
                        client.getUserProfile().getId_user();
        //String newProfile =
                        //objectNameField.getText() + "@@@" +
                        //objectCostField.getText() + "@@@" +
                        //forExportField.getText() + "@@@" +
                        //forImportField.getText() + "@@@" +
                        //typeField.getText() + "@@@" +
                        //client.getUserProfile().getId_user();
        client.sendData(newProfile);
        if(client.receiveResult());
    }

    @Override
    public void initMainScene() {
        client = OwnClient.getInstance();
        typeChoiceBox.setItems(FXCollections.observableArrayList("Мех", "Украшения", "Алкоголь", "Табак", "Другое"));
        ImportExportChoiceBox.setItems(FXCollections.observableArrayList("На экспорт", "На импорт"));
        addObjectButton.setOnMouseClicked(event -> {
            addObject();
        });
        goBackButton.setOnMouseClicked(event -> {
            switchScene(event, USER_MENU_FXML);
        });
    }
}
