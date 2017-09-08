package cs220_HW01;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Nicholas Bohm
 */
public class Runner {

    private Scanner input = new Scanner(System.in);
    private String userInput;
    private TextHandler text = new TextHandler();
    
    public Runner(int num) {
        SocketHandler blackburn = new SocketHandler("10.100.1.239", 255);
        
        if(blackburn.revceiveString().equalsIgnoreCase("00 HELLO")){
            System.out.println("Connection Established!");
        } else {
            System.out.println("Connection Refused!");
            System.exit(0);
        }

        blackburn.sendCommand("LIST");
        if(!blackburn.revceiveString().contains("01 OK")){
            System.out.println("Error Please try again!");
            System.exit(0);
        }

        String[] s = blackburn.revceiveString().split(", ");
        System.out.println("Welcome to Blackburn's File server!");
        for (int i = 0; i != s.length; i++) {
            System.out.println((i + 1) + ". " + s[i]);
        }
        System.out.println("Please enter a number or 0 to exit");
        this.userInput = input.next();

        if (Integer.parseInt(this.userInput) != 0) {
            blackburn.sendCommand("GET " + s[Integer.parseInt(this.userInput) - 1]);
        }
        String msg = blackburn.revceiveString();
        String newmsg = msg.charAt(6) + "";
        for (int x = 7; x != msg.length(); x++) {
            newmsg = newmsg + msg.charAt(x);
        }

        blackburn.reciveFile(Integer.parseInt(newmsg), s[Integer.parseInt(this.userInput) - 1]);
        System.out.println("");

        blackburn.close();
    }
    
    public Runner() {
        SocketHandler padma = new SocketHandler("10.15.1.21", 255);

        padma.revceiveString();

        padma.sendCommand("LIST");
        padma.revceiveString();

        String[] s = text.cutString(padma.revceiveString(), ", ");
        System.out.println("Welcome to Blackburn's File server!");
        for (int i = 0; i != s.length; i++) {
            System.out.println((i + 1) + ". " + s[i]);
        }
        System.out.println("Please enter a number or 0 to exit");
        this.userInput = input.next();

        if (Integer.parseInt(this.userInput) != 0) {
            padma.sendCommand("GET " + s[Integer.parseInt(this.userInput) - 1]);
        }
        String msg = padma.revceiveString();
        String newmsg = msg.charAt(6) + "";
        for (int x = 7; x != msg.length(); x++) {
            newmsg = newmsg + msg.charAt(x);
        }

        padma.reciveFile(Integer.parseInt(newmsg), s[Integer.parseInt(this.userInput) - 1]);
        System.out.println("");

        padma.close();
    }

    public static void main(String[] args) {
        new Runner();
    }

}
