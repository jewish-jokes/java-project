package com.SQLsupport.strategies.selectable;

import com.SQLsupport.DBClass.CostTaxes;
import com.SQLsupport.DBClass.ImportExportTaxes;
import com.SQLsupport.strategies.Requestable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import static com.SQLsupport.Constants.IMPORT_EXPORT_TAXES_SCHEMA;
import static com.SQLsupport.Constants.DB_NAME;

public class SelectAllExportImportTaxes implements Requestable {
    @Override
    public void getData(String data) {

    }

    public Vector<ImportExportTaxes> executeSelect(Connection conn) {
        int count = 1;
        ResultSet res = null;
        Vector<ImportExportTaxes> importExportTaxes = null;

        try {
            String sql1 = "SELECT * FROM " + DB_NAME + "." + IMPORT_EXPORT_TAXES_SCHEMA;
            try (PreparedStatement prepStmt = conn.prepareStatement(sql1)) {
                res = prepStmt.executeQuery();
                importExportTaxes = new Vector<>();
                while (res.next()) {
                    count = 1;
                    double export_taxes = res.getDouble(count++);
                    double import_taxes = res.getDouble(count++);
                    importExportTaxes.add(new ImportExportTaxes(export_taxes, import_taxes));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return importExportTaxes;
    }
}
