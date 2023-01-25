package com.SQLsupport.strategies.updatable;

import com.SQLsupport.strategies.Updatable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.SQLsupport.Constants.*;
import static com.SQLsupport.Constants.OBJECT_USER_ID;

public class AddReview implements Updatable {
    private int user_id;
    private String review_text;

    @Override
    public void getData(String data) {
        String[] dataFromClient = data.split(" ");
        review_text = dataFromClient[0];
        user_id = Integer.parseInt(dataFromClient[1]);
    }

    @Override
    public boolean executeUpdate(Connection conn) {
        int count = 1;
        try {
            String sql1 = "INSERT INTO " + DB_NAME + "." + REVIEWS_SCHEMA + " (" +
                    REVIEW_TEXT + "," +
                    USER_ID_REVIEWS + ")" +
                    " VALUES (?,?);";
            try (PreparedStatement prepStmt = conn.prepareStatement(sql1)) {
                prepStmt.setString(count++, review_text);
                prepStmt.setInt(count++, user_id);
                return prepStmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {

        }
        return false;
    }
}
