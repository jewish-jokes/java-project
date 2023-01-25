package com.SQLsupport.DBClass;

import java.io.Serializable;

public class CostTaxes implements Serializable {
    private double under_1000;
    private double from_1000_to_10000;
    private double over_10000;

    public CostTaxes(double under_1000, double from_1000_to_10000, double over_10000){
        this.under_1000 = under_1000;
        this.from_1000_to_10000 = from_1000_to_10000;
        this.over_10000 = over_10000;
    }

    public double getFrom_1000_to_10000() {
        return from_1000_to_10000;
    }

    public double getOver_10000() {
        return over_10000;
    }

    public double getUnder_1000() {
        return under_1000;
    }
}
