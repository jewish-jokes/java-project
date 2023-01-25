package com.SQLsupport.strategies.updatable;

import com.SQLsupport.strategies.Updatable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.SQLsupport.Constants.*;

public class AddTime implements Updatable {
    private int user_id;
    private int objects_count;
    private double time;

    @Override
    public void getData(String data) {
        String[] dataFromClient = data.split(" ");
        time = Double.parseDouble(dataFromClient[0]);
        objects_count = Integer.parseInt(dataFromClient[1]);
        user_id = Integer.parseInt(dataFromClient[2]);
    }

    @Override
    public boolean executeUpdate(Connection conn) {
        int count = 1;
        try {
            String sql1 = "INSERT INTO " + DB_NAME + "." + TIME_AT_BORDER_SCHEMA + " (" +
                    TIME + "," +
                    OBJECTS_COUNT + "," +
                    USER_ID_TIME + ")" +
                    " VALUES (?,?,?);";
            try (PreparedStatement prepStmt = conn.prepareStatement(sql1)) {
                prepStmt.setDouble(count++, time);
                prepStmt.setInt(count++, objects_count);
                prepStmt.setInt(count++, user_id);
                return prepStmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {

        }
        return false;
    }
}
