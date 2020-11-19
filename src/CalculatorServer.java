import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * HW-11 - Calculator Server
 * <p>
 * This is the server for the calculator.
 * It takes in the client's equation,
 * calculates it, and sends it back to the client.
 * If the user quits, the client sends a message of "close",
 * which then closes this server.
 * <p>
 *
 * @author Jaden Baker, 004
 * @version 11/9/2020
 */
public class CalculatorServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1);


        System.out.println("Waiting for the client to connect...");
        Socket socket = serverSocket.accept();
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        System.out.println("Client connected!");

        String message = "";
        double answer = 0;
        do {
            message = reader.readLine();
            String[] messageSplit = message.split(" ");
            if (message.contains("+")) {
                answer = Double.parseDouble(messageSplit[0]) + Double.parseDouble(messageSplit[2]);
                writer.write(answer + "");
                writer.println();
                writer.flush();
            } else if (message.contains("/")) {
                answer = Double.parseDouble(messageSplit[0]) / Double.parseDouble(messageSplit[2]);
                writer.write(answer + "");
                writer.println();
                writer.flush();
            } else if (message.contains("*")) {
                answer = Double.parseDouble(messageSplit[0]) * Double.parseDouble(messageSplit[2]);
                writer.write(answer + "");
                writer.println();
                writer.flush();
            } else if (message.contains("-")) {
                answer = Double.parseDouble(messageSplit[0]) - Double.parseDouble(messageSplit[2]);
                writer.write(answer + "");
                writer.println();
                writer.flush();
            }
        } while (!message.equals("close"));


        // Ensure data is sent to the client.

        writer.close();
        reader.close();
    }
}