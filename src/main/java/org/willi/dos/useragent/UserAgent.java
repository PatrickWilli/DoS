/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.willi.dos.useragent;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author phamm
 */
public class UserAgent
{
    private static ArrayList<String> useragents = new UserAgentReader().getUserAgents();
    private static Random r = new Random();
    
    public static String getUserAgent()
    {
        return useragents.get(r.nextInt(useragents.size()));        
    }

}
