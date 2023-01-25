package com.SQLsupport.DBClass;

import java.io.Serializable;

public class TypeTaxes implements Serializable {
    private double alcohol_taxes;
    private double cigarettes_taxes;
    private double fur_taxes;
    private double jewelry_taxes;
    private double other_taxes;

    public TypeTaxes(double alcohol_taxes, double cigarettes_taxes, double fur_taxes, double jewelry_taxes, double other_taxes){
        this.alcohol_taxes = alcohol_taxes;
        this.fur_taxes = fur_taxes;
        this.cigarettes_taxes = cigarettes_taxes;
        this.jewelry_taxes = jewelry_taxes;
        this.other_taxes = other_taxes;
    }

    public double getAlcoholTaxes() {
        return alcohol_taxes;
    }

    public double getCigarettesTaxes() {
        return cigarettes_taxes;
    }

    public double getFurTaxes() {
        return fur_taxes;
    }

    public double getJewelryTaxes() {
        return jewelry_taxes;
    }

    public double getOtherTaxes() {
        return other_taxes;
    }

    public void setAlcoholTaxes(double alcohol_taxes) {
        this.alcohol_taxes = alcohol_taxes;
    }

    public void setCigarettesTaxes(double cigarettes_taxes) {
        this.cigarettes_taxes = cigarettes_taxes;
    }

    public void setFurTaxes(double fur_taxes) {
        this.fur_taxes = fur_taxes;
    }

    public void setJewelryTaxes(double jewelry_taxes) {
        this.jewelry_taxes = jewelry_taxes;
    }

    public void setOtherTaxes(double other_taxes) {
        this.other_taxes = other_taxes;
    }
}
