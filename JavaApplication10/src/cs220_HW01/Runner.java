package cs220_HW01;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Nicholas Bohm
 */
public class Runner {

    private Scanner input = new Scanner(System.in);
    private String inputOne;
    private String ip = "10.100.1.239";
    private int port = 255;
    private String[] list;

    public Runner() {
        System.out.println("Welcome!");
        System.out.println("Do you want to use the default address?\n (Y/N)");
        this.inputOne = input.nextLine();
        if(this.inputOne.contains("n")){
            System.out.println("Please Enter the ip you wish to use.");
            this.ip = input.nextLine();
            System.out.println("Please enter a port you wish to use.");
            this.port = input.nextInt();
        }
        //loop until user quits
        while (true) {
            //creates the socket.
            SocketHandler blackburn = new SocketHandler(this.ip, this.port);
            //checks to see if socket connected.
            try{
            if (blackburn.revceiveString().equalsIgnoreCase("00 HELLO")) {
                // System.out.println("Connection Established!");
            }
            } catch(NullPointerException e){
                System.out.println("Connection Refused Please try again!");
                System.exit(0);
            }
            //sends the list commands and checks to see if it recived confirmation.
            blackburn.sendCommand("LIST");
            if (!blackburn.revceiveString().contains("01 OK")) {
                System.out.println("Error Please try again!");
                System.exit(0);
            }
            System.out.println("Start Loop");
            //displays the list

            list = blackburn.revceiveString().split(", ");
            System.out.println("Welcome to Blackburn's File server!");
            for (int i = 0; i != list.length; i++) {
                System.out.println((i + 1) + ". " + list[i]);
            }
            System.out.println("Please enter a number or 0 to exit");
            this.inputOne = input.nextLine();

            //handles user selection and checks to see if that selection is valid.
            try {
                if (Integer.parseInt(inputOne) != 0) {

                    //sends command to get the requested file.
                    blackburn.sendCommand("GET "
                            + list[Integer.parseInt(inputOne) - 1]);

                    //gets the byte length for the requested file.
                    String msg = blackburn.revceiveString();
                    String newmsg = msg.charAt(6) + "";
                    for (int x = 7; x != msg.length(); x++) {
                        newmsg = newmsg + msg.charAt(x);
                    }

                    //downloads the file to the default netbeans folder.
                    blackburn.reciveFile(Integer.valueOf(newmsg), list[Integer.parseInt(inputOne) - 1]);
                    System.out.println("");
                    System.out.println("Download Complete. Please look in "
                            + "default project directory to find it! "
                            + "Press Enter to continue!");
                    input.nextLine();
                    blackburn.close();
                    this.inputOne = null;
                } else {
                    System.out.println("Disconnecting From " + this.ip
                            + " on port " + this.port);
                    blackburn.close();
                    System.exit(0);
                }
            } catch (Exception e) {
            }
        }
    }

    public static void main(String[] args) {
        new Runner();
    }

}
