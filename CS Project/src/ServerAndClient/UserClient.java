package ServerAndClient;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Represents the user client
 *
 * @author aakash jariwala, jaden baker
 * @version 11/21/20
 */
public class UserClient {

    public static void main(String[] args) throws IOException {

        InetAddress address = InetAddress.getLocalHost(); // Get localhost
        Socket socket = null; // Create a null socket
        String line; // Store information coming from server
        BufferedReader bufferedReader = null; // BufferedReader for client
        BufferedReader bufferedReader1 = null; // BufferedReader for client's server thread
        PrintWriter printWriter = null; // PrintWriter for client to send info to server
        ObjectInputStream objectInputStream = null;

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
        System.out.println("[FOR TESTING] Send a message for the server to respond back with:");

        try {
            assert bufferedReader != null;
            System.out.println("Checking for messages");
            line = bufferedReader.readLine();
            assert printWriter != null;
            printWriter.println(line);
            printWriter.flush();
            while (line.compareToIgnoreCase("quit") != 0) {
                if (line.equals("see users")) {
                    assert objectInputStream != null;
                    System.out.println("Checking for objects");
                    String[] arraylist = (String[]) objectInputStream.readObject();
                    if (arraylist != null) {
                        System.out.println("Checking for objects");
                        for (Object a : arraylist) {
                            System.out.println(a);
                            System.out.println("----");
                        }
                    }
                }
                System.out.println("Checking for messages");
                line = bufferedReader.readLine();
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
            bufferedReader1.close();
            printWriter.close();
            bufferedReader.close();
            socket.close();

            //Client closed confirmation
            System.out.println("Connection Closed");
        }
    }
}