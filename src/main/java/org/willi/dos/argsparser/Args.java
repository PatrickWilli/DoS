/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.willi.dos.argsparser;

import org.apache.commons.cli.Options;

/**
 *
 * @author phamm
 */
public class Args
{
    private static Options options;
    
    public static void setArgs(Options op)
    {
        options = op;
    }
    
    public static Options getArgs()
    {
        return options;
    }
}
