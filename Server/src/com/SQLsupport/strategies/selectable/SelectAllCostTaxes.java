package com.SQLsupport.strategies.selectable;

import com.SQLsupport.DBClass.CostTaxes;
import com.SQLsupport.strategies.Requestable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import static com.SQLsupport.Constants.DB_NAME;
import static com.SQLsupport.Constants.COST_TAXES_SCHEMA;

public class SelectAllCostTaxes implements Requestable {
    @Override
    public void getData(String data) {

    }

    public Vector<CostTaxes> executeSelect(Connection conn) {
        int count = 1;
        ResultSet res = null;
        Vector<CostTaxes> costTaxes = null;

        try {
            String sql1 = "SELECT * FROM " + DB_NAME + "." + COST_TAXES_SCHEMA;
            try (PreparedStatement prepStmt = conn.prepareStatement(sql1)) {
                res = prepStmt.executeQuery();
                costTaxes = new Vector<>();
                while (res.next()) {
                    count = 1;
                    double under_1000 = res.getDouble(count++);
                    double from_1000_to_10000 = res.getDouble(count++);
                    double over_10000 = res.getDouble(count++);
                    costTaxes.add(new CostTaxes(under_1000, from_1000_to_10000, over_10000));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return costTaxes;
    }
}
