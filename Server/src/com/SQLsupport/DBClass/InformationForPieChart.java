package com.SQLsupport.DBClass;

import java.io.Serializable;

public class InformationForPieChart implements Serializable {
    private String objName;
    private int objCount;

    public InformationForPieChart(String objName, int objCount) {
        this.objName = objName;
        this.objCount = objCount;
    }

    public String getObjName() {
        return objName;
    }

    public int getObjCount() {
        return objCount;
    }
}
