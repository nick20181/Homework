package cs220_HW01;

import java.io.*;
import java.net.*;

/**
 *
 * @author Nicholas Bohm
 */
public class SocketHandler {

    private Socket connectedSocket;
    private String messageFromServer;
    /**
     * connects to the server without sending a message
     * @param address
     * @param port 
     */
    public SocketHandler(String address, int port) {
        try {
            connectedSocket = new Socket(address, port);
            PrintStream outGoing = new PrintStream(connectedSocket.getOutputStream());
            outGoing.println();
            
            InputStreamReader inputStream = new InputStreamReader(connectedSocket.getInputStream());
            BufferedReader inComing = new BufferedReader(inputStream);
            this.messageFromServer = inComing.readLine();
        } catch (UnknownHostException uh) {
            System.out.println("Error UnknownHostException, Connection refused!!");
            uh.printStackTrace();
            System.exit(0);
        } catch (IOException io) {
            System.out.println("Error Io Exception, Connection refused!!");
            io.printStackTrace();
            System.exit(0);
        }

    }
    /**
     * sends a message to the server 
     * @param address
     * @param port
     * @param toSend 
     */
    public SocketHandler(String address, int port, String toSend) {
        try {
            connectedSocket = new Socket(address, port);
            
            PrintStream outGoing = new PrintStream(connectedSocket.getOutputStream());
            outGoing.println(toSend);
            
            InputStreamReader inputStream = new InputStreamReader(connectedSocket.getInputStream());
            BufferedReader inComing = new BufferedReader(inputStream);
            messageFromServer = inComing.readLine();
            
        } catch (UnknownHostException uh) {
            System.out.println("Error UnknownHostException, Connection refused!!");
            uh.printStackTrace();
            System.exit(0);
        } catch (IOException io) {
            System.out.println("Error Io Exception, Connection refused!!");
            io.printStackTrace();
            System.exit(0);
        }

    }
    /**
     * gets the message from the server
     * @return 
     */
    public String getMessageRecived(){
        return this.messageFromServer;
    }

}
