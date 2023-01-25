package com.SQLsupport.strategies.updatable;

import com.SQLsupport.strategies.Updatable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.SQLsupport.Constants.*;

public class EditTypeTaxes implements Updatable {
    private double alcohol_taxes;
    private double cigarettes_taxes;
    private double fur_taxes;
    private double jewelry_taxes;
    private double other_taxes;

    @Override
    public void getData(String data) {
        int count = 0;
        String[] dataFromClient = data.split("@@@");
        alcohol_taxes = Double.parseDouble(dataFromClient[count++]);
        cigarettes_taxes = Double.parseDouble(dataFromClient[count++]);
        fur_taxes = Double.parseDouble(dataFromClient[count++]);
        jewelry_taxes = Double.parseDouble(dataFromClient[count++]);
        other_taxes = Double.parseDouble(dataFromClient[count++]);
    }

    @Override
    public boolean executeUpdate(Connection conn) {
        int count = 1;
        String sql1 = "UPDATE " + DB_NAME + "." + TYPE_TAXES_SCHEMA +
                " SET " +
                ALCOHOL_TAXES + "=?, " +
                CIGARETTES_TAXES + "=?, " +
                FUR_TAXES + "=?, " +
                JEWELRY_TAXES + "=?, " +
                OTHER_TAXES + "=? ";
        try (PreparedStatement prepStmt = conn.prepareStatement(sql1)) {
            prepStmt.setDouble(count++, alcohol_taxes);
            prepStmt.setDouble(count++, cigarettes_taxes);
            prepStmt.setDouble(count++, fur_taxes);
            prepStmt.setDouble(count++, jewelry_taxes);
            prepStmt.setDouble(count++, other_taxes);
            return prepStmt.executeUpdate() > 0;
        } catch (SQLException e) {

        }
        return false;
    }
}
