package com.implementation.client;


import com.SQLsupport.DBClass.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class OwnClient {
    private Socket client;
    private ObjectOutputStream output_stream;
    private ObjectInputStream input_stream;
    private Scanner scan;
    private static OwnClient ownClient = null;
    private User user = null;
    private boolean isSmallFont;
    private boolean isFirstBackgroundImage;


    private OwnClient(String host, int port) {
        try {
            client = new Socket(host, port);
            output_stream = new ObjectOutputStream(client.getOutputStream());
            input_stream = new ObjectInputStream(client.getInputStream());
            scan = new Scanner(System.in);
            isSmallFont = true;
            isFirstBackgroundImage = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isSmallFont() {
        return isSmallFont;
    }

    public boolean isFirstBackgroundImage() {
        return isFirstBackgroundImage;
    }

    public void changeFont() {
        isSmallFont = !isSmallFont;
    }

    public void changeBackground() { isFirstBackgroundImage = !isFirstBackgroundImage; }

    public static OwnClient getInstance() {
        if (ownClient == null) {
            ownClient = new OwnClient("127.0.0.1", 2525);
        }
        return ownClient;
    }

    public void setUserProfile(User us) {
        user = new User(us);
    }

    public User getUserProfile() {
        return user;
    }

    public void sendData(String data) {
        try {
            output_stream.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean receiveResult() {
        try {
            return (boolean) input_stream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String receiveFilePath() {
        try {
            return (String) input_stream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Vector<User> receiveUsers() {
        try {
            return (Vector<User>) input_stream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Vector<CostTaxes> receiveCostTaxes() {
        try {
            return (Vector<CostTaxes>) input_stream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Vector<ImportExportTaxes> receiveImportExportTaxes() {
        try {
            return (Vector<ImportExportTaxes>) input_stream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Vector<TypeTaxes> receiveTypeTaxes() {
        try {
            return (Vector<TypeTaxes>) input_stream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close() {
        try {
            output_stream.close();
            input_stream.close();
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Vector<UserObjects> receiveObjects() {
        try {
            return (Vector<UserObjects>) input_stream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Vector<InformationForPieChart> receiveDataForPieChart() {
        try {
            return (Vector<InformationForPieChart>) input_stream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Vector<String> receiveReviews() {
        try {
            return (Vector<String>) input_stream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

