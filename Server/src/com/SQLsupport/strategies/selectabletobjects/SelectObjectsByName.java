package com.SQLsupport.strategies.selectabletobjects;

import com.SQLsupport.DBClass.UserObjects;
import com.SQLsupport.strategies.SelectableTicket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import static com.SQLsupport.Constants.*;

public class SelectObjectsByName implements SelectableTicket {
    private String nameOfObject;
    private String user_id;

    @Override
    public void getData(String data) {
        String[] dataFromClient = data.split(" ");
        nameOfObject = dataFromClient[0];
        user_id = dataFromClient[1];
    }

    @Override
    public Vector<UserObjects> executeSelect(Connection conn) {
        int count = 1;
        ResultSet res = null;
        Vector<UserObjects> objects = null;

        try {
            String sql1 = "SELECT " +
                    OBJECTS_SCHEMA + "." + OBJECT_ID + ", " +
                    OBJECTS_SCHEMA + "." + OBJECT_NAME+ ", " +
                    OBJECTS_SCHEMA + "." + OBJECT_COST + ", " +
                    OBJECTS_SCHEMA + "." + OBJECT_FOR_EXPORT + ", " +
                    OBJECTS_SCHEMA + "." + OBJECT_FOR_IMPORT + ", " +
                    OBJECTS_SCHEMA + "." + OBJECT_TYPE + ", " +
                    USER_SCHEMA + "." + USER_LOGIN +
                    " FROM " + DB_NAME + "." + OBJECTS_SCHEMA +
                    " INNER JOIN " + DB_NAME + "." + USER_SCHEMA +
                    " ON " + OBJECTS_SCHEMA + "." + OBJECT_USER_ID + "=" +
                    USER_SCHEMA + "." + USER_ID +
                    " AND " + OBJECTS_SCHEMA + "." + OBJECT_NAME + "=?" +
                    " ORDER BY " + OBJECTS_SCHEMA + "." + OBJECT_ID;

            try (PreparedStatement prepStmt = conn.prepareStatement(sql1)) {
                prepStmt.setString(1, nameOfObject);
                res = prepStmt.executeQuery();
                objects = new Vector<>();
                while (res.next()) {
                    count = 1;
                    int id_object = res.getInt(count++);
                    String name = res.getString(count++);
                    double cost = res.getDouble(count++);
                    int for_export = res.getInt(count++);
                    int for_import = res.getInt(count++);
                    String type = res.getString(count++);
                    int object_user_id = res.getInt(count++);
                    objects.add(new UserObjects(id_object, name, cost, for_export, for_import, type, object_user_id));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return objects;
    }
}
