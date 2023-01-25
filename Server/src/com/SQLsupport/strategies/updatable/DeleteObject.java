package com.SQLsupport.strategies.updatable;

import com.SQLsupport.strategies.Updatable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.SQLsupport.Constants.*;

public class DeleteObject implements Updatable {
    private int id_object;
    private int object_user_id;

    @Override
    public void getData(String data) {
        id_object = Integer.parseInt(data);
    }

    @Override
    public boolean executeUpdate(Connection conn) {
        int count = 1;
        try {
            String sql1 = "DELETE FROM " + DB_NAME + "." + OBJECTS_SCHEMA +
                    " WHERE " + OBJECT_ID + " =?";
                    //"AND WHERE " + OBJECT_USER_ID + " =?";
            try (PreparedStatement prepStmt = conn.prepareStatement(sql1)) {
                prepStmt.setInt(count++, id_object);
                return prepStmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {

        }
        return false;
    }
}
