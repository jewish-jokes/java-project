package com.SQLsupport.strategies.updatable;

import com.SQLsupport.strategies.Updatable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.SQLsupport.Constants.*;

public class EditCostTaxes implements Updatable {
    private double under_1000;
    private double from_1000_to_10000;
    private double over_10000;

    @Override
    public void getData(String data) {
        int count = 0;
        String[] dataFromClient = data.split("@@@");
        under_1000 = Double.parseDouble(dataFromClient[count++]);
        from_1000_to_10000 = Double.parseDouble(dataFromClient[count++]);
        over_10000 = Double.parseDouble(dataFromClient[count++]);
    }

    @Override
    public boolean executeUpdate(Connection conn) {
        int count = 1;
        String sql1 = "UPDATE " + DB_NAME + "." + COST_TAXES_SCHEMA +
                " SET " +
                UNDER_1000 + "=?, " +
                FROM_1000_TO_10000 + "=?, " +
                OVER_10000 + "=? ";
        try (PreparedStatement prepStmt = conn.prepareStatement(sql1)) {
            prepStmt.setDouble(count++, under_1000);
            prepStmt.setDouble(count++, from_1000_to_10000);
            prepStmt.setDouble(count++, over_10000);
            return prepStmt.executeUpdate() > 0;
        } catch (SQLException e) {

        }
        return false;
    }
}
