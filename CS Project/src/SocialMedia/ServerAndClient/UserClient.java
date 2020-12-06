package SocialMedia.ServerAndClient;

import SocialMedia.FriendsList;
import SocialMedia.Profile;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Represents the user client
 *
 * @author aakash jariwala, jaden baker
 * @version 11/21/20
 */
public class UserClient extends Thread {

    public static ArrayList<Profile> profilesList;
    public Profile profile;
    public InetAddress address = null; // Get localhost
    public Socket socket = null; // Create a null socket
    public String line; // Store information coming from server
    public ObjectOutputStream objectOutputStream = null;
    public ObjectInputStream objectInputStream = null;
    boolean exit = false;

    public UserClient() {
        try {
            address = InetAddress.getLocalHost();
            socket = new Socket(address, SettingsAndConstants.SERVER_PORT);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.print("IO Exception");
        }
    }

    // Gets profile with desired username, returns null if doesn't exist
    public static Profile getProfileWith(String username) {
        for (Profile profile : profilesList) {
            if (profile.getUsername().equalsIgnoreCase(username)) {
                return profile;
            }
        }
        return null;
    }

    public boolean Login(String username, String rawPassword) throws IOException {
        objectOutputStream.writeUTF("see users");
        System.out.println("Wrote UTF");
        objectOutputStream.flush();
        objectOutputStream.reset();
        try {
            profilesList = (ArrayList<Profile>) objectInputStream.readObject();

            for (Profile p : profilesList) {
                System.out.println(p.getUsername());
                System.out.println(p.getRawPassword());
            }


            for (Profile p : profilesList) {
                if (p.username.equals(username)) {
                    System.out.println(p.getUsername());
                    if (p.rawPassword.equals(rawPassword)) {
                        System.out.println(p.rawPassword);
                        profile = p;
                        return true;
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void CreateAccount(String name, int age, String email, String website, ArrayList<String> interests,
                              FriendsList friendsList, String aboutMe, String username, String rawPassword) throws IOException {
        objectOutputStream.writeUTF("create profile");
        System.out.println("Wrote UTF");
        objectOutputStream.flush();
        objectOutputStream.reset();
        objectOutputStream.writeObject(new Profile(name, age, email, website, interests, friendsList, aboutMe, username, rawPassword));
        System.out.println("Wrote Profile: " + new Profile(name, age, email, website, interests, friendsList, aboutMe, username, rawPassword).getName());
        objectOutputStream.flush();
        objectOutputStream.reset();
    }

    public void exit() {
        exit = true;
    }

    public void run() {
        System.out.println("Client Address: " + address);
        System.out.println("[FOR TESTING] Send a message for the server to respond back with (Send a random message first, " +
                "it is buggy and it will break if you ask for users first):");

        try {
            assert objectInputStream != null;
            assert objectOutputStream != null;
            line = "see users";
            while (line.compareToIgnoreCase("quit") != 0 && exit == false) {
                switch (line) {
                    case "stop server": {
                        return;
                    }
                    case "see users": {
                        profilesList = (ArrayList<Profile>) objectInputStream.readObject();
                    }
                    default:
                        System.out.println(objectInputStream.readUTF());
                }
                objectOutputStream.writeUTF(line);
                objectOutputStream.flush();
                objectOutputStream.reset();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Socket Read Error");
        } finally {
            //Make sure these objects aren't null
            assert objectInputStream != null;
            assert objectOutputStream != null;

            //Close the client
            try {
                objectInputStream.close();
                objectOutputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


            //Client closed confirmation
            System.out.println("Connection Closed");
        }
    }


}