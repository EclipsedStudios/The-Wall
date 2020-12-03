package SocialMedia.ServerAndClient;

import SocialMedia.Profile;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Represents the server
 *
 * Was named "ProfileServer", but I felt that CentralServer is more fitting - Jaden
 *
 * @author Jaden Baker
 * @version 11/21/20
 */

public class CentralServer {

    public static ServerObjectStorage serverObjectStorage;
    public static int numberOfConnections = 0;

    public static ArrayList<ServerClientThread> serverClientThreads = new ArrayList<>();


    public static void main(String[] args) {
        serverObjectStorage = new ServerObjectStorage();
        Socket socket;
        ServerSocket serverSocket = null;
        System.out.println(SettingsAndConstants.WELCOME_MESSAGE_SERVER);
        System.out.println("Server is online and looking for clients");
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
                System.out.println("Number of connections: " + numberOfConnections);
                System.out.println("----------------------------------------");
                Profile newProfile = new Profile("Test User", String.valueOf(socket.getPort()), 18, "test@domain.com", "test");
                ServerClientThread serverClientThread = new ServerClientThread(socket, serverObjectStorage, newProfile);
                serverClientThreads.add(serverClientThread);
                serverObjectStorage.users.add(newProfile);
                serverClientThread.start();

                for(ServerClientThread s : serverClientThreads){
                    s.serverObjectStorage = serverObjectStorage;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Connection Error");
            }

        }
    }
}