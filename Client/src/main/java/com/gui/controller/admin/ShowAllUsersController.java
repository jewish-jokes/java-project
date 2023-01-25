package com.gui.controller.admin;

import com.SQLsupport.DBClass.User;
import com.implementation.client.OwnClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import static com.gui.Constants.ADMIN_MENU_FXML;

public class ShowAllUsersController extends AdminMenuController{

    @FXML
    private Button createAdminButton;

    @FXML
    private Button deleteUserButton;

    @FXML
    private Button blockButton;

    @FXML
    private Button unblockButton;

    @FXML
    private Button goBackButton;

    @FXML
    private TableColumn<User, Integer> idUserColumn;

    @FXML
    private TableColumn<User, String> loginColumn;

    @FXML
    private TableColumn<User, String> passwordColumn;

    @FXML
    private TableColumn<User, Integer> roleColumn;

    @FXML
    private TableView<User> usersTable;
    private ObservableList<User> dataFromServer;
    private ObservableList<User> selectableUserList;

    @FXML
    void initialize() {

        dataFromServer = FXCollections.observableArrayList();
        selectableUserList = FXCollections.observableArrayList();
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        idUserColumn.setCellValueFactory(new PropertyValueFactory<>("id_user"));

        this.initMainScene();
        this.selectAllUsers();

    }

    private void selectAllUsers() {
        client.sendData("select all users");
        client.sendData("1");
        this.updateTable();
    }

    private void updateTable() {
        dataFromServer.clear();
        dataFromServer.addAll(client.receiveUsers());
        usersTable.setItems(dataFromServer);
    }

    public void initMainScene() {
        client = OwnClient.getInstance();
        blockButton.setOnMouseClicked(event -> {
            blocking();
        });
        unblockButton.setOnMouseClicked((event -> {
            unblocking();
        }));
        goBackButton.setOnMouseClicked((event -> {
            switchScene(event, ADMIN_MENU_FXML);
        }));
        createAdminButton.setOnMouseClicked((event -> {
            createAdmin();
        }));
        deleteUserButton.setOnMouseClicked((event -> {
            deleteUser();
        }));

        usersTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        selectableUserList.clear();
                        selectableUserList.add(usersTable.getSelectionModel().getSelectedItem());
                    }
                }
        );
    }

    private void deleteUser() {
        if (selectableUserList.size() == 0)
            return;
        int id = selectableUserList.get(0).getId_user();
        client.sendData("delete user");
        client.sendData(Integer.toString(id));

        if (client.receiveResult())
            selectAllUsers();
    }

    private void createAdmin() {
        if (selectableUserList.size() == 0)
            return;
        int id = selectableUserList.get(0).getId_user();
        client.sendData("edit user role");
        client.sendData(id + " " + "1");

        if (client.receiveResult())
            selectAllUsers();
    }


    private void blocking() {
        if (selectableUserList.size() == 0)
            return;
        int id = selectableUserList.get(0).getId_user();
        client.sendData("edit user role");
        client.sendData(id + " " + "-1");

        if (client.receiveResult())
            selectAllUsers();
    }

    private void unblocking() {
        if (selectableUserList.size() == 0)
            return;
        int id = selectableUserList.get(0).getId_user();
        client.sendData("edit user role");
        client.sendData(id + " " + "0");

        if (client.receiveResult()) {
            selectAllUsers();
        }

    }
}
