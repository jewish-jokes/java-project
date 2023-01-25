package com.gui.controller.admin;

import com.gui.MainMenuGUI;
import com.implementation.client.OwnClient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static com.gui.Constants.*;

public class AdminMenuController {

    protected OwnClient client;
    protected Parent root;
    protected Stage stage;
    protected Scene scene;

    @FXML
    protected Button showUsersButton;
    @FXML
    protected Button showAdminsButton;
    @FXML
    protected AnchorPane headerPane;
    @FXML
    protected Button changeAllTaxesButton;
    @FXML
    protected Button closeButton;
    @FXML
    protected VBox leftPane;
    @FXML
    protected AnchorPane mainPane;
    @FXML
    protected Button deleteUserButton;
    @FXML
    protected Button logOutButton;
    @FXML
    protected Label headLabel;

    public AdminMenuController() {
    }

    @FXML
    void initialize() {
        initMainScene();
    }

    public void initMainScene() {

        client = OwnClient.getInstance();

        logOutButton.setOnMouseClicked(event -> {
            switchScene(event, MAIN_MENU_FXML);
        });
        showAdminsButton.setOnMouseClicked(event -> {
            switchScene(event, SHOW_ALL_ADMINS_FXML);
        });
        showUsersButton.setOnMouseClicked(event -> {
            switchScene(event, SHOW_ALL_USERS_FXML);
        });
        changeAllTaxesButton.setOnMouseClicked(event -> {
            switchScene(event, CHANGE_ALL_TAXES_FXML);
        });
        closeButton.setOnMouseClicked(event -> {
            client.sendData("exit");
            client.sendData(" ");
            client.close();
            stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        });
    }

    public void switchScene(MouseEvent event, String path) {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(MainMenuGUI.class.getResource(path)));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

