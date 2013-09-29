/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hackathon.gavin.trade;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author Gavin
 */
public class OpenTrade {
    
    private static final String USER_AGENT = "Mozilla/5.0";

    public static void main(String [] args) throws Exception{
        OpenTrade http = new OpenTrade();
        OpenTrade.sendPost();
    }
    public OpenTrade() {
    }
    
    public static void sendPost() throws Exception{
        String url = "http://api-sandbox.oanda.com/v1/accounts/2311905/trades";
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection)obj.openConnection();
    
        //add request header
        connection.setRequestMethod("POST");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        
        String urlParameters = "instrument=EUR_USD&units=1000&side=buy";
    
        //Send post request
        connection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
        
        int responseCode = connection.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);
        
        BufferedReader in = new BufferedReader(
            new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        
        while((inputLine = in.readLine()) != null){
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());
    }
}
