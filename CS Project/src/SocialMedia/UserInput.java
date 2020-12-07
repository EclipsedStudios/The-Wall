package SocialMedia;

import SocialMedia.GUIs.SocialProfileGUI;
import SocialMedia.ServerAndClient.UserClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The login screen for the Social Profile App.
 * NOTE: username.txt files created as the result of creating an account
 * will not show in the side bar until the program has stopped running
 * so you need to remember your login credentials.
 *
 * @author Paul Gherghetta
 * @version 12/6/2020
 */

public class UserInput extends JFrame implements ActionListener {
    public static File UsernamesList;
    public static UserClient userClient;
    public static ArrayList<String> listOfUsernames = new ArrayList<>();
    public static JFrame welcomeFrame;
    public static Profile currentUserProfile;
    public static JTextField username;
    public static JPasswordField password;
    public static JTextField usernameTextField;
    public static JPasswordField passwordTextField;
    public static JTextField nameTextField;
    public static JTextField ageTextField;
    public static JTextField emailTextField;
    public static JTextField websiteTextField;
    public static JTextField likesInterestsTextField;
    public static JTextArea aboutMeTextArea;
    public static JPasswordField confirmPasswordTextField;
    public static JFrame createAccountFrame;
    //An array that holds the username and password to be sent
    //to the SocialProfile GUI.
    public static String[] usernameAndPassword;
    //Components
    public JButton signInButton;

