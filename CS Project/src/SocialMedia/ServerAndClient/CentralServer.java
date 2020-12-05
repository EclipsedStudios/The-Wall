package SocialMedia.ServerAndClient;

import SocialMedia.FriendsList;
import SocialMedia.Profile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

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

    public static void getUsersFromDatabase(){
        File dir = new File("UsernameFiles");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                String name;
                String email;
                String friendsList;
                String website;
                String interests;
                String aboutMe;
                int age;
                String password;
                BufferedReader reader;
                try {
                    reader = new BufferedReader(new FileReader(child));
                    name = reader.readLine();
                    System.out.println("Loaded user: " + name + " from database");
                    email = reader.readLine();
                    friendsList = reader.readLine();
                    website = reader.readLine();
                    interests = reader.readLine();
                    aboutMe = reader.readLine();
                    age = Integer.parseInt(reader.readLine());
                    password = reader.readLine();
                    // setting FriendsList to null for now so it runs, was left out previously
                    serverObjectStorage.users.add(
                            new Profile(name, age, email, website, new ArrayList<>(Arrays.asList(interests.split(", "))),
                                    null, aboutMe, child.getName().strip().substring(0,child.getName().indexOf('.')), password));
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        serverObjectStorage = new ServerObjectStorage();
        getUsersFromDatabase();
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