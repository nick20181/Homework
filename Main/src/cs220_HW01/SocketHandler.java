package cs220_HW01;

import java.io.*;
import java.net.*;
/**
 *
 * @author Nicholas Bohm
 */
public class SocketHandler {
    private String connectionStatus = "No Connection";
    private Socket connectedSocket;
    private SocketAddress endpoint;
    private InputStream socketIn;
    private OutputStream socketOut;
    
    public SocketHandler(String address, int port){
        System.out.println("SocketHander Start");
        socketEstablish(address, port);
        System.out.println("Scoket in on");
        try{
            socketIn = connectedSocket.getInputStream();
        } catch (Exception exception){
            System.out.println("Error in SocketHandler");
            exception.printStackTrace();
        }    
        System.out.println("Socket out on");
        try{
            socketOut = connectedSocket.getOutputStream();
        } catch (Exception exception){
            System.out.println("Error in SocketHandler");
            exception.printStackTrace();
        }
        System.out.println("Socket Handler done");
    }
    public String getConnectionStat(){
        return connectionStatus;
    }
    /**
     * gets the output of the socket
     * @return 
     */
    public OutputStream getOutputSteam(){
        return socketOut;
    }
    /**
     * gets the inputstream for the socket
     * @return 
     */
    public InputStream getInputStream(){  
        return socketIn;
    }
    
    /**
     * Calls the createSocket and gives the connection status
     * @param address
     * @param port
     * @return 
     */    
    public String socketEstablish(String address, int port){
        createSocket(address, port);
        return connectionStatus;
    }
    
    /**
     * Creates the socket and sets the connectionStatus
     * @param address
     * @param port 
     */
    public void createSocket(String address, int port){
        try{
            connectedSocket = new Socket(address, port);
            connectedSocket.connect(endpoint);
            connectionStatus = "Connected";
        } catch (UnknownHostException uh) {
            connectionStatus = "No Connection";
            System.out.println("Error UnknownHostException, Connection refused!!");
            uh.printStackTrace();
            System.exit(0);
        } catch (IOException io){
            connectionStatus = "No Connection";
            System.out.println("Error Io Exception, Connection refused!!");
            io.printStackTrace();
            System.exit(0);
        }
    }
}


    
