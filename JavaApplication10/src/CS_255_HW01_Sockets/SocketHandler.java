package CS_255_HW01_Sockets;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nicholas.bohm
 */
public class SocketHandler {

    private Socket currentSocket;
    private DataInputStream incoming;
    private PrintStream outGoing;
    private BufferedReader bufferInput;
    //Creates a socket
    public SocketHandler(String address, int port) {
        try {
            this.currentSocket = new Socket(address, port);
            this.incoming = new DataInputStream(this.currentSocket.getInputStream());
            this.outGoing = new PrintStream(this.currentSocket.getOutputStream());
            InputStreamReader ir = new InputStreamReader(this.currentSocket.getInputStream());
            this.bufferInput = new BufferedReader(ir);
        } catch (UnknownHostException e) {
           // System.out.println("SH UHE");
        } catch (IOException ex) {
           // System.out.println("SH IOE");
        }
    }
    //Sends a command to the socket
    public void sendCommand(String command) {
        this.outGoing.println(command);
    }
    //receives and saves a file
    public void reciveFile(int fileSize, String nameOfFile) {
        try {
            byte[] buffer = new byte[fileSize];
            this.incoming.readFully(buffer);
            File dir = new File("Downloads");
            dir.mkdir();
            new FileOutputStream(dir + "\\" + nameOfFile);
        } catch (IOException ex) {
        }

    }
    //receives the string msg from the server
    public String revceiveString() {
        
        try {
            return this.bufferInput.readLine();
        } catch (IOException ex) {
            return "error in RevicedString";
        }
    }
    //closes the server
    public void close() {
        try {
            this.currentSocket.close();
        } catch (IOException ex) {
            System.exit(0);
        }
    }
}
