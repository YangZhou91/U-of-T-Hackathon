/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hackathon.gavin.event.listener;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.hackathon.gavin.account.MyAccount;
import com.hackathon.gavin.account.MyAccountGenerator;
import com.hackathon.gavin.trade.DeleteTrade;
import com.hackathon.gavin.trade.OpenTrade;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gavin
 */
public class SellOrderListener implements UpdateListener {
    
    
    public void update(EventBean[] newEvents, EventBean[] oldEvents) {
        System.out.println("------Alert!, Sell your product-------");
        try {
            MyAccount myAccount = MyAccountGenerator.createMyAccount();
            DeleteTrade.Delete(myAccount);
        } catch (Exception ex) {
            Logger.getLogger(SellOrderListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            OpenTrade.sendPost();
        } catch (Exception e) {
        }
    }
}
