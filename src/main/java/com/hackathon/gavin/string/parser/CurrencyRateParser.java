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

public class CurrencyRateParser {

    String urlString;

    public CurrencyRateParser() {
    }

    /**
     * This method is used to retrieve data(prices) from the URL and store in
     * JSONArray.
     *
     * @param urlString address of API
     * @param jsonArrayName stored values with retrieved data
     * @return
     */
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

    /**
     * This method is used to save data in to an array list
     *
     * @param orginalData input of retrieved data
     * @return a Array List version of data
     */
    public static ArrayList convertToArrayList(JSONArray orginalData) {
        ArrayList<String> allElements = new ArrayList<String>();
        for (int i = 0; i < orginalData.length(); i++) {
            try {
                JSONObject obj = orginalData.getJSONObject(i);
                allElements.add(obj.getString("instrument"));
                allElements.add(obj.getString("time"));
                allElements.add(String.valueOf(obj.getDouble("bid")));
                allElements.add(String.valueOf(obj.getDouble("ask")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return allElements;
    }
}
