package com.server;

import com.SQLsupport.DBClass.*;
import com.SQLsupport.DBConnection;
import com.SQLsupport.strategies.PrintBasket;
import com.SQLsupport.strategies.SelectableTicket;
import com.SQLsupport.strategies.Updatable;
import com.SQLsupport.strategies.selectable.*;
import com.SQLsupport.strategies.selectabletobjects.*;
import com.SQLsupport.strategies.updatable.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Vector;

public class ThreadForServer implements Runnable {
    private Socket client;
    private ObjectInputStream input_stream;
    private ObjectOutputStream output_stream;
    private static int allClientCount = 0;
    private int currentClient;
    private DBConnection dbConnection;

    public ThreadForServer(ServerSocket serverSocket, DBConnection dbConnection) {

        try {
            client = serverSocket.accept();
            allClientCount++;
            currentClient = allClientCount;
            input_stream = new ObjectInputStream(client.getInputStream());
            output_stream = new ObjectOutputStream(client.getOutputStream());
            this.dbConnection = dbConnection;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Release() throws IOException, NullPointerException {
        input_stream.close();
        output_stream.close();
        client.close();
    }

    private void closeThread() {
        try {
            allClientCount--;
            this.Release();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String clientChoice = (String) input_stream.readObject();
                String dataFromClient = (String) input_stream.readObject();

                Updatable sqlUpdate = null;
                switch (clientChoice) {
                    case "add user" -> sqlUpdate = new AddUser();
                    case "add object" -> sqlUpdate = new AddObject();
                    case "delete all user objects" -> sqlUpdate = new DeleteAllUserObjects();
                    case "delete object" -> sqlUpdate = new DeleteObject();
                    case "delete user" -> sqlUpdate = new DeleteUser();
                    case "edit cost taxes" -> sqlUpdate = new EditCostTaxes();
                    case "edit export import taxes" -> sqlUpdate = new EditExportImportTaxes();
                    case "edit type taxes" -> sqlUpdate = new EditTypeTaxes();
                    case "edit user" -> sqlUpdate = new EditUser();
                    case "edit user role" -> sqlUpdate = new EditUserRole();
                    case "add review" -> sqlUpdate = new AddReview();
                    case "add time" -> sqlUpdate = new AddTime();
                    case "add total cost" -> sqlUpdate = new AddTotalCost();
                    case "exit" -> {
                        closeThread();
                        return;
                    }
                }
                if (sqlUpdate != null) {
                    sqlUpdate.getData(dataFromClient);
                    boolean res = sqlUpdate.executeUpdate(dbConnection.getMyConnection());
                    output_stream.writeObject(res);
                    continue;
                }

                SelectableTicket sqlSelectCar = null;
                switch (clientChoice) {
                    case "select all objects" -> sqlSelectCar = new SelectAllObjects();
                    case "select object" -> sqlSelectCar = new SelectObject();
                    case "select objects by name" -> sqlSelectCar = new SelectObjectsByName();
                }
                if (sqlSelectCar != null) {
                    sqlSelectCar.getData(dataFromClient);
                    Vector<UserObjects> objects = sqlSelectCar.executeSelect(dbConnection.getMyConnection());
                    output_stream.writeObject(objects);
                    continue;
                }

                switch (clientChoice) {
                    case "select user" -> {
                        var sqlSelect = new SelectUser();
                        sqlSelect.getData(dataFromClient);
                        Vector<User> user = sqlSelect.executeSelect(dbConnection.getMyConnection());
                        output_stream.writeObject(user);
                    }
                    case "select all users" -> {
                        var sqlSelect3 = new SelectAllUsers();
                        sqlSelect3.getData(dataFromClient);
                        Vector<User> users = sqlSelect3.executeSelect(dbConnection.getMyConnection());
                        output_stream.writeObject(users);
                    }
                    case "select all type taxes" -> {
                        var sqlSelect2 = new SelectAllTypeTaxes();
                        sqlSelect2.getData(dataFromClient);
                        Vector<TypeTaxes> typeTaxes = sqlSelect2.executeSelect(dbConnection.getMyConnection());
                        output_stream.writeObject(typeTaxes);
                    }
                    case "select all export import taxes" -> {
                        var sqlSelect3 = new SelectAllExportImportTaxes();
                        sqlSelect3.getData(dataFromClient);
                        Vector<ImportExportTaxes> importExportTaxes = sqlSelect3.executeSelect(dbConnection.getMyConnection());
                        output_stream.writeObject(importExportTaxes);
                    }
                    case "select all cost taxes" -> {
                        var sqlSelect3 = new SelectAllCostTaxes();
                        sqlSelect3.getData(dataFromClient);
                        Vector<CostTaxes> costTaxes = sqlSelect3.executeSelect(dbConnection.getMyConnection());
                        output_stream.writeObject(costTaxes);
                    }
                    case "print basket" -> {
                        var sqlSelect3 = new PrintBasket();
                        sqlSelect3.getData(dataFromClient);
                        String filePath = sqlSelect3.execute(dbConnection.getMyConnection());
                        output_stream.writeObject(filePath);
                    }
                    case "select data for chart" -> {
                        var sqlSelect3 = new SelectDataForPieChart();
                        sqlSelect3.getData(dataFromClient);
                        Vector<InformationForPieChart> informationForPieCharts = sqlSelect3.execute(dbConnection.getMyConnection());
                        output_stream.writeObject(informationForPieCharts);
                    }
                    case "select reviews" -> {
                        var sqlSelect3 = new SelectReviews();
                        sqlSelect3.getData(dataFromClient);
                        Vector<String> reviews = sqlSelect3.executeSelect(dbConnection.getMyConnection());
                        output_stream.writeObject(reviews);
                    }
                }
            } catch (SocketException e) {
                System.out.println("client №" + currentClient + " break connection");
                closeThread();
                return;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}
