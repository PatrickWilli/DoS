/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.willi.dos.argsparser;

import org.apache.commons.cli.HelpFormatter;
import org.willi.dos.methods.SlowLoris;

/**
 *
 * @author phamm
 */
public class HelpPrinter
{
    private static HelpFormatter helpformatter;
    public static void printHelp()
    {
        helpformatter = new HelpFormatter();
        helpformatter.setOptionComparator(null); //Keine sortierung der optionen
        helpformatter.printHelp("DoS.jar [attack method] [target] [options]", "", Args.getArgs(), SlowLoris.printExampleUsage());
    }
}
