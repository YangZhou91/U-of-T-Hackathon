/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hackathon.gavin.currency.event;

import java.util.Date;

/**
 *
 * @author Gavin
 */
public class CurrencyEvent {
    String instrument;
    Date currentTime;
    double bid;
    double ask;

    public CurrencyEvent(String instrument, Date currentTime, double bid, double ask) {
        this.instrument = instrument;
        this.currentTime = currentTime;
        this.bid = bid;
        this.ask = ask;
    }

    public String getInstrument() {
        return instrument;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public double getBid() {
        return bid;
    }

    public double getAsk() {
        return ask;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }

    @Override
    public String toString() {
        return "instrument: " + instrument + ", currentTime:" + currentTime + ", bid: " + bid + ", ask: " + ask;
    }
    
    
}
