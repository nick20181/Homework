package cs220_HW01;

import java.io.*;

/**
 *
 * @author Nicholas Bohm
 */
public class ResponseHandler {
    private TextHandler text = new TextHandler();
    public ResponseHandler() {

    }

    public String handleReponse(String command, String response) {
        if (response.contains("00")) {
            return "";
        } else if (response.contains("01")) {
            if (command.equals("LIST")) {
                response = text.cutString(response, "01 OK ")[1];
                String toReturn;
                String[] list = text.cutString(response, ", ");
                toReturn = "1. " + list[0] + "\n";
                for(int i = 1; i != list.length; i++){
                    toReturn =  toReturn + (i + 1) + ". " + list[i] + "\n";
                }
                return toReturn;
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
