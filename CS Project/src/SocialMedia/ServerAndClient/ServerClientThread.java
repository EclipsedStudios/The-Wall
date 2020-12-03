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
    String line = null;
    BufferedReader bufferedReader = null;
    PrintWriter printWriter = null;
    Socket socket;
    ObjectOutputStream objectOutputStream = null;
    ObjectInputStream objectInputStream = null;
    ServerObjectStorage serverObjectStorage;
    Profile profile;

    public ServerClientThread(Socket socket, ServerObjectStorage serverObjectStorage, Profile profile) {
        this.socket = socket;
        this.serverObjectStorage = serverObjectStorage;
        this.profile = profile;
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

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            printWriter = new PrintWriter(outputStream);

            // create a ObjectOutputStream so we can write data from it
            objectOutputStream = new ObjectOutputStream(outputStream);
            // create a ObjectInputStream so we can read data from it
            objectInputStream = new ObjectInputStream(inputStream);
        } catch (IOException e) {
            System.out.println("IO error in server thread");
        }

        try {
            for(Profile a : serverObjectStorage.users){
                System.out.println(a);
            }
            System.out.println("=======");
            line = bufferedReader.readLine();
            while (line.compareToIgnoreCase("quit") != 0) {
                switch (line) {
                    case "stop server" -> System.exit(0);
                    case "see users" -> {
                        System.out.println("User has tried to see users");
                        objectOutputStream.reset();
                        objectOutputStream.writeObject(serverObjectStorage.users);
                        objectOutputStream.flush();
                    }
                    default -> {
                        printWriter.println("You said: " + line);
                        printWriter.flush();
                    }
                }
                System.out.println(socket.getInetAddress() + ":" + socket.getPort() + " said: " + line);
                line = bufferedReader.readLine();
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
                if (bufferedReader != null) {
                    bufferedReader.close();
                    System.out.println("Socket Input Stream Closed");
                }

                if (printWriter != null) {
                    printWriter.close();
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