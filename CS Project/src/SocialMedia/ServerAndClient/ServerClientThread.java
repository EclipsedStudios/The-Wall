package SocialMedia.ServerAndClient;

import SocialMedia.Profile;

import java.io.*;
import java.net.Socket;

/**
 * Represents a client connected to the server
 *
 * @author Jaden Baker
 * @version 11/22/20
 */

public class ServerClientThread extends Thread {
    public  String line = null;
    public Socket socket;
    public ObjectOutputStream objectOutputStream = null;
    public ObjectInputStream objectInputStream = null;
    public ServerObjectStorage serverObjectStorage;
    public Profile profile;

    public ServerClientThread(Socket socket, ServerObjectStorage serverObjectStorage) {
        this.socket = socket;
        this.serverObjectStorage = serverObjectStorage;
    }

    private void StopThread() throws IOException {
        socket.close();
        System.out.println("Socket Closed");
        CentralServer.numberOfConnections--;
        serverObjectStorage.users.remove(profile);
        CentralServer.serverClientThreads.remove(this);
        System.out.println("----------------------------------------");
    }

    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // create a ObjectOutputStream so we can write data from it
            objectOutputStream = new ObjectOutputStream(outputStream);
            // create a ObjectInputStream so we can read data from it
            objectInputStream = new ObjectInputStream(inputStream);

        } catch (IOException e) {
            System.out.println("IO error in server thread");
        }

        try {
            System.out.println("Thread started");
            line = objectInputStream.readUTF();
            System.out.println(line);
            while (line.compareToIgnoreCase("quit") != 0) {
                switch (line) {
                    case "stop server" : System.exit(0);
                    case "see users" : {
                        System.out.println("User has tried to see users");
                        objectOutputStream.writeObject(serverObjectStorage.users);
                        objectOutputStream.flush();
                        objectOutputStream.reset();
                    }
                    case "create profile" : {
                        System.out.println("User has tried to add a user");
                        try {
                            Profile profile = (Profile) objectInputStream.readObject();
                            System.out.println("added " + profile.getName());
                            serverObjectStorage.users.add(profile);
                            serverObjectStorage.saveUsersToDatabase();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }

                    }
                    default : {
                        objectOutputStream.writeUTF("You said: " + line);
                        objectOutputStream.flush();
                        objectOutputStream.reset();
                    }
                }
                System.out.println(socket.getInetAddress() + ":" + socket.getPort() + " said: " + line);
                line = objectInputStream.readUTF();
            }
        } catch (IOException e) {
            line = this.getName();
            System.out.println("----------------------------------------");
            try {
                StopThread();
            } catch (IOException ioException) {
                System.out.println("Error with doing the stop thread stuff");
            }
            System.out.println("IO Error: " + line + " terminated abruptly");
        } catch (NullPointerException e) {
            line = this.getName();
            System.out.println("----------------------------------------");
            System.out.println("Client " + line + " Closed");
            try {
                StopThread();
            } catch (IOException ioException) {
                System.out.println("Error with doing the stop thread stuff");
            }
        } finally {
            try {
                System.out.println("Connection Closing...");
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                    System.out.println("Socket Input Stream Closed");
                }

                if (objectInputStream != null) {
                    objectInputStream.close();
                    System.out.println("Socket Output Closed");
                }

                if (socket != null) {
                    StopThread();
                }
            } catch (IOException ie) {
                System.out.println("Socket Close Error");
            }
        }
    }
}