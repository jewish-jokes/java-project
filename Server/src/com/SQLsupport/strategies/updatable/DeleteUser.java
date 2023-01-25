package com.SQLsupport.strategies.updatable;

import com.SQLsupport.strategies.Updatable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.SQLsupport.Constants.*;

public class DeleteUser implements Updatable {
    private int id_user;

    @Override
    public void getData(String data) {
        id_user = Integer.parseInt(data);
    }

    @Override
    public boolean executeUpdate(Connection conn) {
        int count = 1;
        try {
            String sql1 = "DELETE FROM " + DB_NAME + "." + USER_SCHEMA +
                    " WHERE " + USER_ID + " =?";
            try (PreparedStatement prepStmt = conn.prepareStatement(sql1)) {
                prepStmt.setInt(count++, id_user);
                return prepStmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {

        }
        return false;
    }
}
