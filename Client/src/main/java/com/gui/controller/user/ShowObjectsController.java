package com.gui.controller.user;

import com.SQLsupport.DBClass.CostTaxes;
import com.SQLsupport.DBClass.ImportExportTaxes;
import com.SQLsupport.DBClass.TypeTaxes;
import com.SQLsupport.DBClass.UserObjects;
import com.implementation.client.OwnClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Vector;

import static com.gui.Constants.CHANGE_OBJECT_FXML;
import static com.gui.Constants.USER_MENU_FXML;

public class ShowObjectsController extends UserMenuController {

    public UserObjects userObjectForEditing;

    @FXML
    public TextField typeTaxesField;

    @FXML
    public TextField costTaxesField;

    @FXML
    public TextField importExportTaxesField;

    @FXML
    public Label typeTaxesLable;

    @FXML
    public Label costTaxesLable;

    @FXML
    public Label importExportTaxesLable;

    @FXML
    public Button countImportExportTaxesButton;

    @FXML
    public Button countCostTaxesButton;

    @FXML
    public Button countTypeTaxesButton;

    @FXML
    private Button goBackButton;

    @FXML
    private Button deleteObjectButton;

    @FXML
    private Button countTaxesButton;

    @FXML
    private Button printObjectsButton;

    @FXML
    private TextField taxesField;

    @FXML
    private Label taxesLable;

    @FXML
    private Button findObjectsButton;

    @FXML
    private TextField findObjectsField;

    @FXML
    private Label findObjectsLable;

    @FXML
    private Button reloadButton;

    @FXML
    private TableColumn<UserObjects, Integer> idObjectColumn;

    @FXML
    private TableColumn<UserObjects, String> nameColumn;

    @FXML
    private TableColumn<UserObjects, Double> costColumn;

    @FXML
    private TableColumn<UserObjects, Integer> forExportColumn;

    @FXML
    private TableColumn<UserObjects, Integer> forImportColumn;

    @FXML
    private TableColumn<UserObjects, String> typeColumn;

    @FXML
    private TableView<UserObjects> objectsTable;
    private ObservableList<UserObjects> dataFromServer;
    protected ObservableList<UserObjects> selectableUserObjectsList;

