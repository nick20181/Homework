package cs220_HW01;

import java.io.*;

/**
 *
 * @author Nicholas Bohm
 */
public class ResponseHandler {

    public ResponseHandler() {

    }

    public String handleReponse(String command, String response) {
        if (response.contains("00")) {
            return "";
        } else if (response.contains("01")) {
            if (command.equals("LIST")) {
                //break up list
                return "List!!";
            } else if (command.contains("GET")) {
                //gets a file
                return "Download Complete!";
            } else if (command.equals("EXIT")) {
                return "Exiting!";
            } else {
                return "Unkown Error! Please Try again.";
            }
        } else if (response.contains("02")) {
            return "Unkwown File Please Try again.";
        } else if (response.contains("03")) {
            return "Unkown Command Please Try again.";
        } else {
            return "Unkown Error! Please Try again..";
        }

    }
    
    public String ListBreaker(){
        return "";
    }
}
