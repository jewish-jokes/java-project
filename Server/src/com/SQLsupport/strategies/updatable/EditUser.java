package com.SQLsupport.strategies.updatable;

import com.SQLsupport.strategies.Updatable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.SQLsupport.Constants.*;

public class EditUser implements Updatable {
    private String login, password;
    private int id;

    @Override
    public void getData(String data) {
        int count = 0;
        String[] dataFromClient = data.split("@@@");
        id = Integer.parseInt(dataFromClient[count++]);
        login = dataFromClient[count++];
        password = dataFromClient[count++];
    }

    @Override
    public boolean executeUpdate(Connection conn) {
        int count = 1;
        //UPDATE `border_taxes`.`user` SET `login` = '44' WHERE (`id_user` = '11');
        String sql1 = "UPDATE " + DB_NAME + "." + USER_SCHEMA +
                " SET " +
                USER_LOGIN + "=?, " +
                USER_PASSWORD + "=? " +
                " WHERE " + USER_ID + "=?;";
        try (PreparedStatement prepStmt = conn.prepareStatement(sql1)) {
            prepStmt.setString(count++, login);
            prepStmt.setString(count++, password);
            prepStmt.setInt(count++, id);
            return prepStmt.executeUpdate() > 0;
        } catch (SQLException e) {

        }
        return false;
    }
}
