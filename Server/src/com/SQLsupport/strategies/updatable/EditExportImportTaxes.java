package com.SQLsupport.strategies.updatable;

import com.SQLsupport.strategies.Updatable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.SQLsupport.Constants.*;

public class EditExportImportTaxes implements Updatable {
    private double export_taxes;
    private double import_taxes;

    @Override
    public void getData(String data) {
        int count = 0;
        String[] dataFromClient = data.split("@@@");
        export_taxes = Double.parseDouble(dataFromClient[count++]);
        import_taxes = Double.parseDouble(dataFromClient[count++]);
    }

    @Override
    public boolean executeUpdate(Connection conn) {
        int count = 1;
        String sql1 = "UPDATE " + DB_NAME + "." + IMPORT_EXPORT_TAXES_SCHEMA +
                " SET " +
                EXPORT_TAXES + "=?, " +
                IMPORT_TAXES + "=? ";
        try (PreparedStatement prepStmt = conn.prepareStatement(sql1)) {
            prepStmt.setDouble(count++, export_taxes);
            prepStmt.setDouble(count++, import_taxes);
            return prepStmt.executeUpdate() > 0;
        } catch (SQLException e) {

        }
        return false;
    }
}