    public static void main(String[] args) {
        //File that only has the names of people
        //File userNames.txt
        //List of usernames
        //Loop through
        //Create a file that only has the usernames of people in it
        userClient = new UserClient();
        try {
            userClient.refreshPageNoLogin();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File("ListOfUsernames.txt");
        File directory = new File("UsernameFiles");
        String[] UsernameFiles = directory.list();
        for (int i = 0; i < UsernameFiles.length; i++) {
            String[] fileUsername = UsernameFiles[i].split("\\.");
            listOfUsernames.add(fileUsername[0]);
        }
        //Now we can write the elements of listOfUsernames to a file
        UsernamesList = new File("ListOfUsernames.txt");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(UsernamesList, false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fos);
        for (int i = 0; i < listOfUsernames.size(); i++) {
            pw.println(listOfUsernames.get(i));
        }
        pw.close();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createWelcomeScreen();
            }
        });
    }

    public static void createWelcomeScreen() {
        //Create the welcome frame
        welcomeFrame = new JFrame("Social Profile App");
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
        Font font = new Font("Times New Roman", Font.BOLD, 30);
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
                    JOptionPane.showMessageDialog(null, "Error reading file!",
                            "Social Profile App",
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
        //exists with the username as the word before the .txt (in the
        //UsernameFiles folder!).
        System.out.println("test login");
        System.out.println("test login pass");
        try {
            System.out.println("Username: " + username.getText() + " Password: " + String.valueOf(password.getPassword()));
            Boolean loginSuccessful = userClient.login(username.getText(), String.valueOf(password.getPassword()));
            System.out.println("test login success " + loginSuccessful);
            if (!loginSuccessful) {
                JOptionPane.showMessageDialog(null, "Password or Username is invalid",
                        "Social Profile App", JOptionPane.ERROR_MESSAGE);
            } else if (loginSuccessful) {
                //Program should only reach this point if the login was valid
                JOptionPane.showMessageDialog(null, "Success! Logging in...",
                        "Social Profile App", JOptionPane.INFORMATION_MESSAGE);

                //I'm instantiating an array here that will contain the entered login and password
                usernameAndPassword = new String[2];
                usernameAndPassword[0] = username.getText();
                usernameAndPassword[1] = String.valueOf(password.getPassword());
                //Create a profile object for the user that logs in.
                if(userClient.isAlive()){
                    userClient.interrupt();
                    userClient = new UserClient();
                }
                userClient.start();

                //Now we can get rid of the old screen and call the next screen
                welcomeFrame.setVisible(false);
                SocialProfileGUI.createProfileGUI();
            }


        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    // Method to create a new account when the user clicks the create account button
    public static void createAccount() {
        createAccountFrame = new JFrame("Create Account");
        //An array of the labels to be used
        //Update: Friends label will not exist
        String[] labels = {"Username", "Password", "Full Name", "Age", "Email", "Friends",
                "Website", "Likes/Interests", "About Me"};
        //Variables that will be used to size all the text fields
        int textFieldWidth = 200;
        int textFieldHeight = 30;

        //Create the JPanel and set the layout
        JPanel createAccountPanel = new JPanel();
        createAccountPanel.setLayout(new BoxLayout(createAccountPanel, BoxLayout.Y_AXIS));

        //Username label
        JLabel usernameLabel = new JLabel(labels[0], JLabel.LEFT);
        usernameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        usernameLabel.setVerticalAlignment(JLabel.NORTH);
        createAccountPanel.add(usernameLabel);

        //Username text box
        usernameTextField = new JTextField(10);
        Dimension usernameTextBox = new Dimension(textFieldWidth, textFieldHeight);
        usernameTextField.setMaximumSize(usernameTextBox);
        usernameTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        createAccountPanel.add(usernameTextField);

        //Password Label
        JLabel passwordLabel = new JLabel(labels[1], JLabel.LEFT);
        passwordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        passwordLabel.setVerticalAlignment(JLabel.NORTH);
        createAccountPanel.add(passwordLabel);

        //Password text box
        passwordTextField = new JPasswordField(10);
        Dimension passwordTextBox = new Dimension(textFieldWidth, textFieldHeight);
        passwordTextField.setMaximumSize(usernameTextBox);
        passwordTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        createAccountPanel.add(passwordTextField);

        //Confirm Password label
        //Password Label
        JLabel confirmPasswordLabel = new JLabel("Confirm Password", JLabel.LEFT);
        confirmPasswordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        confirmPasswordLabel.setVerticalAlignment(JLabel.NORTH);
        createAccountPanel.add(confirmPasswordLabel);

        //Confirm Password text box
        confirmPasswordTextField = new JPasswordField(10);
        Dimension confirmPasswordTextBox = new Dimension(textFieldWidth, textFieldHeight);
        confirmPasswordTextField.setMaximumSize(confirmPasswordTextBox);
        confirmPasswordTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        createAccountPanel.add(confirmPasswordTextField);

        //Name label
        JLabel nameLabel = new JLabel(labels[2], JLabel.LEFT);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        nameLabel.setVerticalAlignment(JLabel.NORTH);
        createAccountPanel.add(nameLabel);

        //Name text box
        nameTextField = new JTextField(10);
        Dimension nameTextBox = new Dimension(textFieldWidth, textFieldHeight);
        nameTextField.setMaximumSize(usernameTextBox);
        nameTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        createAccountPanel.add(nameTextField);

        //Age label
        JLabel ageLabel = new JLabel(labels[3], JLabel.LEFT);
        ageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        ageLabel.setVerticalAlignment(JLabel.NORTH);
        createAccountPanel.add(ageLabel);

        //Age text box
        ageTextField = new JTextField(10);
        Dimension ageTextBox = new Dimension(textFieldWidth, textFieldHeight);
        ageTextField.setMaximumSize(ageTextBox);
        ageTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        createAccountPanel.add(ageTextField);

        //Email label
        JLabel emailLabel = new JLabel(labels[4], JLabel.LEFT);
        emailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        emailLabel.setVerticalAlignment(JLabel.NORTH);
        createAccountPanel.add(emailLabel);

        //Email text box
        emailTextField = new JTextField(10);
        Dimension emailTextBox = new Dimension(textFieldWidth, textFieldHeight);
        emailTextField.setMaximumSize(emailTextBox);
        emailTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        createAccountPanel.add(emailTextField);

        //Website label
        JLabel websiteLabel = new JLabel(labels[6], JLabel.LEFT);
        websiteLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        websiteLabel.setVerticalAlignment(JLabel.NORTH);
        createAccountPanel.add(websiteLabel);

        //Website text box
        websiteTextField = new JTextField(10);
        Dimension websiteTextBox = new Dimension(textFieldWidth, textFieldHeight);
        websiteTextField.setMaximumSize(websiteTextBox);
        websiteTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        createAccountPanel.add(websiteTextField);

        //Likes/Interests label
        JLabel likesInterestsLabel = new JLabel(labels[7], JLabel.LEFT);
        likesInterestsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        likesInterestsLabel.setVerticalAlignment(JLabel.NORTH);
        createAccountPanel.add(likesInterestsLabel);

        //Likes/Interests text box
        likesInterestsTextField = new JTextField(10);
        Dimension likesInterestsTextBox = new Dimension(textFieldWidth, textFieldHeight);
        likesInterestsTextField.setMaximumSize(likesInterestsTextBox);
        likesInterestsTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
        createAccountPanel.add(likesInterestsTextField);

        //About Me label
        JLabel aboutMeLabel = new JLabel(labels[8], JLabel.LEFT);
        aboutMeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        aboutMeLabel.setVerticalAlignment(JLabel.NORTH);
        createAccountPanel.add(aboutMeLabel);

        //About Me text area
        aboutMeTextArea = new JTextArea();
        aboutMeTextArea.setLineWrap(true);
        aboutMeTextArea.setWrapStyleWord(true);
        Dimension aboutMeTextBox = new Dimension(500, 200);
        aboutMeTextArea.setMaximumSize(aboutMeTextBox);
        aboutMeTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        createAccountPanel.add(aboutMeTextArea);

        //A button that will confirm the creation of a new account
        JButton confirmButton = new JButton("Create Account");
        confirmButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        confirmButton.setVerticalAlignment(JLabel.NORTH);
        //Set the text fields to "". This will be useful when error checking.
        //i.e. checking if the field was left blank
        usernameTextField.setText("");
        passwordTextField.setText("");
        confirmPasswordTextField.setText("");
        nameTextField.setText("");
        ageTextField.setText("");
        emailTextField.setText("");
        websiteTextField.setText("");
        likesInterestsTextField.setText("");
        aboutMeTextArea.setText("");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //performCreateAccountConfirmButtonAction() is already called
                //in the switch statement so there's no need to call it by itself.
                switch (performCreateAccountConfirmButtonAction()) {
                    case 1:
                        JOptionPane.showMessageDialog(null,
                                "You left one or more fields blank! Please fill all text boxes!",
                                "Social Profile App", JOptionPane.ERROR_MESSAGE);
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null, "Passwords " +
                                        "did not match!",
                                "Social Profile App", JOptionPane.ERROR_MESSAGE);
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null, "Make sure that " +
                                        "multiple likes/interests are separated by a comma and space!",
                                "Social Profile App", JOptionPane.ERROR_MESSAGE);
                        break;
                    case 4:
                        JOptionPane.showMessageDialog(null, "Email is " +
                                        "invalid!",
                                "Social Profile App", JOptionPane.ERROR_MESSAGE);
                        break;
                    case 5:
                        JOptionPane.showMessageDialog(null, "Website is " +
                                        "invalid!",
                                "Social Profile App", JOptionPane.ERROR_MESSAGE);
                        break;
                    case 6:
                        JOptionPane.showMessageDialog(null, "Username " +
                                        "already exists! " +
                                        "Please choose a different username.", "Social Profile App",
                                JOptionPane.ERROR_MESSAGE);
                        break;
                    case 0:
                        JOptionPane.showMessageDialog(null,
                                "Account was successfully created.", "Social Profile App",
                                JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        createAccountPanel.add(confirmButton);

        //Adds the create account panel to the create account frame and
        //sets a few other things like size of the frame.
        createAccountFrame.getContentPane().add(createAccountPanel);
        createAccountFrame.setSize(1000, 1000);
        createAccountFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        createAccountFrame.setVisible(true);
    }

    public static int performCreateAccountConfirmButtonAction() {
        //Now that the user has confirmed the creation of their new account, there
        //are a few things we'll check for first.
        //First, did they leave any text field blank?
        String password1 = String.valueOf(passwordTextField.getPassword());
        String password2 = String.valueOf(confirmPasswordTextField.getPassword());
        /*
        This is for testing purposes:
        String a = usernameTextField.getText();
        String b = nameTextField.getText();
        String c = ageTextField.getText();
        String d = emailTextField.getText();
        String e = friendsTextField.getText();
        String g = websiteTextField.getText();
        String h = likesInterestsTextField.getText();
        String i = aboutMeTextField.getText();
         */
        if (usernameTextField.getText().equals("") || password1.equals("") ||
                password2.equals("") ||
                nameTextField.getText().equals("") || ageTextField.getText().equals("") ||
                emailTextField.getText().equals("") ||
                websiteTextField.getText().equals("") ||
                likesInterestsTextField.getText().equals("") ||
                aboutMeTextArea.getText().equals("")) {
            return 1;
        }
        //Check if passwords matched
        password1 = String.valueOf(passwordTextField.getPassword());
        password2 = String.valueOf(confirmPasswordTextField.getPassword());
        if (!password1.equals(password2)) {
            return 2;
        }
        if (!emailTextField.getText().contains("@")) {
            return 4;
        }
        if (!websiteTextField.getText().contains(".")) {
            return 5;
        }
        //Make sure the username does not already exist
        File f = new File(usernameTextField.getText() + ".txt");
        if (f.getAbsoluteFile().exists()) {
            return 6;
        }

        try {
            userClient.refreshPageNoLogin();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //Then, check that likes/interests is separated by ", ".
        if (!likesInterestsTextField.getText().contains(", ")) {
            //Check for the input of just one like/interest
            int onlyOneLikeInterest = JOptionPane.showConfirmDialog(null, "Are " +
                            "you only inputting one like/interest?", "Social Profile App",
                    JOptionPane.YES_NO_OPTION);
            if (onlyOneLikeInterest == JOptionPane.NO_OPTION) {
                return 3;
            }
        }
        //We can very likely assume now that all text fields have the correct format.
        //Now we'll create a file and write the new account information to it.
        //We'll make sure to put the new file in the UsernameFiles directory first.
        File file = new File("UsernameClientsideStorage/" + usernameTextField.getText() + ".txt");
        FileOutputStream fOS = null;
        try {
            fOS = new FileOutputStream(file, false);
        } catch (FileNotFoundException fileNotFoundException) {
            JOptionPane.showMessageDialog(null,
                    "File Not Found Exception thrown!", "Social Profile App",
                    JOptionPane.ERROR_MESSAGE);
        }
        PrintWriter pw = new PrintWriter(fOS);

        for(Profile p : UserClient.profilesList){
            System.out.println(p.getUsername());
            if(p.getUsername().equals(usernameTextField.getText())){
                return 6;
            }
        }

        try {
            userClient.createAccount(nameTextField.getText(),
                    Integer.parseInt(ageTextField.getText()),
                    emailTextField.getText(),
                    websiteTextField.getText(),
                    new ArrayList<>(Arrays.asList(likesInterestsTextField.getText().split(", "))),
                    new FriendsList(),
                    aboutMeTextArea.getText(),
                    usernameTextField.getText(),
                    password2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOS = new FileOutputStream(UsernamesList, false);
            pw = new PrintWriter(fOS);
            listOfUsernames.add(usernameTextField.getText());
            for (int i = 0; i < listOfUsernames.size(); i++) {
                pw.println(listOfUsernames.get(i));
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //At this point, a new username.txt file was created.
        //Furthermore, the new username was added
        //We can now get rid of the create account frame.
        createAccountFrame.setVisible(false);
        return 0;
    }

    //A method to return the array with the login credentials entered.
    //It will be used in SocialProfileGUI.java
    public static String[] getUsernameAndPassword() {
        return usernameAndPassword;
    }

    //A method to make the login screen visible.
    //It will only be called when the user logs out from SocialProfileGUI.java.
    public static void makeLoginScreenVisible() {
        welcomeFrame.setVisible(true);
    }



    public static Profile getUserProfile() {
        return currentUserProfile;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signInButton) {
            try {
                validateLoginCredentials();
            } catch (IOException ioe) {
                System.out.println("test login 2");
                JOptionPane.showMessageDialog(null, "Error reading file!",
                        "Social Profile App",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
