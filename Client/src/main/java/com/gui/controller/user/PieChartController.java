package com.gui.controller.user;

import com.SQLsupport.DBClass.InformationForPieChart;
import com.implementation.client.OwnClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

import java.util.Vector;

import static com.gui.Constants.USER_MENU_FXML;

public class PieChartController extends UserMenuController {

    ObservableList<PieChart.Data> dataList;

    @FXML
    private PieChart myPieChart;

    @FXML
    protected Button goBackButton;

    @FXML
    public void initialize() {
        client= OwnClient.getInstance();
        goBackButton.setOnMouseClicked(event -> {
            switchScene(event, USER_MENU_FXML);
        });
        dataList = FXCollections.observableArrayList();
        client.sendData("select data for chart");
        client.sendData(Integer.toString(client.getUserProfile().getId_user()));
        Vector<InformationForPieChart> informationForPieCharts = client.receiveDataForPieChart();
        for (var data : informationForPieCharts)
            dataList.add(new PieChart.Data(data.getObjName(),1));

        myPieChart.setData(dataList);
    }
}
