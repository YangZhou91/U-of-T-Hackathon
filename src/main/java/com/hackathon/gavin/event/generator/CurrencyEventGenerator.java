/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hackathon.gavin.event.generator;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.hackathon.gavin.currency.event.CurrencyEvent;
import com.hackathon.gavin.currency.event.CurrencyEventCreater;
import com.hackathon.gavin.event.listener.SellOrderListener;
import com.hackathon.gavin.statement.SellOrderStatement;
import java.text.ParseException;

/**
 *
 * @author Gavin
 */
public class CurrencyEventGenerator {
    
    public static void main(String [] args) throws ParseException{
        Configuration cepConfiguration = new Configuration().configure("esper.cfg.xml");
        EPServiceProvider cepProvider = EPServiceProviderManager.getDefaultProvider(cepConfiguration);
        EPRuntime cepRuntime = cepProvider.getEPRuntime();
        EPAdministrator cepAdmin = cepProvider.getEPAdministrator();
    
        SellOrderListener(cepAdmin);
        
        for(int i = 0; i < 50; i++){
            GenerateCurrencyTick(cepRuntime);
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    
    private static void GenerateCurrencyTick(EPRuntime cepRunTime) throws ParseException{
        CurrencyEventCreater currencyEventCreater = new CurrencyEventCreater();
        CurrencyEvent tick = currencyEventCreater.createCurrencyEvent();
        System.out.println(tick.toString());
        cepRunTime.sendEvent(tick);
    }
    
    public static void SellOrderListener(EPAdministrator cepAdmin){
        SellOrderStatement sellOrderStatement = new SellOrderStatement(cepAdmin);
        sellOrderStatement.addListener(new SellOrderListener());
    }
}
