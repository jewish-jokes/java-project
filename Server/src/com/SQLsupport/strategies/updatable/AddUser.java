package com.SQLsupport.strategies.updatable;

import com.SQLsupport.strategies.Updatable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.SQLsupport.Constants.*;

public class AddUser implements Updatable {
    private String login, password;
    int  role;

    public AddUser() {
    }

    @Override
    public void getData(String data) {
        String[] transformedData = data.split(" ");
        login = transformedData[0];
        password = transformedData[1];
        role = Integer.parseInt(transformedData[2]);
    }

    @Override
    public boolean executeUpdate(Connection conn) {
        int count = 1;
        try {
            String sql1 = "INSERT INTO " + DB_NAME + "." + USER_SCHEMA + " (" +
                    USER_LOGIN + "," + USER_PASSWORD + "," + USER_ROLE+ ")" + " VALUES (?,?,?)";
            try (PreparedStatement prepStmt = conn.prepareStatement(sql1)) {
                prepStmt.setString(count++, login);
                prepStmt.setString(count++, password);
                prepStmt.setInt(count++, role);
                return prepStmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {

        }
        return false;
    }
}
