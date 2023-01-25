package com.SQLsupport.DBClass;

import java.io.Serializable;

public class ImportExportTaxes implements Serializable {
   private double export_taxes;
   private double import_taxes;

   public ImportExportTaxes(double export_taxes, double import_taxes){
       this.export_taxes = export_taxes;
       this.import_taxes = import_taxes;
   }

    public double getExport_taxes() {
        return export_taxes;
    }

    public double getImport_taxes() {
        return import_taxes;
    }

    public void setExport_taxes(double export_taxes) {
        this.export_taxes = export_taxes;
    }

    public void setImport_taxes(double import_taxes) {
        this.import_taxes = import_taxes;
    }
}
