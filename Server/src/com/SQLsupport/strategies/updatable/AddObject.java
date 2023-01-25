package com.SQLsupport.strategies.updatable;

import com.SQLsupport.strategies.Updatable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.SQLsupport.Constants.*;

public class AddObject implements Updatable {
    private int id_object;
    private String name;
    private double cost;
    private int for_export;
    private int for_import;
    private String type;
    private int object_user_id;

    @Override
    public void getData(String data) {
        int i = 0;
        String[] dataFromClient = data.split("@@@");
        //id_object = Integer.parseInt(dataFromClient[i++]);
        name = dataFromClient[i++];
        cost = Double.parseDouble(dataFromClient[i++]);
        for_export = Integer.parseInt(dataFromClient[i++]);
        for_import = Integer.parseInt(dataFromClient[i++]);
        type = dataFromClient[i++];
        object_user_id = Integer.parseInt(dataFromClient[i++]);
    }

    @Override
    public boolean executeUpdate(Connection conn) {
        int count = 1;
        try {
            String sql1 = "INSERT INTO " + DB_NAME + "." + OBJECTS_SCHEMA + " (" +
                    //OBJECT_ID + "," +
                    OBJECT_NAME + "," +
                    OBJECT_COST + "," +
                    OBJECT_FOR_EXPORT + "," +
                    OBJECT_FOR_IMPORT + "," +
                    OBJECT_TYPE + "," +
                    OBJECT_USER_ID + ")" +
                    " VALUES (?,?,?,?,?,?);";
            try (PreparedStatement prepStmt = conn.prepareStatement(sql1)) {
                //prepStmt.setInt(count++, id_object);
                prepStmt.setString(count++, name);
                prepStmt.setDouble(count++, cost);
                prepStmt.setInt(count++, for_export);
                prepStmt.setInt(count++, for_import);
                prepStmt.setString(count++, type);
                prepStmt.setInt(count++, object_user_id);
                return prepStmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {

        }
        return false;
    }
}
