package com.SQLsupport.DBClass;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class UserObjects implements Serializable {
    private int id_object;
    private String name;
    private double cost;
    private int for_export;
    private int for_import;
    private String type;
    private int object_user_id;

    public UserObjects(UserObjects that){
        this.id_object = that.id_object;
        this.name = that.name;
        this.cost = that.cost;
        this.for_export = that.for_export;
        this.for_import = that.for_import;
        this.type = that.type;
        this.object_user_id = that.object_user_id;
    }

    public UserObjects(int id_object, String name, double cost, int for_export, int for_import, String type, int object_user_id){
        this.id_object = id_object;
        this.name = name;
        this.cost = cost;
        this.for_export = for_export;
        this.for_import = for_import;
        this.type = type;
        this.object_user_id = object_user_id;
    }

    public int getId_object() {
        return id_object;
    }

    public double getCost() {
        return cost;
    }

    public int getObject_user_id() {
        return object_user_id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getFor_export(){
        return for_export;
    }


    public int getFor_import(){
        return for_import;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setForExport(int for_export) {
        this.for_export = for_export;
    }

    public void setForImport(int for_import) {
        this.for_import = for_import;
    }

    public void setId_object(int id_object) {
        this.id_object = id_object;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setObject_user_id(int object_user_id) {
        this.object_user_id = object_user_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void print(FileWriter writer) {
        try {
            writer.write("id: " + id_object + "\nname: " + name + "\ncost: " + cost + "\nfor_export: " + for_export + "\nfor_import: " + for_import + "\ntype: " + type +"\n\n");
        } catch (IOException e) {
            System.out.printf("\nCannot write objects in file\n");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "id: " + id_object + " name: " + name + " cost: " + cost + " for_export: " + for_export + " for_import: " + for_import + ".";
    }

    public int getTypeObjectsCount() {
        return 0;
    }
}
