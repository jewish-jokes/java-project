package com.SQLsupport.strategies.selectable;

import com.SQLsupport.DBClass.InformationForPieChart;
import com.SQLsupport.strategies.Requestable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import static com.SQLsupport.Constants.*;
import static com.SQLsupport.Constants.OBJECTS_SCHEMA;

public class SelectDataForPieChart implements Requestable {
    private String user_id;

    @Override
    public void getData(String data) {
        user_id = data;
    }

    public Vector<InformationForPieChart> execute(Connection conn) {

        ResultSet res;
        Vector<InformationForPieChart> informationForPieCharts = new Vector<>();

        int max = getObjectsCount(conn);
        int currentNumber = 0;
        String name = "";
        int count = 1;
        int fur_count = 0, alcohol_count = 0, other_count = 0, cigarettes_count = 0, jewelry_count = 0;
        String sql1 = "SELECT " + OBJECTS_SCHEMA + "." + OBJECT_TYPE +
                " FROM " + DB_NAME + "." + OBJECTS_SCHEMA +
                " INNER JOIN " + DB_NAME + "." + USER_SCHEMA +
                " ON " + OBJECTS_SCHEMA + "." + OBJECT_USER_ID + "=" + USER_SCHEMA + "." + USER_ID +
                " WHERE " + USER_SCHEMA + "." + USER_ID + "=?";

        try (PreparedStatement prepStmt = conn.prepareStatement(sql1)) {
            prepStmt.setInt(1, Integer.parseInt(user_id));
            res = prepStmt.executeQuery();
            while (currentNumber < max) {
                if (res.next()) {
                    name = res.getString(1);
                    //informationForPieCharts.add(new InformationForPieChart(name, count));
                    if (name.equals("Мех")) fur_count++;
                    if (name.equals("Алкоголь")) alcohol_count++;
                    if (name.equals("Другое")) other_count++;
                    if (name.equals("Табак")) cigarettes_count++;
                    if (name.equals("Украшения")) jewelry_count++;
                }
                currentNumber++;
            }
            if (fur_count > 0) informationForPieCharts.add(new InformationForPieChart("Мех", fur_count));
            if (alcohol_count > 0) informationForPieCharts.add(new InformationForPieChart("Алкоголь", alcohol_count));
            if (other_count > 0) informationForPieCharts.add(new InformationForPieChart("Другое", other_count));
            if (cigarettes_count > 0)
                informationForPieCharts.add(new InformationForPieChart("Табак", cigarettes_count));
            if (jewelry_count > 0) informationForPieCharts.add(new InformationForPieChart("Украшения", jewelry_count));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return informationForPieCharts;
    }

    public int getObjectsCount(Connection conn) {
        ResultSet res;
        String sql1 = "SELECT COUNT(*) FROM " + DB_NAME + "." + OBJECTS_SCHEMA +
                " INNER JOIN " + DB_NAME + "." + USER_SCHEMA +
                " ON " + OBJECTS_SCHEMA + "." + OBJECT_USER_ID + "=" + USER_SCHEMA + "." + USER_ID +
                " WHERE " + USER_SCHEMA + "." + USER_ID + "=?";
        try (PreparedStatement prepStmt = conn.prepareStatement(sql1)) {
            prepStmt.setInt(1, Integer.parseInt(user_id));
            res = prepStmt.executeQuery();
            if (res.next()) return res.getInt(1);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return 0;
    }
}
