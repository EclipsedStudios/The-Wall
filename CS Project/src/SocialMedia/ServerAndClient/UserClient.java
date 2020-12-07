package SocialMedia.ServerAndClient;

import SocialMedia.FriendsList;
import SocialMedia.Profile;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Represents the user client
 *
 * @author Jaden Baker
 * @version 12/7/20
 */
public class UserClient extends Thread {

    public static ArrayList<Profile> profilesList = new ArrayList<>();
    private final AtomicBoolean running = new AtomicBoolean(false);
    public static Profile profile;
    public InetAddress address = null; // Get localhost
    public Socket socket = null; // Create a null socket
    public String line; // Store information coming from server
    public ObjectOutputStream objectOutputStream = null;
    public ObjectInputStream objectInputStream = null;
    boolean oISUsed = false;

    public UserClient() {
        try {
            address = InetAddress.getLocalHost();
            socket = new Socket(SettingsAndConstants.SERVER_HOST, SettingsAndConstants.SERVER_PORT);
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

    public void interrupt() {
        running.set(false);
    }

    public boolean login(String username, String rawPassword) throws IOException {
        try {


            if(!socket.isConnected()){
                socket = new Socket(SettingsAndConstants.SERVER_HOST, SettingsAndConstants.SERVER_PORT);
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectInputStream = new ObjectInputStream(socket.getInputStream());
            }

            System.out.println("Wrote UTF");
            objectOutputStream.writeUTF("see users");
            objectOutputStream.flush();
            objectOutputStream.reset();
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

    public void createAccount(String name, int age, String email, String website, ArrayList<String> interests,
                              FriendsList friendsList, String aboutMe, String username, String rawPassword) throws IOException {
        if(!socket.isConnected()){
            socket = new Socket(SettingsAndConstants.SERVER_HOST, SettingsAndConstants.SERVER_PORT);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        }
        objectOutputStream.writeUTF("create profile");
        System.out.println("Wrote UTF");
        objectOutputStream.flush();
        objectOutputStream.reset();
        objectOutputStream.writeObject(new Profile(name, age, email, website, interests, friendsList, aboutMe, username, rawPassword));
        System.out.println("Wrote Profile: " + new Profile(name, age, email, website, interests, friendsList, aboutMe, username, rawPassword).getName());
        objectOutputStream.flush();
        objectOutputStream.reset();
        oISUsed = true;
    }

    public void updateAccount(Profile profile) throws IOException {
        if(!socket.isConnected()){
            socket = new Socket(SettingsAndConstants.SERVER_HOST, SettingsAndConstants.SERVER_PORT);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        }
        objectOutputStream.writeUTF("update profile");
        System.out.println("Wrote UTF");
        objectOutputStream.flush();
        objectOutputStream.reset();
        objectOutputStream.writeObject(profile);
        System.out.println("Wrote Profile: " + profile.getName());
        objectOutputStream.flush();
        objectOutputStream.reset();
        oISUsed = true;
    }

    public void deleteAccount(Profile profile) throws IOException {
        if(!socket.isConnected()){
            socket = new Socket(SettingsAndConstants.SERVER_HOST, SettingsAndConstants.SERVER_PORT);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        }
        objectOutputStream.writeUTF("delete profile");
        System.out.println("Wrote UTF");
        objectOutputStream.flush();
        objectOutputStream.reset();
        objectOutputStream.writeObject(profile);
        System.out.println("Wrote Profile: " + profile.getName());
        objectOutputStream.flush();
        objectOutputStream.reset();
        oISUsed = true;
    }

    public void refreshPage() throws IOException {
        if(!socket.isConnected()){
            socket = new Socket(SettingsAndConstants.SERVER_HOST, SettingsAndConstants.SERVER_PORT);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        }
        objectOutputStream.writeUTF("see users");
        objectOutputStream.flush();
        objectOutputStream.reset();
        try {
            profilesList = (ArrayList<Profile>) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Asked for users, size: " + profilesList.size());

        for(Profile p : profilesList){
            if(p.getUsername().equals(profile.getUsername())){
                profile = p;
            }
        }
        oISUsed = true;
    }

    public void refreshPageNoLogin() throws IOException {
        if(!socket.isConnected()){
            socket = new Socket(SettingsAndConstants.SERVER_HOST, SettingsAndConstants.SERVER_PORT);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        }
        objectOutputStream.writeUTF("see users");
        objectOutputStream.flush();
        objectOutputStream.reset();
        try {
            profilesList = (ArrayList<Profile>) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Asked for users, size: " + profilesList.size());
        oISUsed = true;
    }

    public void addFriend(Profile profile) throws IOException {
        if(!socket.isConnected()){
            socket = new Socket(SettingsAndConstants.SERVER_HOST, SettingsAndConstants.SERVER_PORT);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        }
        objectOutputStream.writeUTF("add friend");
        System.out.println("Wrote UTF");
        objectOutputStream.flush();
        objectOutputStream.reset();
        objectOutputStream.writeObject(profile);
        System.out.println("Wrote Profile: ");
        objectOutputStream.flush();
        objectOutputStream.reset();
        objectOutputStream.writeObject(UserClient.profile);
        System.out.println("Wrote Profile: ");
        objectOutputStream.flush();
        objectOutputStream.reset();
        oISUsed = true;
    }

    public void cancelFriendRequest(Profile profile) throws IOException {
        if(!socket.isConnected()){
            socket = new Socket(SettingsAndConstants.SERVER_HOST, SettingsAndConstants.SERVER_PORT);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        }
        objectOutputStream.writeUTF("cancel friend");
        System.out.println("Cancelled Friend Request");
        objectOutputStream.flush();
        objectOutputStream.reset();
        objectOutputStream.writeObject(profile);
        System.out.println("Wrote profile to be cancelled: ");
        objectOutputStream.flush();
        objectOutputStream.reset();
        objectOutputStream.writeObject(UserClient.profile);
        System.out.println("Wrote your profile: ");
        objectOutputStream.flush();
        objectOutputStream.reset();
        oISUsed = true;
    }

    public void acceptFriend(Profile profile) throws IOException {
        if(!socket.isConnected()){
            socket = new Socket(SettingsAndConstants.SERVER_HOST, SettingsAndConstants.SERVER_PORT);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
        }
        objectOutputStream.writeUTF("accept friend");
        System.out.println("Wrote UTF");
        objectOutputStream.flush();
        objectOutputStream.reset();
        objectOutputStream.writeObject(profile);
        System.out.println("Wrote Profile: ");
        objectOutputStream.flush();
        objectOutputStream.reset();
        objectOutputStream.writeObject(UserClient.profile);
        System.out.println("Wrote Profile: ");
        objectOutputStream.flush();
        objectOutputStream.reset();
        oISUsed = true;
    }
    public void run() {
        if(!socket.isConnected()){
            try {
                socket = new Socket(SettingsAndConstants.SERVER_HOST, SettingsAndConstants.SERVER_PORT);
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectInputStream = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        running.set(true);
        System.out.println("Client Address: " + address);
        try {
            line = "see users";
            objectOutputStream.reset();
            objectOutputStream.writeUTF("see users");
            objectOutputStream.flush();
            objectOutputStream.reset();
            while (line.compareToIgnoreCase("quit") != 0 && running.get()) {
                if ("see users".equals(line)) {
                    profilesList = (ArrayList<Profile>) objectInputStream.readObject();
                    for(Profile p : profilesList){
                        if(p.getUsername().equals(profile.getUsername())){
                            profile = p;
                        }
                    }
                }
                objectOutputStream.writeUTF(line);
                objectOutputStream.flush();
                objectOutputStream.reset();
                try {
                    Thread.sleep(333);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                /* objectOutputStream.writeUTF(line);
                objectOutputStream.flush();
                objectOutputStream.reset(); */
            }
            if(!running.get() && socket.isConnected()){
                objectInputStream.close();
                objectOutputStream.close();
                socket.close();
                System.out.println("Connection Closed");
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