/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hackathon.gavin.trade;

import com.hackathon.gavin.account.MyAccount;
import com.hackathon.gavin.account.MyAccountGenerator;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 *
 * @author Gavin
 */
public class DeleteTrade {

    private final String USER_AGENT = "Mozilla/5.0";

    public DeleteTrade() {
       
    }
    
    
    public static void Delete(MyAccount myAccount) throws Exception{
        String url = "http://api-sandbox.oanda.com/v1/accounts/2311905/trades/"+Integer.toString(myAccount.getID());
        System.out.println(url);
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection)obj.openConnection();
        
        //Send Delete Request
        connection.setDoOutput(true);
        
        //add request header
        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        
        connection.connect();
        System.out.println(connection.getResponseCode());
    }
}
