package ServerAndClient;

import java.io.*;
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
    ObjectOutputStream objectOutputStream = null;
    ObjectInputStream objectInputStream = null;

    public ServerClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("IO error in server thread");
        }

        try {
            line = bufferedReader.readLine();
            while (line.compareToIgnoreCase("quit") != 0) {
                switch (line) {
                    case "stop server":
                        System.exit(0);
                        objectOutputStream.writeObject(null);
                        objectOutputStream.flush();
                    case "see users":
                        printWriter.println("clientUserRequest");
                        printWriter.flush();
                        objectOutputStream.writeObject(CentralServer.users.toArray());
                        objectOutputStream.flush();
                        break;
                    case "serverUserArrayRequest":
                        objectOutputStream.writeObject(CentralServer.users.toArray());
                        objectOutputStream.flush();
                        break;
                    default:
                        printWriter.println("You said: " + line);
                        printWriter.println("---------");
                        printWriter.flush();
                        objectOutputStream.writeObject(null);
                        objectOutputStream.flush();
                        break;
                }
                System.out.println(socket.getInetAddress() + ":" + socket.getPort() + " said: " + line);
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
                    CentralServer.users.remove(String.valueOf(socket.getInetAddress()));
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