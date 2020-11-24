package ServerAndClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Represents the server
 *
 * Was named "ProfileServer", but I felt that Server.CentralServer is more fitting - Jaden
 *
 * @author Jaden Baker
 * @version 11/21/20
 */

public class CentralServer {

    public static int numberOfConnections = 0;

    public static void PrintNumberOfConnections() {
        System.out.println("Number of connections: " + numberOfConnections);
    }

    public static void main(String[] args) {
        Socket socket;
        ServerSocket serverSocket = null;
        System.out.println(SettingsAndConstants.WELCOME_MESSAGE_SERVER);
        System.out.println("Server is online and looking for clients");
        try {
            serverSocket = new ServerSocket(SettingsAndConstants.SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server error");
        }

        while (true) {
            try {
                assert serverSocket != null;
                socket = serverSocket.accept();
                numberOfConnections++;
                System.out.println("----------------------------------------");

                System.out.println("Connection established to: " + socket.getInetAddress() +
                        "\nClient Port: " + socket.getPort());
                PrintNumberOfConnections();

                System.out.println("----------------------------------------");
                ServerClientThread serverClientThread = new ServerClientThread(socket);
                serverClientThread.start();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Connection Error");
            }
        }
    }
}