package com.gui.controller.user;

import com.implementation.client.OwnClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

import static com.gui.Constants.*;

public class ShowInfoController extends UserMenuController{
    @FXML
    protected Button goBackButton;

    @FXML
    protected TextArea infoArea;

    void initialize() {
        initMainScene();
    }

    public void initMainScene() {
        client = OwnClient.getInstance();
        goBackButton.setOnMouseClicked(event -> {
            switchScene(event, USER_MENU_FXML);
        });
        infoArea.setText("Данное приложение было разработано");
    }
}
