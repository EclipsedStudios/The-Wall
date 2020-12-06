package SocialMedia.ServerAndClient;

import SocialMedia.Profile;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the user client
 *
 * @author aakash jariwala, jaden baker
 * @version 11/21/20
 */
public class UserClient extends Thread  {

    public static ArrayList<Profile> profilesList;
    public Profile profile;

    public UserClient(Profile currentUserProfile) {
        this.profile = currentUserProfile;
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

    public void run() {

        InetAddress address = null; // Get localhost
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        /**/
        Socket socket = null; // Create a null socket
        String line; // Store information coming from server
        BufferedReader bufferedReader = null; // BufferedReader for client
        BufferedReader bufferedReader1 = null; // BufferedReader for client's server thread
        PrintWriter printWriter = null; // PrintWriter for client to send info to server
        ObjectInputStream objectInputStream = null;
        Scanner scan = new Scanner(System.in);
        try {
            //Initializes all objects
            socket = new Socket(address, SettingsAndConstants.SERVER_PORT);
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            bufferedReader1 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream());
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
            assert bufferedReader != null;
            assert bufferedReader1 != null;
            assert printWriter != null;
            line = scan.nextLine();
            printWriter.println("First Message");
            printWriter.flush();
            while (line.compareToIgnoreCase("quit") != 0) {
                switch (line) {
                    case "stop server": {
                        return;
                    }
                    case "see users": {
                        profilesList = (ArrayList<Profile>) objectInputStream.readObject();
                        System.out.println("Received [" + profilesList.size() + "] users from: " + socket);
                        for (Profile a : profilesList) {
                            System.out.println("Name: " + a.getName() +
                                    "| Age: " + a.getAge() +
                                    "| Username: " + a.getUsername() +
                                    "| Password: " + a.getRawPassword() +
                                    "| Email: " + a.getEmail());
                        }
                    }
                    default:
                        System.out.println(bufferedReader1.readLine());
                }
                line = scan.nextLine();
                printWriter.println(line);
                printWriter.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Socket Read Error");
        } finally {
            //Make sure these objects aren't null
            assert bufferedReader1 != null;
            assert printWriter != null;

            //Close the client
            try {
                bufferedReader1.close();
                printWriter.close();
                bufferedReader.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


            //Client closed confirmation
            System.out.println("Connection Closed");
        }
    }


}