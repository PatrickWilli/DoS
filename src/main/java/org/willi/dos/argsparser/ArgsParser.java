/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.willi.dos.argsparser;


import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.willi.dos.methods.SlowLoris;
import org.apache.commons.lang.StringUtils;
import org.willi.dos.methods.HTTPFlood;
import org.willi.dos.methods.UDPFlood;

/**
 *
 * @author phamm
 */

public class ArgsParser
{

    private static String target = "";
    private static int connections = 0;
    private static int duration = 0;

    public static void main(String[] args)
    {
       new InitHelper();

        if (args == null || args.length < 1)
        {
            HelpPrinter.printHelp();
            return;
        } else
        {
            parseArgs(args);
        }

    }

    private static void parseArgs(String[] args)
    {
        CommandLineParser parser = new DefaultParser();
        try
        {
            CommandLine line = parser.parse(Args.getArgs(), args);
            analyzeCommadline(line);
        } catch (ParseException ex)
        {
            System.err.println(ex.getMessage());
            return;
        }
    }

    private static void analyzeCommadline(CommandLine line)
    {
        if (line.hasOption("slowloris"))
        {
            //isSlowLoris = true;
            AttackMethod.isSlowLoris = true;
        }
        if (line.hasOption("http"))
        {
            AttackMethod.isHTTP = true;
        }
        if (line.hasOption("udp"))
        {
            AttackMethod.isUDP = true;
        }
        if (AttackMethod.isMoreThenOneSelected())
        {
            System.err.println("Please provide only one attack method");
            return;
        }
        if (line.hasOption("target"))
        {
            target = line.getOptionValue("target");
        } 
        else
        {
            System.err.println("You have to provide a target");
            System.exit(0);
        }
        if (line.hasOption("connections"))
        {
            if(StringUtils.isNumeric(line.getOptionValue("connections")))
            {
                connections = Integer.parseInt(line.getOptionValue("connections"));
            }
            else
            {
                System.err.println("connection amount has to be a number");
            }
        } 
        else
        {
            System.err.println("You have to provide connections");
            return;
        }
        if (line.hasOption("duration"))
        {
            if(StringUtils.isNumeric(line.getOptionValue("duration")))
            {
                duration = Integer.parseInt(line.getOptionValue("duration"));
            }
            else
            {
                System.err.println("duration has to be a number");
            }
        } 
        else
        {
            System.err.println("You have to provide the duration");
            return;
        }
        if(AttackMethod.isSlowLoris)
        {
            if(target != null && connections != 0 && duration != 0)
            {
                SlowLoris slowloris = new SlowLoris(target, connections, duration);
                slowloris.startAttack();
                System.out.println("Starting attack on " + target);
            }
            else
            {
                System.err.println("Please check your args");
            }
            
        }
        if(AttackMethod.isUDP)
        {
            if(target != null && connections != 0 && duration != 0)
            {
                UDPFlood udpflood = new UDPFlood(target, connections, duration);
                udpflood.startAttack();
                System.out.println("Starting attack on " + target);
            }
            else
            {
                System.out.println("Please check your args!");
            }
        }
        if(AttackMethod.isHTTP)
        {
            if(target != null && connections != 0 && duration != 0)
            {
                HTTPFlood httpflood = new HTTPFlood(target, connections, duration);
                httpflood.startAttack();
                System.out.println("Starting attack on " + target);
            }
            else
            {
                System.out.println("Please check your args!");
            }
        }

    }

}
