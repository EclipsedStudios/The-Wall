//A Sample login screen
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

/**
 * Represents the inital welcome screen for entering user inputs
 * @author paul gherghetta
 * @verseion 11/21/20
 */

public class UserInput extends JFrame implements ActionListener {
    //Components
    private JButton signInButton;
    public static JTextField username;
    public static JTextField password;

    private static final ArrayList<String> usernames = new ArrayList<>();
    private static final ArrayList<String> passwords = new ArrayList<>();



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                createWelcomeScreen();
            }});
    }

    public static void createWelcomeScreen() {
        //Create the welcome frame
        JFrame welcomeFrame = new JFrame("Social Network App");
        //add text fields and labels to welcome frame
        //Create a panel to allow for this.
        JPanel welcomePanel = new JPanel();
        //Set up the BoxLayout
        BoxLayout layout1 = new BoxLayout(welcomePanel, BoxLayout.Y_AXIS);
        welcomePanel.setLayout(layout1);

        welcomePanel.add(Box.createRigidArea(new Dimension(0, 100)));
        //Welcome/title message
        JLabel welcome = new JLabel("Welcome to the Social Profile App!");
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        //I think this sets the vertical alignment within the CENTER_ALIGNMENT
        welcome.setVerticalAlignment(JLabel.NORTH);
        welcomePanel.add(welcome);
        //Used for adding vertical blank space between components
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 150)));

        //Username label
        JLabel user = new JLabel("Username");
        user.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomePanel.add(user);

        //Username text box
        username = new JTextField(5);
        //Set the maximum size the text box can be when the screen is resized
        Dimension usernameTextBox = new Dimension(130, 30);
        username.setMaximumSize(usernameTextBox);
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcome.setVerticalAlignment(JLabel.CENTER);
        welcomePanel.add(username);

        //Password label
        JLabel pass = new JLabel("Password");
        pass.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomePanel.add(pass);

        //Password text box
        password = new JPasswordField(5);
        //Set the maximum size the text box can be when the screen is resized
        Dimension passwordTextBox = new Dimension(130, 30);
        password.setMaximumSize(passwordTextBox);
        password.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcomePanel.add(password);


        //Sign In Button
        JButton signInButton = new JButton("Sign In");
        signInButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcome.setVerticalAlignment(JLabel.CENTER);
        signInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //The code below is what executes when the sign in button is clicked
                try {
                    validateLoginCredentials();
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(null, "Error reading file!", "Social Network App",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        welcomePanel.add(signInButton);


        //Create an account button
        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createAccountButton.setVerticalAlignment(JLabel.CENTER);
        //Creates blank space between the buttons
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 30)));
        welcomePanel.add(createAccountButton);

        //Adding to the welcome frame
        welcomeFrame.getContentPane().add(welcomePanel);
        welcomeFrame.setSize(1000, 1000);
        welcomeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        welcomeFrame.setVisible(true);
    }


    //NOTE: This was just practice trying to validate the login credentials
    //We don't have to include that feature this way.
    public static void validateLoginCredentials() throws IOException {
        //boolean variables to check whether the username and the password inputted
        //matches a username and password in the arraylists.
        boolean usernameMatch = false;
        boolean passwordMatch = false;
        //Check for matching usernames and passwords
        for (int i = 0; i < usernames.size(); i++) {
            if (username != null) {
                if (usernames.get(i).equals(username.getText())) {
                    usernameMatch = true;
                }
            }
        }
        for (int i = 0; i < passwords.size(); i++) {
            if (password != null) {
                if (passwords.get(i).equals(password.getText())) {
                    passwordMatch = true;
                }
            }
        }
        if (usernameMatch == false || passwordMatch == false) {
            JOptionPane.showMessageDialog(null, "Invalid Login!",
                    "Social Network App", JOptionPane.ERROR_MESSAGE);
        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signInButton) {
            try {
                validateLoginCredentials();
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(null, "Error reading file!", "Social Network App",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
