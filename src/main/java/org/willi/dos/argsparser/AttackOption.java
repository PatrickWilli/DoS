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
public class AttackOption
{
    public static boolean isSlowLoris = false; // sl & http, sl & udp
    public static boolean isHTTP = false; //http & slowloris, http & udp
    public static boolean isUDP = false; //udp & http, udp & slowloris
    
    public static boolean isMoreThenOneSelected()
    {
        if((isHTTP && isSlowLoris) || (isHTTP && isUDP) || (isSlowLoris && isUDP) || (isUDP && isHTTP))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
