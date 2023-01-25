package com.SQLsupport.strategies.selectable;

import com.SQLsupport.DBClass.CostTaxes;
import com.SQLsupport.DBClass.TypeTaxes;
import com.SQLsupport.strategies.Requestable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import static com.SQLsupport.Constants.TYPE_TAXES_SCHEMA;
import static com.SQLsupport.Constants.DB_NAME;

public class SelectAllTypeTaxes implements Requestable {
    @Override
    public void getData(String data) {

    }

    public Vector<TypeTaxes> executeSelect(Connection conn) {
        int count = 1;
        ResultSet res = null;
        Vector<TypeTaxes> typeTaxes = null;

        try {
            String sql1 = "SELECT * FROM " + DB_NAME + "." + TYPE_TAXES_SCHEMA;
            try (PreparedStatement prepStmt = conn.prepareStatement(sql1)) {
                res = prepStmt.executeQuery();
                typeTaxes = new Vector<>();
                while (res.next()) {
                    count = 1;
                    double alcohol_taxes = res.getDouble(count++);
                    double cigarettes_taxes = res.getDouble(count++);
                    double fur_taxes = res.getDouble(count++);
                    double jewelry_taxes = res.getDouble(count++);
                    double other_taxes = res.getDouble(count++);
                    typeTaxes.add(new TypeTaxes(alcohol_taxes, cigarettes_taxes, fur_taxes, jewelry_taxes, other_taxes));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return typeTaxes;
    }
}
