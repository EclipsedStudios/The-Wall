import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

/**
 * Represents the initial welcome screen for entering user inputs.
 * @author Paul Gherghetta
 * @version 11/21/20
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
        JFrame welcomeFrame = new JFrame("Social Profile App");
        //add text fields and labels to welcome frame
        //Create a panel to allow for this.
        JPanel welcomePanel = new JPanel();
        //Set up the BoxLayout
        BoxLayout layout1 = new BoxLayout(welcomePanel, BoxLayout.Y_AXIS);
        welcomePanel.setLayout(layout1);

        welcomePanel.add(Box.createRigidArea(new Dimension(0, 100)));
        //Welcome/title message
        JLabel welcome = new JLabel("Social Profile App");
        //Changes the font
        Font font = new Font("Times New Roman", Font.BOLD,30);
        welcome.setFont(font);
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
                    JOptionPane.showMessageDialog(null, "Error reading file!", "Social Profile App",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        welcomePanel.add(signInButton);


        //Create an account button
        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createAccountButton.setVerticalAlignment(JLabel.CENTER);
        createAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createAccount();
            }
        });

        //Creates blank space between the buttons
        welcomePanel.add(Box.createRigidArea(new Dimension(0, 30)));
        welcomePanel.add(createAccountButton);

        //Adding to the welcome frame
        welcomeFrame.getContentPane().add(welcomePanel);
        welcomeFrame.setSize(1000, 1000);
        welcomeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        welcomeFrame.setVisible(true);
    }

    //A method to check if the username and password are valid
    //Invoked when sign in button is clicked
    public static void validateLoginCredentials() throws IOException {
        //The username will be the name of the file so I will check if a file
        //exists with the username as the word before the .txt.
        String usernameToValidate = username.getText();
        String testUsername = usernameToValidate + ".txt";
        File tempFile = new File(testUsername);
        boolean tempFileExists = tempFile.exists();
        //If a username as the file name could not be found, show an error message
        if (!tempFileExists) {
            JOptionPane.showMessageDialog(null, "Username is invalid!",
                    "Social Profile App", JOptionPane.ERROR_MESSAGE);
        } else if(tempFileExists) {
            FileReader fr = new FileReader(tempFile);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            //Keeps reading lines until it gets to the password line (which is the 8th line).
            for (int i = 0; i < 7; i++) {
                line = bfr.readLine();
            }
            //Store the password text in a variable
            String passwordToValidate = password.getText();
            if (!passwordToValidate.equals(line)) {
                JOptionPane.showMessageDialog(null, "Password is invalid!",
                        "Social Profile App", JOptionPane.ERROR_MESSAGE);
            } else {
                //Program should only reach this point if the login was valid
                JOptionPane.showMessageDialog(null, "Success! Logging in...",
                        "Social Profile App", JOptionPane.INFORMATION_MESSAGE);
                //Need to coordinate with groupmates on what method or frame should be called after
                //logging in
            }

        }
    }

    //Method to create a new account when the user clicks the create account button
    public static void createAccount() {
        JFrame createAccountFrame = new JFrame("Create Account");
        //An array of the labels to be used
        String[] labels = {"Username", "Password", "Name", "Age", "Email", "Friends",
                "Website", "Likes/Interests", "About Me"};
        //Create the JPanel and set the layout
        JPanel createAccountPanel = new JPanel();
        createAccountPanel.setLayout(new BoxLayout(createAccountPanel, BoxLayout.Y_AXIS));

        //Username label
        JLabel usernameLabel = new JLabel(labels[0], JLabel.LEFT);
        usernameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        usernameLabel.setVerticalAlignment(JLabel.NORTH);
        createAccountPanel.add(usernameLabel);

        //Username text box
        JTextField usernameTextField = new JTextField(10);
        Dimension usernameTextBox = new Dimension(130, 30);
        usernameTextField.setMaximumSize(usernameTextBox);
        usernameTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        createAccountPanel.add(usernameTextField);

        //Password Label
        JLabel passwordLabel = new JLabel(labels[1], JLabel.LEFT);
        passwordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        passwordLabel.setVerticalAlignment(JLabel.NORTH);
        createAccountPanel.add(passwordLabel);

        //Password text box
        JTextField passwordTextField = new JTextField(10);
        Dimension passwordTextBox = new Dimension(130, 30);
        passwordTextField.setMaximumSize(usernameTextBox);
        passwordTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        createAccountPanel.add(passwordTextField);

        //Name label
        JLabel nameLabel = new JLabel(labels[2], JLabel.LEFT);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        nameLabel.setVerticalAlignment(JLabel.NORTH);
        createAccountPanel.add(nameLabel);

        //Name text box
        JTextField nameTextField = new JTextField(10);
        Dimension nameTextBox = new Dimension(130, 30);
        nameTextField.setMaximumSize(usernameTextBox);
        nameTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        createAccountPanel.add(nameTextField);

        //Age label
        JLabel ageLabel = new JLabel(labels[3], JLabel.LEFT);
        ageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        ageLabel.setVerticalAlignment(JLabel.NORTH);
        createAccountPanel.add(ageLabel);

        //Age text box
        JTextField ageTextField = new JTextField(10);
        Dimension ageTextBox = new Dimension(130, 30);
        ageTextField.setMaximumSize(ageTextBox);
        ageTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        createAccountPanel.add(ageTextField);

        //Email label
        JLabel emailLabel = new JLabel(labels[4], JLabel.LEFT);
        emailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        emailLabel.setVerticalAlignment(JLabel.NORTH);
        createAccountPanel.add(emailLabel);

        //Email text box
        JTextField emailTextField = new JTextField(10);
        Dimension emailTextBox = new Dimension(130, 30);
        emailTextField.setMaximumSize(emailTextBox);
        emailTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        createAccountPanel.add(emailTextField);

        //Friends label
        JLabel friendsLabel = new JLabel(labels[5], JLabel.LEFT);
        friendsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        friendsLabel.setVerticalAlignment(JLabel.NORTH);
        createAccountPanel.add(friendsLabel);

        //Friends text box
        JTextField friendsTextField = new JTextField(10);
        Dimension friendsTextBox = new Dimension(130, 30);
        friendsTextField.setMaximumSize(friendsTextBox);
        friendsTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        createAccountPanel.add(friendsTextField);

        //Website label
        JLabel websiteLabel = new JLabel(labels[6], JLabel.LEFT);
        websiteLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        websiteLabel.setVerticalAlignment(JLabel.NORTH);
        createAccountPanel.add(websiteLabel);

        //Website text box
        JTextField websiteTextField = new JTextField(10);
        Dimension websiteTextBox = new Dimension(130, 30);
        websiteTextField.setMaximumSize(websiteTextBox);
        websiteTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        createAccountPanel.add(websiteTextField);

        //Likes/Interests label
        JLabel likesInterestsLabel = new JLabel(labels[7], JLabel.LEFT);
        likesInterestsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        likesInterestsLabel.setVerticalAlignment(JLabel.NORTH);
        createAccountPanel.add(likesInterestsLabel);

        //Likes/Interests text box
        JTextField likesInterestsTextField = new JTextField(10);
        Dimension likesInterestsTextBox = new Dimension(130, 30);
        likesInterestsTextField.setMaximumSize(likesInterestsTextBox);
        likesInterestsTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        createAccountPanel.add(likesInterestsTextField);

        //About Me label
        JLabel aboutMeLabel = new JLabel(labels[8], JLabel.LEFT);
        aboutMeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        aboutMeLabel.setVerticalAlignment(JLabel.NORTH);
        createAccountPanel.add(aboutMeLabel);

        //About Me text box
        JTextField aboutMeTextField = new JTextField(10);
        Dimension aboutMeTextBox = new Dimension(130, 30);
        aboutMeTextField.setMaximumSize(aboutMeTextBox);
        aboutMeTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        createAccountPanel.add(aboutMeTextField);

        //Will create a confirm button here soon that, when pressed, will create a new username.txt file
        //and write the information appropriately.


        createAccountFrame.getContentPane().add(createAccountPanel);
        createAccountFrame.setSize(1000, 1000);
        createAccountFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createAccountFrame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signInButton) {
            try {
                validateLoginCredentials();
            } catch (IOException ioe) {
                JOptionPane.showMessageDialog(null, "Error reading file!", "Social Profile App",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}