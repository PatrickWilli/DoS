/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.willi.dos.argsparser;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

/**
 *
 * @author phamm
 */
public class InitHelper
{
    private Options options;
    
    public InitHelper()
    {
        options = new Options();
        initArgs();
    }
    
    private void initArgs()
    {
        options.addOption("sl", "slowloris", false, "Uses SlowLoris as attacking method");
        options.addOption("udp", "udp", false, "Uses UDP as attacking method");
        options.addOption("http", "http", false, "Uses HTTP Flooding as attacking method");
        options.addOption("t", "target", true, "The to attacking target. Can also be a domain");
        options.addOption("c", "connections", true, "How many connections should be opened");
        options.addOption("d", "duration", true, "duration of one connection (in seconds)");
        Args.setArgs(options);
    }
}
