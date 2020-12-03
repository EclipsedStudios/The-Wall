package ServerAndClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

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
    public static ArrayList<String> users = new ArrayList<>();
    public static ArrayList<ServerClientThread> serverClientThreads = new ArrayList<>();
    public static void RefreshGlobally() {
        for(ServerClientThread a : serverClientThreads){
            a.RefreshUsers(users);
        }
    }

    public static void main(String[] args) {
        Socket socket;
        ServerSocket serverSocket = null;
        System.out.println(SettingsAndConstants.WELCOME_MESSAGE_SERVER);
        System.out.println("Server is online and looking for clients");
        users.add("Default");
        Runtime.getRuntime().addShutdownHook(new Thread(() ->
                System.out.println("Server shutting down..."), "Shutdown-thread"));

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
                users.add(String.valueOf(socket.getInetAddress()) + (socket.getPort()));
                System.out.println("Number of connections: " + numberOfConnections);
                System.out.println("----------------------------------------");
                ServerClientThread serverClientThread = new ServerClientThread(socket);
                serverClientThreads.add(serverClientThread);
                serverClientThread.start();
                for(ServerClientThread a : serverClientThreads){
                    a.RefreshUsers(users);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Connection Error");
            }

        }
    }
}