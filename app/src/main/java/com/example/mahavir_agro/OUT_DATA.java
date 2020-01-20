package com.example.mahavir_agro;

import java.sql.Time;
import java.util.Date;

public class OUT_DATA {
    private String noc, typeGrain, TruckNo, dor, driver_name,vehicle_num;
    private double Bags;

    public OUT_DATA(){
    }

    public OUT_DATA(String noc, String typeGrain, String truckNo, String dor, String driver_name, String vehicle_num, double bags) {
        this.noc = noc;
        this.typeGrain = typeGrain;
        TruckNo = truckNo;
        this.dor = dor;
        this.driver_name = driver_name;
        this.vehicle_num = vehicle_num;
        Bags = bags;
    }

    public String getNoc() {
        return noc;
    }

    public void setNoc(String noc) {
        this.noc = noc;
    }

    public String getTypeGrain() {
        return typeGrain;
    }

    public void setTypeGrain(String typeGrain) {
        this.typeGrain = typeGrain;
    }

    public String getVehicle_num() {
        return vehicle_num;
    }

    public void setVehicle_num(String truckNo) {
        TruckNo = truckNo;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public double getBags() {
        return Bags;
    }

    public void setBags(double bags) {
        Bags = bags;
    }

    public String getDor() {
        return dor;
    }

    public void setDor(String dor) {
        this.dor = dor;
    }
}