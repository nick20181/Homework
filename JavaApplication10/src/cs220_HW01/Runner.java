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
    private String command;
    private TextHandler text = new TextHandler();

    public Runner() {
        ResponseHandler controller = new ResponseHandler();
        System.out.println("Welcome to Blackburn Computer Science File Server");
        SocketHandler blackburn = new SocketHandler("10.100.1.239", 255);

        System.out.println(controller.handleReponse(blackburn.sendMsg("LIST"),
                blackburn.inBound() + blackburn.inBound()));
        System.out.println("Enter a number to download or 0 to exit");
        this.userInput = this.input.nextLine();
        while(true) {
            if (Integer.parseInt(this.userInput) == 0) {
                System.out.println("Shutting Down. ");
                blackburn.closeSocket();
                System.exit(0);
            } else {
                try {
                    Integer.parseInt(this.userInput);
                    this.command = "GET " + this.text.findNumber(
                            controller.handleReponse(blackburn.sendMsg("LIST"),
                                    blackburn.inBound() + blackburn.inBound()), userInput);
                    controller.handleReponse(this.command, blackburn.inBound()
                            + blackburn.inBound());
                } catch (NumberFormatException e) {
                    System.out.println("You did not enter a number associated to "
                            + "a File please try again!");
                    System.out.println(controller.handleReponse(blackburn.sendMsg("LIST"),
                            blackburn.inBound() + blackburn.inBound()));
                    System.out.println("Enter a number to download or 0 to exit");
                    this.userInput = this.input.nextLine();
                }
            }
        }
    }

    public Runner(int num) {
        TextHandler t = new TextHandler();
        System.out.println(t.findNumber("1. Nick.txt\n2. Emily.txt\n3. Adam.jpg", "2"));
        
    }

    public static void main(String[] args) {
        // new Runner();
        new Runner(1);
    }

}
