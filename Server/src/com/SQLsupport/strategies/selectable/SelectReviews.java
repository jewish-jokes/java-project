package com.SQLsupport.strategies.selectable;

import com.SQLsupport.DBClass.CostTaxes;
import com.SQLsupport.strategies.Requestable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import static com.SQLsupport.Constants.*;

public class SelectReviews implements Requestable {
    @Override
    public void getData(String data) {

    }

    public Vector<String> executeSelect(Connection conn) {
        ResultSet res = null;
        Vector<String> reviews = null;

        try {
            String sql1 = "SELECT * FROM " + DB_NAME + "." + REVIEWS_SCHEMA;
            try (PreparedStatement prepStmt = conn.prepareStatement(sql1)) {
                res = prepStmt.executeQuery();
                reviews = new Vector<>();
                while (res.next()) {
                    String review_text = res.getString(2);
                    reviews.add(review_text);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reviews;
    }
}
