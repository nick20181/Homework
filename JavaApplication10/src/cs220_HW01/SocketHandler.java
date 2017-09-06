package cs220_HW01;

import java.io.*;
import java.net.*;

/**
 *
 * @author Nicholas Bohm
 */
public class SocketHandler {

    private Socket connectedSocket;

    /**
     * connects to the server without sending a message
     *
     * @param address
     * @param port
     */
    public SocketHandler(String address, int port) {
        try {
            this.connectedSocket = new Socket(address, port);
            
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
     * Closes the socket connection.
     */
    public void closeSocket(){
        try{
        this.connectedSocket.close();
        } catch (IOException io) {
            System.exit(0);
        }
    }
    /**
     * gets the message from the server
     * @return 
     */
    public String inBound() {
        try {
            InputStreamReader inputStream = new InputStreamReader(this.connectedSocket.getInputStream());
            BufferedReader inComing = new BufferedReader(inputStream);
            return inComing.readLine();
        } catch (IOException io) {
            System.out.println("Error Io Exception, Connection refused!!");
            io.printStackTrace();
            System.exit(0);
            return null;
        }
    }

    /**
     * sends a msg to the server
     *
     * @param outStream
     */
    public String sendMsg(String outStream) {
        try {
            PrintStream outGoing = new PrintStream(this.connectedSocket.getOutputStream());
            outGoing.println(outStream);
            return outStream;
        } catch (IOException io) {
            System.out.println("Error Io Exception, Connection refused!!");
            io.printStackTrace();
            System.exit(0);
            return "";
        }

    }
}

