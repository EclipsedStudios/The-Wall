package ServerAndClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Represents a client connected to the server
 *
 * @author Jaden Baker
 * @version 11/22/20
 */

public class ServerClientThread extends Thread {
    String line = null;
    BufferedReader bufferedReader = null;
    PrintWriter printWriter = null;
    Socket socket;

    public ServerClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("IO error in server thread");
        }

        try {
            line = bufferedReader.readLine();
            while (line.compareToIgnoreCase("quit") != 0) {
                printWriter.println("You said: " + line);

                printWriter.flush();
                System.out.println(socket.getInetAddress()+ ":" + socket.getPort() + " said: " + line);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            line = this.getName();
            System.out.println("----------------------------------------");
            System.out.println("IO Error: " + line + " terminated abruptly");
        } catch (NullPointerException e) {
            line = this.getName();
            System.out.println("----------------------------------------");
            System.out.println("Client " + line + " Closed");
        } finally {

            try {
                System.out.println("Connection Closing...");
                if (bufferedReader != null) {
                    bufferedReader.close();
                    System.out.println("Socket Input Stream Closed");
                }

                if (printWriter != null) {
                    printWriter.close();
                    System.out.println("Socket Output Closed");
                }

                if (socket != null) {
                    socket.close();
                    System.out.println("Socket Closed");
                    CentralServer.numberOfConnections--;
                    CentralServer.PrintNumberOfConnections();
                    System.out.println("----------------------------------------");
                }
            } catch (IOException ie) {
                System.out.println("Socket Close Error");
            }
        }
    }
}