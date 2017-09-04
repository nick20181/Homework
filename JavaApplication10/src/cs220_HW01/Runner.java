/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs220_HW01;

import java.io.*;

/**
 *
 * @author Nicholas Bohm
 */
public class Runner {
    
    public Runner(){
        System.out.println("Runner Start ...");
        SocketHandler blackburn = new SocketHandler("10.100.1.239", 255);
            System.out.println(blackburn.getMessageRecived());
        
    }
    
    
    public static void main(String[] args) {
        new Runner();
    }
}
