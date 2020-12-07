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
 * @author Jaden Baker
 * @version 12/7/20
 */

public class CentralServer {

    public static ServerObjectStorage serverObjectStorage;
    public static int numberOfConnections = 0;

    public static ArrayList<ServerClientThread> serverClientThreads = new ArrayList<>();


    public static void getUsersFromDatabase() {
        File dir = new File("UsernameFiles");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                try {
                    String name;
                    String email;
                    FriendsList friendsList;
                    String friendsListString;
                    String website;
                    String interests;
                    String aboutMe;
                    int age;
                    String password;
                    BufferedReader reader;
                    try {
                        reader = new BufferedReader(new FileReader(child));
                        name = reader.readLine();
                        email = reader.readLine();
                        friendsListString = reader.readLine();
                        website = reader.readLine();
                        interests = reader.readLine();
                        aboutMe = reader.readLine();
                        age = Integer.parseInt(reader.readLine());
                        password = reader.readLine();
                        serverObjectStorage.users.add(new Profile(name,
                                age,
                                email,
                                website,
                                new ArrayList<>(Arrays.asList(interests
                                        .substring(1, interests.length() - 1)
                                        .split(", "))),
                                new FriendsList(),
                                aboutMe,
                                child.getName().strip().substring(0, child.getName().indexOf('.')),
                                password));
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (NumberFormatException exception) {
                    System.out.println("File must be broken, skipping user: " + child.getName());
                }
            }
        }

        if (directoryListing != null) {
            for (File child : directoryListing) {
                try {
                    FriendsList friendsList;
                    String friendsListString;
                    BufferedReader reader;
                    Profile currentProfile = null;
                    try {
                        for(Profile p : serverObjectStorage.users){
                            if(p.getUsername().equals(child.getName().substring(0,child.getName().indexOf(".")))){
                                currentProfile = p;
                            }
                        }
                        reader = new BufferedReader(new FileReader(child));
                        reader.readLine();
                        reader.readLine();
                        friendsListString = reader.readLine();
                        String[] friendsSplitUp = friendsListString.split(", "); // [Frank2, Frank3]
                        reader.readLine();
                        reader.readLine();
                        reader.readLine();
                        reader.readLine();
                        reader.readLine();
                        for (String f : friendsSplitUp) {
                            for(Profile p : serverObjectStorage.users){
                                if(p.getUsername().equals(f)){
                                    currentProfile.friendsList.addFriend(p);
                                    p.friendsList.addFriend(currentProfile);
                                }
                            }
                        }
                        System.out.println("Added " + currentProfile.getUsername() + "'s friends");
                        System.out.println("-------");
                        for(Profile p : currentProfile.friendsList.getFriends()){
                            System.out.println(p.getUsername());
                        }
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (NumberFormatException exception) {
                    System.out.println("File must be broken, skipping user: " + child.getName());
                }
            }
        }
    }

    public static void main(String[] args) {
        serverObjectStorage = new ServerObjectStorage();
        getUsersFromDatabase();
        serverObjectStorage.saveUsersToDatabase();
        Socket socket;
        ServerSocket serverSocket = null;
        System.out.println(SettingsAndConstants.WELCOME_MESSAGE_SERVER);
        System.out.println("Server is online and looking for clients");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Server shutting down...");
            serverObjectStorage.saveUsersToDatabase();
        }, "Shutdown-thread"));


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
                ServerClientThread serverClientThread = new ServerClientThread(socket, serverObjectStorage);
                serverClientThreads.add(serverClientThread);
                serverClientThread.start();
                serverObjectStorage.saveUsersToDatabase();
                for (ServerClientThread s : serverClientThreads) {
                    s.serverObjectStorage = serverObjectStorage;
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Connection Error");
            }

        }
    }
}