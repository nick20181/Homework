package cs220_HW01;

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

    public SocketHandler(String address, int port) {
        try {
            this.currentSocket = new Socket(address, port);
            this.incoming = new DataInputStream(this.currentSocket.getInputStream());
            this.outGoing = new PrintStream(this.currentSocket.getOutputStream());
            InputStreamReader ir = new InputStreamReader(this.currentSocket.getInputStream());
            this.bufferInput = new BufferedReader(ir);
        } catch (UnknownHostException e) {
            System.out.println("SH UHE");
        } catch (IOException ex) {
            System.out.println("SH IOE");
        }
    }

    public void sendCommand(String command) {
        this.outGoing.println(command);
    }

    public void reciveFile(int fileSize, String nameOfFile) {
        try {
            byte[] buffer = new byte[fileSize];
            this.incoming.readFully(buffer);
            new File("\\..\\Downloads");
            new FileOutputStream("Downloads\\..\\" + nameOfFile);

        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("RF IOE");
        }

    }

    public String revceiveString() {
        
        try {
            return this.bufferInput.readLine();
        } catch (IOException ex) {
            return "error in RevicedString";
        }
    }

    public void close() {
        try {
            this.currentSocket.close();
        } catch (IOException ex) {
            System.exit(0);
        }
    }
}
