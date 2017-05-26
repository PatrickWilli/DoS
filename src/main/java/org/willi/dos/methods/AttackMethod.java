/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.willi.dos.methods;

import java.util.Random;
import org.willi.dos.interfaces.Attack;

/**
 *
 * @author phamm
 */
public abstract class AttackMethod implements Attack
{
    private String host;
    private int threads;
    private int duration;
    private static String[] help = {"DoS.jar --slowloris --target 8.8.8.8 --connections 400 --duration 6", 
                                    "DoS.jar -sl -t www.google.com -c 500 -d 10", 
                                    "DoS.jar --http --target 8.8.8.8 --connections 400 --duration 6", 
                                    "DoS.jar -http -t www.google.com -c 500 -d 10"};
    private static Random rand = new Random();
    
    public static String printExampleUsage()
    {
        return help[rand.nextInt(help.length)];
    }
    
    public AttackMethod(String host, int threads, int duration)
    {
        this.host = host;
        this.threads = threads;
        this.duration = duration;
    }
    
       
    public String getHost()
    {
        return host;
    }
    
    public int getThreads()
    {
        return threads;
    }
    
    public int getDuration()
    {
        return duration;
    }

}
