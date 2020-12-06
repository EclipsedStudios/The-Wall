package SocialMedia.ServerAndClient;

import SocialMedia.FriendsList;
import SocialMedia.Profile;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the user client
 *
 * @author aakash jariwala, jaden baker
 * @version 11/21/20
 */
public class UserClient extends Thread  {

    public static ArrayList<Profile> profilesList;
    public static Profile profile;
    public InetAddress address = null; // Get localhost
    public Socket socket = null; // Create a null socket
    public String line; // Store information coming from server
    public ObjectOutputStream objectOutputStream =  null;
    public ObjectInputStream objectInputStream = null;

    public boolean Login(String username, String rawPassword) throws IOException {
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            //Initializes all objects
            socket = new Socket(address, SettingsAndConstants.SERVER_PORT);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            //Creates exception if failed
            e.printStackTrace();
            System.err.print("IO Exception");
        }

        objectOutputStream.writeUTF("see users");
        System.out.println("Wrote UTF");
        objectOutputStream.flush();
        objectOutputStream.reset();
        try {
            profilesList = (ArrayList<Profile>) objectInputStream.readObject();

            for(Profile p : profilesList) {
                System.out.println(p.getUsername());
                System.out.println(p.getRawPassword());
            }


            for(Profile p : profilesList){
                if(p.username.equals(username)){
                    System.out.println(p.getUsername());
                    if(p.rawPassword.equals(rawPassword)){
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
        Profile newProfile = new Profile(name, age, email, website, interests, friendsList,  aboutMe,  username, rawPassword);
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            //Initializes all objects
            socket = new Socket(address, SettingsAndConstants.SERVER_PORT);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            //Creates exception if failed
            e.printStackTrace();
            System.err.print("IO Exception");
        }
        objectOutputStream.reset();
        objectOutputStream.writeUTF("create profile");
        System.out.println("Wrote UTF");
        objectOutputStream.flush();
        objectOutputStream.reset();
        objectOutputStream.writeObject(profile);
        System.out.println("Wrote Profile");
        objectOutputStream.flush();
        objectOutputStream.reset();

        try {
            objectInputStream.close();
            objectOutputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Gets profile with desired username, returns null if doesn't exist
    public static Profile getProfileWith(String username){
        for (Profile profile : profilesList) {
            if (profile.getUsername().equalsIgnoreCase(username)) {
                return profile;
            }
        }
        return null;
    }
    boolean exit = false;
    public void exit()
    {
        exit = true;
    }
    public void run() {
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        /**/

        try {
            //Initializes all objects
            socket = new Socket(address, SettingsAndConstants.SERVER_PORT);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            //Creates exception if failed
            e.printStackTrace();
            System.err.print("IO Exception");
        }

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