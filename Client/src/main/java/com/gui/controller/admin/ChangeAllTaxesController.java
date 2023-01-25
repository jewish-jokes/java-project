package com.gui.controller.admin;

import com.gui.MainMenuGUI;
import com.implementation.client.OwnClient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

import static com.gui.Constants.*;
import static com.gui.Constants.CHANGE_ALL_TAXES_FXML;

public class ChangeAllTaxesController extends AdminMenuController{
    protected OwnClient client;
    protected Parent root;
    protected Stage stage;
    protected Scene scene;

    @FXML
    protected Button changeExportImportTaxesButton;
    @FXML
    protected Button changeCostTaxesButton;
    @FXML
    protected Button changeTypeTaxesButton;
    @FXML
    protected Button goBackButton;

    @FXML
    void initialize(){initMainScene();}

    public void initMainScene() {
        changeExportImportTaxesButton.setOnMouseClicked(event -> {
            switchScene(event, CHANGE_ALL_EXPORT_IMPORT_TAXES_FXML);
        });
        changeCostTaxesButton.setOnMouseClicked(event -> {
            switchScene(event, CHANGE_ALL_COST_TAXES_FXML);
        });
        changeTypeTaxesButton.setOnMouseClicked(event -> {
            switchScene(event, CHANGE_ALL_TYPE_TAXES_FXML);
        });
        goBackButton.setOnMouseClicked(event -> {
            switchScene(event, ADMIN_MENU_FXML);
        });
    }
}
