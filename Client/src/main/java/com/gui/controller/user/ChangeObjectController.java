package com.gui.controller.user;

import com.SQLsupport.DBClass.TypeTaxes;
import com.SQLsupport.DBClass.UserObjects;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Vector;

import static com.gui.Constants.SHOW_OBJECTS_FXML;
import static com.gui.Constants.USER_MENU_FXML;

public class ChangeObjectController extends ShowObjectsController {
    @FXML
    protected Button goBackButton;

    @FXML
    protected Button changeObjectButton;

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

    private void changeObject() {
        int for_import = 0, for_export = 0;
        String type = (String) typeChoiceBox.getValue();
        switch ((String) ImportExportChoiceBox.getValue()) {
            case "На экспорт" -> {
                for_export = 1;
            }
            case "На импорт" -> {
                for_import = 1;
            }
        }
        String name = objectNameField.getText();
        String cost = objectCostField.getText();
    }

    @Override
    public void initMainScene() {
        typeChoiceBox.setItems(FXCollections.observableArrayList("Мех", "Украшения", "Алкоголь", "Табак", "Другое"));
        ImportExportChoiceBox.setItems(FXCollections.observableArrayList("На экспорт", "На импорт"));
        if (userObjectForEditing!=null) initFields();
        changeObjectButton.setOnMouseClicked(event -> {
            changeObject();
        });
        goBackButton.setOnMouseClicked(event -> {
            switchScene(event, SHOW_OBJECTS_FXML);
        });
    }

    public void initFields() {
        int object_id = userObjectForEditing.getId_object();
        client.sendData("select object");
        client.sendData(Integer.toString(object_id));
        Vector<UserObjects> userObjects = client.receiveObjects();
        objectNameField.setText(userObjects.get(0).getName());
        objectCostField.setText(Double.toString(userObjects.get(0).getCost()));
        typeChoiceBox.setValue(userObjects.get(0).getType());
        if (userObjects.get(0).getFor_export()==1) ImportExportChoiceBox.setValue("На экспорт");
        else ImportExportChoiceBox.setValue("На импорт");
    }
}
