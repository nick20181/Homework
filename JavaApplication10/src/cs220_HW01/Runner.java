package cs220_HW01;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Nicholas Bohm
 */
public class Runner {
    private Scanner input = new Scanner(System.in);
    
    public Runner() {
        ResponseHandler controller = new ResponseHandler();
        System.out.println("Welcome to Blackburn Computer Science File Server");
        SocketHandler blackburn = new SocketHandler("10.100.1.239", 255);
        
        
        System.out.println(controller.handleReponse(blackburn.sendMsg(""),
                blackburn.inBound() + blackburn.inBound()));
        System.out.println("Enter a number to download or 0 to exit");
    }

    public static void main(String[] args) {
        new Runner();
    }

}
