package com.SQLsupport.strategies;

import com.SQLsupport.DBClass.UserObjects;
import com.SQLsupport.strategies.selectabletobjects.SelectAllObjects;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.Vector;

public class PrintBasket implements Requestable {

    SelectAllObjects selectAllObjects = null;
    private String user_id;

    public PrintBasket() {
        selectAllObjects = new SelectAllObjects();
    }

    @Override
    public void getData(String data) {
        selectAllObjects.getData(data);
        user_id = selectAllObjects.getId();
    }

    public String execute(Connection conn) {
        String path = "file-" + user_id + ".txt";
        Vector<UserObjects> objects = selectAllObjects.executeSelect(conn);
        try (FileWriter writer = new FileWriter(path, false)) {
            for (var object : objects)
                object.print(writer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return path;
    }
}