    @FXML
    void initialize() {
        client = OwnClient.getInstance();
        dataFromServer = FXCollections.observableArrayList();
        selectableUserObjectsList = FXCollections.observableArrayList();
        idObjectColumn.setCellValueFactory(new PropertyValueFactory<>("id_object"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        forExportColumn.setCellValueFactory(new PropertyValueFactory<>("for_export"));
        forImportColumn.setCellValueFactory(new PropertyValueFactory<>("for_import"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        this.initMainScene();
        this.selectAllObjects();

    }

    private void selectAllObjects() {
        client.sendData("select all objects");
        client.sendData(Integer.toString(client.getUserProfile().getId_user()));
        this.updateTable();
    }

    private void updateTable() {
        dataFromServer.clear();
        dataFromServer.addAll(client.receiveObjects());
        objectsTable.setItems(dataFromServer);
    }

    private void updateTableFind(Vector<UserObjects> userObjects) {
        dataFromServer.clear();
        dataFromServer.addAll(userObjects);
        objectsTable.setItems(dataFromServer);
    }

    public void initMainScene() {
        client = OwnClient.getInstance();
        goBackButton.setOnMouseClicked((event -> {
            switchScene(event, USER_MENU_FXML);
        }));
        deleteObjectButton.setOnMouseClicked((event -> {
            deleteObject();
        }));
        countTaxesButton.setOnMouseClicked((event -> {
            countTaxes();
        }));
        printObjectsButton.setOnMouseClicked((event -> {
            printObjects();
        }));
        findObjectsButton.setOnMouseClicked((event -> {
            findObjects();
        }));
        reloadButton.setOnMouseClicked((event -> {
            this.selectAllObjects();
        }));
        countCostTaxesButton.setOnMouseClicked((event -> {
            countCostTaxes();
        }));
        countImportExportTaxesButton.setOnMouseClicked((event -> {
            countImportExportTaxes();
        }));
        countTypeTaxesButton.setOnMouseClicked((event -> {
            countTypeTaxes();
        }));
        objectsTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        selectableUserObjectsList.clear();
                        selectableUserObjectsList.add(objectsTable.getSelectionModel().getSelectedItem());
                    }
                }
        );
    }

    private void countTypeTaxes() {
        if (selectableUserObjectsList.size() == 0)
            return;
        int id = selectableUserObjectsList.get(0).getId_object();
        client.sendData("select object");
        client.sendData(Integer.toString(id));
        Vector<UserObjects> userObjects = client.receiveObjects();
        if (userObjects.size() != 0) {
            client.sendData("select all type taxes");
            client.sendData("");
            Vector<TypeTaxes> typeTaxes = client.receiveTypeTaxes();
            String type = userObjects.get(0).getType();
            double type_taxes = 0;
            switch (type) {
                case "Мех" -> type_taxes = typeTaxes.get(0).getFurTaxes();
                case "Другое" -> type_taxes = typeTaxes.get(0).getOtherTaxes();
                case "Алкоголь" -> type_taxes = typeTaxes.get(0).getAlcoholTaxes();
                case "Табак" -> type_taxes = typeTaxes.get(0).getCigarettesTaxes();
                case "Украшения" -> type_taxes = typeTaxes.get(0).getJewelryTaxes();
            }
            typeTaxesField.setText(Double.toString(type_taxes));
        }
    }

    private void countImportExportTaxes() {
        if (selectableUserObjectsList.size() == 0)
            return;
        int id = selectableUserObjectsList.get(0).getId_object();
        client.sendData("select object");
        client.sendData(Integer.toString(id));
        Vector<UserObjects> userObjects = client.receiveObjects();
        if (userObjects.size() != 0) {
            client.sendData("select all export import taxes");
            client.sendData("");
            Vector<ImportExportTaxes> importExportTaxes = client.receiveImportExportTaxes();
            int for_export = userObjects.get(0).getFor_export();
            double export_import_taxes = 0;
            if (for_export == 1) export_import_taxes = importExportTaxes.get(0).getExport_taxes();
            else export_import_taxes = importExportTaxes.get(0).getImport_taxes();
            importExportTaxesField.setText(Double.toString(export_import_taxes));
        }
    }

    private void countCostTaxes() {
        if (selectableUserObjectsList.size() == 0)
            return;
        int id = selectableUserObjectsList.get(0).getId_object();
        client.sendData("select object");
        client.sendData(Integer.toString(id));
        Vector<UserObjects> userObjects = client.receiveObjects();
        if (userObjects.size() != 0) {
            client.sendData("select all cost taxes");
            client.sendData("");
            Vector<CostTaxes> costTaxes = client.receiveCostTaxes();
            double cost = userObjects.get(0).getCost();
            double cost_taxes = 0;
            if (cost < 1000) {
                cost_taxes = costTaxes.get(0).getUnder_1000();
            } else {
                if (cost > 10000) cost_taxes = costTaxes.get(0).getOver_10000();
                else cost_taxes = costTaxes.get(0).getFrom_1000_to_10000();
            }
            costTaxesField.setText(Double.toString(cost_taxes));
        }
    }

    private void findObjects() {
        String name = findObjectsField.getText();
        int id = client.getUserProfile().getId_user();
        client.sendData("select objects by name");
        client.sendData(name+" "+id);
        Vector<UserObjects> userObjects = client.receiveObjects();
        this.updateTableFind(userObjects);
    }

    private void printObjects() {
        client.sendData("print basket");
        client.sendData(Integer.toString(client.getUserProfile().getId_user()));
        client.receiveFilePath();
    }

    private void countTaxes() {
        if (selectableUserObjectsList.size() == 0)
            return;
        int id = selectableUserObjectsList.get(0).getId_object();
        client.sendData("select object");
        client.sendData(Integer.toString(id));
        Vector<UserObjects> userObjects = client.receiveObjects();
        if (userObjects.size() != 0) {
            client.sendData("select all type taxes");
            client.sendData("");
            Vector<TypeTaxes> typeTaxes = client.receiveTypeTaxes();
            client.sendData("select all export import taxes");
            client.sendData("");
            Vector<ImportExportTaxes> importExportTaxes = client.receiveImportExportTaxes();
            client.sendData("select all cost taxes");
            client.sendData("");
            Vector<CostTaxes> costTaxes = client.receiveCostTaxes();
            double cost = userObjects.get(0).getCost();
            int for_export = userObjects.get(0).getFor_export();
            String type = userObjects.get(0).getType();
            double cost_taxes = 0, export_import_taxes = 0, type_taxes = 0;
            switch (type) {
                case "Мех" -> type_taxes = typeTaxes.get(0).getFurTaxes();
                case "Другое" -> type_taxes = typeTaxes.get(0).getOtherTaxes();
                case "Алкоголь" -> type_taxes = typeTaxes.get(0).getAlcoholTaxes();
                case "Табак" -> type_taxes = typeTaxes.get(0).getCigarettesTaxes();
                case "Украшения" -> type_taxes = typeTaxes.get(0).getJewelryTaxes();
            }
            if (cost < 1000) {
                cost_taxes = costTaxes.get(0).getUnder_1000();
            } else {
                if (cost > 10000) cost_taxes = costTaxes.get(0).getOver_10000();
                else cost_taxes = costTaxes.get(0).getFrom_1000_to_10000();
            }
            if (for_export == 1) export_import_taxes = importExportTaxes.get(0).getExport_taxes();
            else export_import_taxes = importExportTaxes.get(0).getImport_taxes();
            double final_taxes = cost_taxes + export_import_taxes + type_taxes;
            taxesField.setText(Double.toString(final_taxes));
            client.sendData("add total cost");
            client.sendData(final_taxes+" "+userObjects.get(0).getId_object());
            client.receiveResult();
        }
    }

    private void deleteObject() {
        if (selectableUserObjectsList.size() == 0)
            return;
        int id = selectableUserObjectsList.get(0).getId_object();
        client.sendData("delete object");
        client.sendData(Integer.toString(id));
        if (client.receiveResult()) {
            selectAllObjects();
        }
    }
}
