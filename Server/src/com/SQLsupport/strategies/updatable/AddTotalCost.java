package com.SQLsupport.strategies.updatable;

import com.SQLsupport.strategies.Updatable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.SQLsupport.Constants.*;
import static com.SQLsupport.Constants.USER_ID_REVIEWS;

public class AddTotalCost implements Updatable {
    private int object_id;
    private double total_cost;

    @Override
    public void getData(String data) {
        String[] dataFromClient = data.split(" ");
        total_cost = Double.parseDouble(dataFromClient[0]);
        object_id = Integer.parseInt(dataFromClient[1]);
    }

    @Override
    public boolean executeUpdate(Connection conn) {
        int count = 1;
        try {
            String sql1 = "INSERT INTO " + DB_NAME + "." + TOTAL_TAXES_SCHEMA + " (" +
                    ID_OBJECTS_TAXES + "," +
                    TOTAL_TAXES + ")" +
                    " VALUES (?,?);";
            try (PreparedStatement prepStmt = conn.prepareStatement(sql1)) {
                prepStmt.setInt(count++, object_id);
                prepStmt.setDouble(count++, total_cost);
                return prepStmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {

        }
        return false;
    }
}
