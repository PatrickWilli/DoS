/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.willi.dos.useragent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author phamm
 */
public class UserAgentReader
{
    
    private BufferedReader readUserAgentResource()
    {
        InputStream stream = this.getClass().getResourceAsStream("/useragents.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        return br;
    }
    
    
    public ArrayList<String> getUserAgents()
    {
        try
        {
            ArrayList<String> useragents = new ArrayList<String>();
            BufferedReader br = readUserAgentResource();
            String line = "";
            while((line = br.readLine()) != null)
            {
               useragents.add(line);
            }
            return useragents;
        } 
        catch (IOException ex)
        {
            System.err.println("Cannot read line from BufferedReader");
            return null;
        }
    }
    
}
