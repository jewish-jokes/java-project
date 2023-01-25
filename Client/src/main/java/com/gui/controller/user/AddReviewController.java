package com.gui.controller.user;

import com.SQLsupport.DBClass.ImportExportTaxes;
import com.implementation.client.OwnClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.util.Vector;

import static com.gui.Constants.USER_MENU_FXML;

public class AddReviewController extends UserMenuController{

    OwnClient client;

    @FXML
    public Button goBackButton;
    @FXML
    public TextArea enterReviewsArea;
    @FXML
    public Button submitReviewButton;
    @FXML
    public TextArea showReviewsArea;

    void initialize() {
        initMainScene();
    }

    public void initMainScene() {
        client = OwnClient.getInstance();
        initFields();
        goBackButton.setOnMouseClicked(event -> {
            switchScene(event, USER_MENU_FXML);
        });
        submitReviewButton.setOnMouseClicked(event -> {
            addReview();
        });
    }

    private void addReview() {
        client.sendData("add review");
        String newProfile =
                enterReviewsArea.getText() + " " +
                        client.getUserProfile().getId_user();
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

    public void initFields() {
        client.sendData("select reviews");
        client.sendData("");
        Vector<String> reviews = client.receiveReviews();
        if (reviews.size()>0)showReviewsArea.setText(reviews.get(0)+"\n");
        for (Integer i = 1; i < reviews.size(); i++)
        {
            showReviewsArea.appendText(reviews.get(i)+"\n");
        }
    }
}
