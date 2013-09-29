/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hackathon.gavin.account;

/**
 *
 * @author Gavin
 */
public class MyAccount {
    int ID;
    int units;
    String instrument;
    double price;

    public MyAccount(int ID, int units, String instrument, double price) {
        this.ID = ID;
        this.units = units;
        this.instrument = instrument;
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public int getUnits() {
        return units;
    }

    public String getInstrument() {
        return instrument;
    }

    public double getPrice() {
        return price;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}
