import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * HW-11 - Calculator Client
 * <p>
 * This is the client for the calculator.
 * It prompts the user asking for the server's port and host name.
 * Then, it will ask the user for equations,
 * alerting the user if the format is wrong.
 * The user is then shown the answer and later asked if they want to continue.
 * If the user quits,
 * the client sends a message of "close" which tells it to close the server.
 * <p>
 *
 * @author Jaden Baker, 004
 * @version 11/9/2020
 */
public class CalculatorClient {

    public static void main(String[] args) throws IOException {
        JOptionPane.showMessageDialog(null, "Welcome to the Calculator Client!",
                "Calculator", JOptionPane.INFORMATION_MESSAGE);
        Socket socket = createSocket();
        JOptionPane.showMessageDialog(null, "Connected to server!",
                "Calculator", JOptionPane.INFORMATION_MESSAGE);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        boolean keepCalculating = true;
        do {
            String response = checkEquation();
            writer.write(response);
            writer.println();
            writer.flush(); // ensure data is sent to the server
            String s1 = reader.readLine();
            JOptionPane.showMessageDialog(null, "Equation answer: " + s1,
                    "Calculator", JOptionPane.INFORMATION_MESSAGE);
            int dialogButton = JOptionPane.showConfirmDialog(null,
                    "Would you like to calculate more?",
                    "Calculator",
                    JOptionPane.YES_NO_OPTION);
            if (dialogButton == JOptionPane.NO_OPTION) {
                keepCalculating = false;
                writer.write("close");
                writer.println();
                writer.flush();
            }
        } while (keepCalculating);
        writer.close();
        reader.close();
    }

    public static Socket createSocket() {
        Socket output;
        try {
            String hostName = "";
            int port = 0;

            hostName = JOptionPane.showInputDialog(null, "Enter the host name of the server",
                    "Calculator", JOptionPane.QUESTION_MESSAGE);

            port = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the port number",
                    "Calculator", JOptionPane.QUESTION_MESSAGE));
            output = new Socket(hostName, port);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "You must enter a valid port and host name! " +
                            "Is the server running?",
                    "Calculator", JOptionPane.ERROR_MESSAGE);
            output = createSocket();
        }
        return output;
    }

    public static String checkEquation() {
        String equation = "";
        boolean equationIsCorrect = false;
        do {
            String equationInput = JOptionPane.showInputDialog(null, "Enter your equation",
                    "Test Client", JOptionPane.QUESTION_MESSAGE);
            if (equationInput.split(" ").length != 3) {
                JOptionPane.showMessageDialog(null, "Re-enter the equation.\n" +
                                "You must have a space between the operator and numbers. \n" +
                                "(Ex: 2 + 2). Only +,-,*,/ are supported operators",
                        "Calculator", JOptionPane.ERROR_MESSAGE);
            } else if ((equationInput.split(" ")[1].equals("/") ||
                    equationInput.split(" ")[1].equals("-") ||
                    equationInput.split(" ")[1].equals("+") ||
                    equationInput.split(" ")[1].equals("*")) &&
                    equationInput.split(" ").length == 3) {
                equation = equationInput;
                equationIsCorrect = true;
            } else {
                JOptionPane.showMessageDialog(null, "Re-enter the equation.\n" +
                                "You must have a space between the operator and numbers. \n" +
                                "(Ex: 2 + 2). Only +,-,*,/ are supported operators",
                        "Calculator", JOptionPane.ERROR_MESSAGE);
            }
        } while (!equationIsCorrect);
        return equation;
    }

}