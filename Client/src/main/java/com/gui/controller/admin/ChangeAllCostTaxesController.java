package com.gui.controller.admin;

import com.SQLsupport.DBClass.CostTaxes;
import com.SQLsupport.DBClass.ImportExportTaxes;
import com.SQLsupport.DBClass.User;
import com.implementation.client.OwnClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Vector;

import static com.gui.Constants.CHANGE_ALL_TAXES_FXML;

public class ChangeAllCostTaxesController extends AdminMenuController{
    private boolean isEdit;
    private String admin, userStr;

    @FXML
    private Label under1000Label;

    @FXML
    private Label from1000To10000Label;

    @FXML
    private Label over10000Label;

    @FXML
    private Button saveChangesButton;

    @FXML
    private Button goBackButton;

    @FXML
    private TextField under1000Field;

    @FXML
    private TextField from1000To10000Field;

    @FXML
    private TextField over10000Field;


    @FXML
    void initialize() {
        isEdit = false;
        this.initMainScene();
        this.initFields();
        this.addActions();
    }

    public void addActions() {
        under1000Field.setOnMouseClicked(event -> {
            isEdit = true;
        });
        from1000To10000Field.setOnMouseClicked(event -> {
            isEdit = true;
        });
        over10000Field.setOnMouseClicked(event -> {
            isEdit = true;
        });
    }

    private void editCostTaxes() {
        //if (!isEdit)
            //return;
        client.sendData("edit cost taxes");
        String newProfile =
                        under1000Field.getText() + "@@@" +
                        from1000To10000Field.getText() + "@@@" +
                        over10000Field.getText();
        client.sendData(newProfile);
        if (client.receiveResult()) {
            client.sendData("select all cost taxes");
            client.sendData("");
            Vector<CostTaxes> costTaxes = client.receiveCostTaxes();
            if (costTaxes != null) {
                initFields();
            }
        }
    }

    @Override
    public void initMainScene() {
        client = OwnClient.getInstance();
        saveChangesButton.setOnMouseClicked(event -> {
            editCostTaxes();
        });
        goBackButton.setOnMouseClicked(event -> {
            switchScene(event, CHANGE_ALL_TAXES_FXML);
        });
    }

    public void initFields() {
        client.sendData("select all cost taxes");
        client.sendData("");
        Vector<CostTaxes> costTaxes = client.receiveCostTaxes();
        under1000Field.setText(Double.toString(costTaxes.get(0).getUnder_1000()));
        from1000To10000Field.setText(Double.toString(costTaxes.get(0).getFrom_1000_to_10000()));
        over10000Field.setText(Double.toString(costTaxes.get(0).getOver_10000()));
    }
}
