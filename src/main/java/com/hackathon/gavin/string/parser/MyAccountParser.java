/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hackathon.gavin.string.parser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Gavin
 */
public class MyAccountParser {
    
    public static void main(String[] args){
        String urlString = "http://api-sandbox.oanda.com/v1/accounts/2311905/trades?instrument=EUR_USD";
        String jsonArrayName = "trades";
        convertToArrayList(httpGetCall(urlString, jsonArrayName));
    }
    
    public static JSONArray httpGetCall(String urlString, String jsonArrayName) {
        JSONArray result = null;
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(urlString);

        try {
            HttpResponse getResponse = client.execute(request);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            getResponse.getEntity().getContent(), "UTF-8"));
            StringBuilder builder = new StringBuilder();
            for (String line = null; (line = reader.readLine()) != null;) {
                builder.append(line).append("\n");
            }
            //result = new JSONObject(builder.toString()).getJSONArray(jsonArrayName);
            JSONObject obj = new JSONObject(builder.toString());
            result = obj.getJSONArray(jsonArrayName);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return result;
    }
    
        public static ArrayList convertToArrayList(JSONArray orginalData) {
        ArrayList<String> allElements = new ArrayList<String>();
        for (int i = 0; i < orginalData.length(); i++) {
            try {
                JSONObject obj = orginalData.getJSONObject(i);
                allElements.add(Integer.toString(obj.getInt("id")));
                allElements.add(Integer.toString(obj.getInt("units")));
                allElements.add(obj.getString("instrument"));
                allElements.add(String.valueOf(obj.getDouble("price")));
                System.out.println(allElements);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return allElements;
    }
}
