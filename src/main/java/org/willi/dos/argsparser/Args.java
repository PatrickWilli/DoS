/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.willi.dos.argsparser;

/**
 *
 * @author phamm
 */
public enum Args
{
    UDP("UDP"),
    TCP("TCP"),
    SLOWLORIS("SLOWLORIS"),
    HTTP("HTTP");
    
    private String args;
    private Args(String args)
    {
        this.args = args;
    }
}