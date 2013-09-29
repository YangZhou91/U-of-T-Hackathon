/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hackathon.gavin.account;

import com.hackathon.gavin.string.parser.MyAccountParser;
import java.util.ArrayList;
import org.json.JSONArray;

/**
 *
 * @author Gavin
 */
public class MyAccountGenerator {
     static String urlString = "http://api-sandbox.oanda.com/v1/accounts/2311905/trades?instrument=EUR_USD";
     static String jsonArrayName = "trades";
    
     
     public static MyAccount createMyAccount(){
         JSONArray jSONArray = MyAccountParser.httpGetCall(urlString, jsonArrayName);
         ArrayList arrayList = MyAccountParser.convertToArrayList(jSONArray);
         
         int ID = Integer.valueOf(arrayList.get(0).toString());
         int UNITS = Integer.valueOf(arrayList.get(1).toString());
         String INSTRUMENT = arrayList.get(2).toString();
         double PRICE = Double.valueOf(arrayList.get(3).toString());
         MyAccount myAccount = new MyAccount(ID, UNITS, INSTRUMENT, PRICE);
         return myAccount;
     }
}
