package com.example.mahavir_agro;

import java.sql.Time;
import java.util.Date;

public class NEWENTRY2 {
    private String noc, typeGrain, TruckNo, dor, tor;
    private double quanQuin, Bags;

    public NEWENTRY2(){
    }

    public NEWENTRY2(String noc, String typeGrain, String truckNo, String dor, String tor, double quanQuin, double bags) {
        this.noc = noc;
        this.typeGrain = typeGrain;
        TruckNo = truckNo;
        this.dor = dor;
        this.tor = tor;
        this.quanQuin = quanQuin;
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

    public String getTruckNo() {
        return TruckNo;
    }

    public void setTruckNo(String truckNo) {
        TruckNo = truckNo;
    }

    public double getQuanQuin() {
        return quanQuin;
    }

    public void setQuanQuin(double quanQuin) {
        this.quanQuin = quanQuin;
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

    public String getTor() {
        return tor;
    }

    public void setTor(String tor) {
        this.tor = tor;
    }
}