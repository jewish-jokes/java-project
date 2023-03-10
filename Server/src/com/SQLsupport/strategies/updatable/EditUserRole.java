package com.SQLsupport.strategies.updatable;

import com.SQLsupport.strategies.Updatable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.SQLsupport.Constants.*;
import static com.SQLsupport.Constants.USER_ID;

public class EditUserRole implements Updatable {
    private int id, role;

    @Override
    public void getData(String data) {
        String[] dataFromClient = data.split(" ");
        id = Integer.parseInt(dataFromClient[0]);
        role = Integer.parseInt(dataFromClient[1]);
    }

    @Override
    public boolean executeUpdate(Connection conn) {
        String sql1 = "UPDATE " + DB_NAME + "." + USER_SCHEMA +
                " SET " + USER_ROLE + "=? WHERE " + USER_ID + "=?;";
        try (PreparedStatement prepStmt = conn.prepareStatement(sql1)) {
            prepStmt.setInt(1, role);
            prepStmt.setInt(2, id);
            return prepStmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
