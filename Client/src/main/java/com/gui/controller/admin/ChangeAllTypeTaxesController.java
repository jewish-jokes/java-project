package com.gui.controller.admin;

import com.SQLsupport.DBClass.TypeTaxes;
import com.SQLsupport.DBClass.User;
import com.implementation.client.OwnClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Vector;

import static com.gui.Constants.CHANGE_ALL_TAXES_FXML;

public class ChangeAllTypeTaxesController extends AdminMenuController{
    private boolean isEdit;
    private String admin, userStr;

    @FXML
    private Label alcoholTaxesLabel;

    @FXML
    private Label cigarettesTaxesLabel;

    @FXML
    private Label furTaxesLabel;

    @FXML
    private Label jewelryTaxesLabel;

    @FXML
    private Label otherTaxesLabel;

    @FXML
    private Button saveChangesButton;

    @FXML
    private Button goBackButton;

    @FXML
    private TextField alcoholTaxesField;

    @FXML
    private TextField cigarettesTaxesField;

    @FXML
    private TextField furTaxesField;

    @FXML
    private TextField jewelryTaxesField;

    @FXML
    private TextField otherTaxesField;

    @FXML
    void initialize() {
        isEdit = false;
        this.initMainScene();
        this.initFields();
        this.addActions();
    }

    public void addActions() {
        alcoholTaxesField.setOnMouseClicked(event -> {
            isEdit = true;
        });
        cigarettesTaxesField.setOnMouseClicked(event -> {
            isEdit = true;
        });
        furTaxesField.setOnMouseClicked(event -> {
            isEdit = true;
        });
        jewelryTaxesField.setOnMouseClicked(event -> {
            isEdit = true;
        });
        otherTaxesField.setOnMouseClicked(event -> {
            isEdit = true;
        });
    }

    private void editTypeTaxes() {

        client.sendData("edit type taxes");
        String newProfile =
                        alcoholTaxesField.getText() + "@@@" +
                        cigarettesTaxesField.getText() + "@@@" +
                        furTaxesField.getText() + "@@@" +
                        jewelryTaxesField.getText() + "@@@" +
                        otherTaxesField.getText();
        client.sendData(newProfile);
        if (client.receiveResult()) {
            client.sendData("select all type taxes");
            client.sendData("");
           Vector<TypeTaxes> typeTaxes = client.receiveTypeTaxes();
            if (typeTaxes != null) {
                initFields();
            }
        }
    }

    @Override
    public void initMainScene() {
        client = OwnClient.getInstance();
        saveChangesButton.setOnMouseClicked(event -> {
            editTypeTaxes();
        });
        goBackButton.setOnMouseClicked(event -> {
            switchScene(event, CHANGE_ALL_TAXES_FXML);
        });
    }


    public void initFields() {
        client.sendData("select all type taxes");
        client.sendData("");
        Vector<TypeTaxes> typeTaxes = client.receiveTypeTaxes();
        alcoholTaxesField.setText(Double.toString(typeTaxes.get(0).getAlcoholTaxes()));
        cigarettesTaxesField.setText(Double.toString(typeTaxes.get(0).getCigarettesTaxes()));
        furTaxesField.setText(Double.toString(typeTaxes.get(0).getFurTaxes()));
        jewelryTaxesField.setText(Double.toString(typeTaxes.get(0).getJewelryTaxes()));
        otherTaxesField.setText(Double.toString(typeTaxes.get(0).getOtherTaxes()));
    }
}
