package com.gui.controller.user;

import com.SQLsupport.DBClass.UserObjects;
import com.gui.MainMenuGUI;
import com.implementation.client.OwnClient;
import com.itextpdf.text.*;
import com.itextpdf.text.List;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.*;

import static com.gui.Constants.*;

public class UserMenuController {
    public VBox leftPane;
    public AnchorPane mainPane;
    public AnchorPane headerPane;
    public ImageView backgroundImage;

    protected OwnClient client;
    protected Parent root;
    protected Stage stage;
    protected Scene scene;

    @FXML
    public TextField countTimeField;

    @FXML
    public Button shareOpinionButton;

    @FXML
    public Button countTimeButton;

    @FXML
    protected Button changeThemeButton;

    @FXML
    protected Button createPdfButton;

    @FXML
    protected Button goWebButton;

    @FXML
    protected Button changeFontSizeButton;

    @FXML
    protected Button showInfoButton;

    @FXML
    protected Button addObjectButton;

    @FXML
    protected Button showObjectsButton;

    @FXML
    protected Button showChartButton;

    @FXML
    protected Button userProfileButton;

    @FXML
    protected Button logOutButton;

    @FXML
    protected Button closeButton;

    @FXML
    void initialize() {
        initMainScene();
    }

    public void initMainScene() {

        client = OwnClient.getInstance();

        String path = client.isFirstBackgroundImage() ? FIRST_BACKGROUND_IMAGE : SECOND_BACKGROUND_IMAGE;
        changeTheme(path);

        path = client.isSmallFont() ? SMALL_FONT : BIG_FONT;
        changeFontSize(path);

        addObjectButton.setOnMouseClicked(event -> {
            switchScene(event, ADD_OBJECT_FXML);
        });
        changeThemeButton.setOnMouseClicked(event -> {
            String path1 = client.isFirstBackgroundImage() ? FIRST_BACKGROUND_IMAGE : SECOND_BACKGROUND_IMAGE;
            changeTheme(path1);
            client.changeBackground();
            this.initialize();
        });
        goWebButton.setOnMouseClicked(event -> {
            try {
                goWeb();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        changeFontSizeButton.setOnMouseClicked(event -> {
            String path1 = client.isSmallFont() ? SMALL_FONT : BIG_FONT;
            changeFontSize(path1);
            client.changeFont();
            this.initialize();
        });
        showInfoButton.setOnMouseClicked(event -> {
            switchScene(event, SHOW_INFO_FXML);
        });
        logOutButton.setOnMouseClicked(event -> {
            switchScene(event, MAIN_MENU_FXML);
        });
        showObjectsButton.setOnMouseClicked(event -> {
            switchScene(event, SHOW_OBJECTS_FXML);
        });
        showChartButton.setOnMouseClicked(event -> {
            switchScene(event, "PieChart.fxml" );
        });
        userProfileButton.setOnMouseClicked(event -> {
            switchScene(event, USER_PROFILE_FXML);
        });
        shareOpinionButton.setOnMouseClicked(event -> {
            switchScene(event, ADD_REVIEW);
        });
        countTimeButton.setOnMouseClicked(event -> {
            countTime();
        });
        createPdfButton.setOnMouseClicked(event -> {
            try {
                createPdf();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            }
        });
        closeButton.setOnMouseClicked(event -> {
            client.sendData("exit");
            client.sendData(" ");
            client.close();
            stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        });
    }

    private void countTime() {
        client.sendData("select all objects");
        client.sendData(Integer.toString(client.getUserProfile().getId_user()));
        Vector<UserObjects> userObjects = client.receiveObjects();
        int fur_count = 0, alcohol_count = 0, other_count = 0, cigarettes_count = 0, jewelry_count = 0;
        for (var object:userObjects){
            if (object.getType().equals("Мех")) fur_count++;
            if (object.getType().equals("Алкоголь")) alcohol_count++;
            if (object.getType().equals("Другое")) other_count++;
            if (object.getType().equals("Табак")) cigarettes_count++;
            if (object.getType().equals("Украшения")) jewelry_count++;
        }
        double result=0.5*fur_count+0.2*alcohol_count+0.3*other_count+0.1*cigarettes_count+0.4*jewelry_count;
        countTimeField.setText(Double.toString(result));
        client.sendData("add time");
        client.sendData(result + " " + userObjects.size() + " " + client.getUserProfile().getId_user());
        client.receiveResult();
    }

    private void createPdf() throws FileNotFoundException, DocumentException {
        client.sendData("select all objects");
        client.sendData(Integer.toString(client.getUserProfile().getId_user()));
        Vector<UserObjects> userObjects = client.receiveObjects();
        List list = new List();
        for (var object : userObjects)
            list.add(object.toString());
        Document document = new Document();
        PdfWriter.getInstance(document,new FileOutputStream("pdf" +client.getUserProfile().getId_user()+ ".pdf"));
        document.open();
        document.add(list);
        document.close();
    }

    private void changeTheme(String themePath) {
        backgroundImage.setImage(new Image(themePath));
    }

    private void goWeb() throws IOException {
        java.awt.Desktop.getDesktop().browse(URI.create("https://stackoverflow.com/questions/748895/how-do-you-open-web-pages-in-java"));
    }

    private void changeFontSize(String themePath) {
        ObservableList<String> styleSheets = headerPane.getStylesheets();
        String css = MainMenuGUI.class.getResource(themePath).toExternalForm();
        styleSheets.add(css);
        if (styleSheets.size() > 1)
            styleSheets.remove(0);
        headerPane.getStyleClass().add("header");
        mainPane.getStyleClass().add("main");
        leftPane.getStyleClass().add("left");
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
