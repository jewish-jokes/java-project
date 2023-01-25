package com.gui.controller.admin;

import com.SQLsupport.DBClass.CostTaxes;
import com.SQLsupport.DBClass.ImportExportTaxes;
import com.SQLsupport.DBClass.TypeTaxes;
import com.SQLsupport.DBClass.User;
import com.implementation.client.OwnClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Vector;

import static com.gui.Constants.CHANGE_ALL_TAXES_FXML;

public class ChangeAllExportImportTaxesController extends AdminMenuController{
    private boolean isEdit;
    private String admin, userStr;

    @FXML
    private Label forExportLabel;

    @FXML
    private Label forImportLabel;

    @FXML
    private Button saveChangesButton;

    @FXML
    private Button goBackButton;

    @FXML
    private TextField forExportField;

    @FXML
    private TextField forImportField;

    @FXML
    void initialize() {
        isEdit = false;
        this.initMainScene();
        this.initFields();
        this.addActions();
    }

    public void addActions() {
        forExportField.setOnMouseClicked(event -> {
            isEdit = true;
        });
        forImportField.setOnMouseClicked(event -> {
            isEdit = true;
        });
    }

    private void editExportImportTaxes() {
        client.sendData("edit export import taxes");
        String newProfile =
                forExportField.getText() + "@@@" +
                        forImportField.getText();
        client.sendData(newProfile);
        if (client.receiveResult()) {
            client.sendData("select all export import taxes");
            client.sendData("");
            Vector<ImportExportTaxes> importExportTaxes = client.receiveImportExportTaxes();
            if (importExportTaxes != null) {
                initFields();
            }
        }
    }

    @Override
    public void initMainScene() {
        client = OwnClient.getInstance();
        saveChangesButton.setOnMouseClicked(event -> {
            editExportImportTaxes();
        });
        goBackButton.setOnMouseClicked(event -> {
            switchScene(event, CHANGE_ALL_TAXES_FXML);
        });
    }


    public void initFields() {
        client.sendData("select all export import taxes");
        client.sendData("");
        Vector<ImportExportTaxes> importExportTaxes = client.receiveImportExportTaxes();
        forExportField.setText(Double.toString(importExportTaxes.get(0).getExport_taxes()));
        forImportField.setText(Double.toString(importExportTaxes.get(0).getImport_taxes()));
    }
}
