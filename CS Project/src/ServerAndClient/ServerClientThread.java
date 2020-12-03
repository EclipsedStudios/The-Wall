package ServerAndClient;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

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

    public volatile ArrayList<String> users = CentralServer.users;

    public void RefreshUsers(ArrayList<String> users){
        this.users = users;
    }

    public void TestTheApplication(){
        System.out.println("Socket works!");
    }

    public ServerClientThread(Socket socket) {
        this.socket = socket;
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
            for(String a : users){
                System.out.println(a);
            }
            System.out.println("=======");
            line = bufferedReader.readLine();
            while (line.compareToIgnoreCase("quit") != 0) {
                switch (line) {
                    case "stop server" -> System.exit(0);
                    case "see users" -> {
                        CentralServer.RefreshGlobally();
                        System.out.println("User has tried to see users");
                        objectOutputStream.writeObject(users);
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
            System.out.println("IO Error: " + line + " terminated abruptly");
        } catch (NullPointerException e) {
            line = this.getName();
            System.out.println("----------------------------------------");
            System.out.println("Client " + line + " Closed");
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
                    CentralServer.users.remove(String.valueOf(socket.getInetAddress()));
                    socket.close();
                    System.out.println("Socket Closed");
                    CentralServer.numberOfConnections--;
                    CentralServer.users.remove(String.valueOf(socket.getInetAddress()) + (socket.getPort()));
                    CentralServer.serverClientThreads.remove(this);
                    System.out.println("----------------------------------------");
                }
            } catch (IOException ie) {
                System.out.println("Socket Close Error");
            }
        }
    }
}