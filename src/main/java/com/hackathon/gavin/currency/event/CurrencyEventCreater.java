/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hackathon.gavin.currency.event;

import com.hackathon.gavin.string.parser.CurrencyRateParser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONArray;

/**
 *
 * @author Gavin
 */
public class CurrencyEventCreater {

    public CurrencyEventCreater() {
    }


    private static final String API_URL
            = "http://api-sandbox.oanda.com/v1/quote?instruments=EUR_USD";

    /**
     * This method is used to create currency object from parser
     * return CurrencyEvent
     */
    public static CurrencyEvent createCurrencyEvent() throws ParseException {
        
        JSONArray jSONArray = CurrencyRateParser.httpGetCall(API_URL, "prices");
        ArrayList arrayList = CurrencyRateParser.convertToArrayList(jSONArray);
        
        String INSTRUMENT = arrayList.get(0).toString();
        Date CURRENTTIME = parseRFC3339Date(arrayList.get(1).toString());
        double BID = Double.valueOf(arrayList.get(2).toString());
        double ASK = Double.valueOf(arrayList.get(3).toString());
        CurrencyEvent currencyEvent = new CurrencyEvent(INSTRUMENT, CURRENTTIME, BID, ASK);
        return currencyEvent;
    }

    /**
     * This method is used to convert RFC3339 format to normal date
     *
     * @param dateString in RFC3339 format
     * @return date in java.util.Date format
     * @throws java.text.ParseException
     * @throws IndexOutOfBoundsException
     */
    public static Date parseRFC3339Date(String dateString)
            throws java.text.ParseException, IndexOutOfBoundsException {

        Date currentDate = new Date();
        
        if (dateString.endsWith("Z")) {
            try {
                SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
                currentDate = s.parse(dateString);
            } catch (java.text.ParseException pe) {
                pe.printStackTrace();
            }
            return currentDate;
        }
        
        return currentDate;
    }
}
