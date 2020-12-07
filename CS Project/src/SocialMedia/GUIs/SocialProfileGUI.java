package SocialMedia.GUIs;

import SocialMedia.Profile;
import SocialMedia.ServerAndClient.UserClient;
import SocialMedia.UserInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.util.List;

/**
 * The Social SocialMedia.Profile GUI.
 * It displays after the user logs in.
 * This class can only be run from UserInput.java!
 * @author Paul Gherghetta
 * @version 12/4/2020
 */

public class SocialProfileGUI extends JFrame implements ActionListener {

    public static JFrame listOfUsersFrame;
    public static Profile GUIProfile;
    //Components
    public static JLabel titleLabel;
    public static JButton usersButton;
    public static JButton myProfileButton;
    public static JButton friendsListButton;
    public static JButton logoutButton;
    public static JLabel nameLabel;
    public static JLabel usernameLabel;
    public static JLabel ageLabel;
    public static JLabel emailLabel;
    public static JLabel websiteLabel;
    public static JLabel likesInterestsLabel;
    public static JLabel friendsLabel;
    public static JLabel aboutMeLabel;
    public static JLabel aboutMeText;

    public static void createProfileGUI() {
        JFrame profileGUIFrame = new JFrame();
        JPanel profileGUIPanel = new JPanel();
        //sets the layout for the social profile GUI
        profileGUIPanel.setLayout(new BorderLayout());

    //NORTH section of profileGUIPanel
        titleLabel = new JLabel("                                                   " +
                "                                                     My Profile");
        profileGUIPanel.add(titleLabel, BorderLayout.NORTH);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    //SOUTH SECTION of profileGUIPanel
        //Makes a component panel for the south section
        JPanel profileGUISouthComponentPanel = new JPanel();
        profileGUISouthComponentPanel.setLayout(new FlowLayout());

        //Users button
        usersButton = new JButton("Users");
        listOfUsersFrame = new JFrame("Social Profile App");
        JPanel usersPanel = new JPanel();
        usersPanel.setLayout(new BoxLayout(usersPanel, BoxLayout.PAGE_AXIS));
        usersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                profileGUIFrame.setVisible(false);
                UsersListGUI.createUsersListGUI();
            }
        });

        myProfileButton = new JButton("My Profile");
        friendsListButton = new JButton("Friends List");
        friendsListButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                profileGUIFrame.setVisible(false);
                FriendsGUI.createFriendsGUI();
            }
        });

        //Logout button
        logoutButton = new JButton("Log Out");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //If logoutButton is clicked get rid of the profileGUIFrame and
                //return to the login screen.
                profileGUIFrame.setVisible(false);
                UserInput.makeLoginScreenVisible();
            }
        });

        profileGUISouthComponentPanel.add(usersButton);
        profileGUISouthComponentPanel.add(myProfileButton);
        profileGUISouthComponentPanel.add(friendsListButton);
        profileGUISouthComponentPanel.add(logoutButton);
        //Puts the south component panel into the south section of the
        //profileGUIPanel
        profileGUIPanel.add(profileGUISouthComponentPanel, BorderLayout.SOUTH);

    //EAST SECTION of profileGUIPanel
        //profileGUIPanel.add(new JButton("Layout"), BorderLayout.EAST);

    //WEST SECTION of profileGUIPanel
        JPanel profileGUIWestComponentPanel = new JPanel();
        profileGUIWestComponentPanel.setLayout(new BoxLayout(profileGUIWestComponentPanel,
                BoxLayout.PAGE_AXIS));
        profileGUIWestComponentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        //We need to get the profile created in UserInput here
        for (int i = 0; i < Profile.getProfilesList().size(); i++) {
            if (Profile.getProfilesList().get(i).getUsername().equals(UserInput.getUsernameAndPassword()[0])) {
                GUIProfile = Profile.getProfilesList().get(i);
                break;
            }
        }

        //Add 10 spaces before labels
        nameLabel = new JLabel("          Name: " + GUIProfile.getName());
        //Print the username label with the username entered from logging in
        usernameLabel = new JLabel("          Username: " + UserInput.getUsernameAndPassword()[0]);
        //Age label
        ageLabel = new JLabel("          Age: " + GUIProfile.getAge());
        //Email label
        emailLabel = new JLabel("          Email: " + GUIProfile.getEmail());
        //Website label
        websiteLabel = new JLabel("          Website: " + GUIProfile.getWebsite());
        //Likes/Interests label
        likesInterestsLabel = new JLabel("          Likes/Interests: " + GUIProfile.getInterests());
        //Friends label
        friendsLabel = new JLabel("          Friends: ");
        //About Me label
        aboutMeLabel = new JLabel("          About Me:");
        aboutMeText = new JLabel("          " + GUIProfile.getAboutMe());

        //Change fonts
        Font font = new Font("Cambria", Font.BOLD, 15);
        nameLabel.setFont(font);
        usernameLabel.setFont(font);
        ageLabel.setFont(font);
        emailLabel.setFont(font);
        websiteLabel.setFont(font);
        likesInterestsLabel.setFont(font);
        friendsLabel.setFont(font);
        aboutMeLabel.setFont(font);

        //Adds the components to the West panel and creates blank spaces
        //as appropriate.
        profileGUIWestComponentPanel.add(nameLabel, BorderLayout.WEST);
        profileGUIWestComponentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        profileGUIWestComponentPanel.add(usernameLabel, BorderLayout.WEST);
        profileGUIWestComponentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        profileGUIWestComponentPanel.add(ageLabel, BorderLayout.WEST);
        profileGUIWestComponentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        profileGUIWestComponentPanel.add(emailLabel, BorderLayout.WEST);
        profileGUIWestComponentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        profileGUIWestComponentPanel.add(websiteLabel, BorderLayout.WEST);
        profileGUIWestComponentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        profileGUIWestComponentPanel.add(likesInterestsLabel, BorderLayout.WEST);
        profileGUIWestComponentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        profileGUIWestComponentPanel.add(friendsLabel, BorderLayout.WEST);
        profileGUIWestComponentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        profileGUIWestComponentPanel.add(aboutMeLabel, BorderLayout.WEST);
        profileGUIWestComponentPanel.add(aboutMeText, BorderLayout.WEST);
        //Puts the west component panel into the west section of the
        //profileGUIPanel
        profileGUIPanel.add(profileGUIWestComponentPanel);

    //CENTER section of panel



        //Add profileGUIPanel to the JFrame profileGUIFrame, set screen size,
        //setDefaultCloseOperation and make the frame visible.
        profileGUIFrame.getContentPane().add(profileGUIPanel);
        profileGUIFrame.setSize(1000, 1000);
        profileGUIFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        profileGUIFrame.setVisible(true);
    }


    /**
     * Creates a window for a profile based on username
     * Used for when viewing another user's profile besides their own.
     * TODO
     * - Need to fix profiles list so it isn't pulling from a null list
     * @param username
     */
    public static void createProfileGUIFor(String username) {
        JFrame profileGUIFrame = new JFrame();
        JPanel profileGUIPanel = new JPanel();
        //sets the layout for the social profile GUI
        profileGUIPanel.setLayout(new BorderLayout());

        // changing Profile obj to that of username
        // should never be null
        // TODO revert back to getProfile method
        Profile profile = UserClient.getProfileWith(username);
      GUIProfile = profile;

        //NORTH section of profileGUIPanel
        titleLabel = new JLabel("                                                   " +
                "                                                     " + username +"'s Profile" );
        profileGUIPanel.add(titleLabel, BorderLayout.NORTH);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //SOUTH SECTION of profileGUIPanel
        //Makes a component panel for the south section
        JPanel profileGUISouthComponentPanel = new JPanel();
        profileGUISouthComponentPanel.setLayout(new FlowLayout());


        Profile currentUser = UserClient.profile;

        // Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener((ActionEvent event) -> {
            profileGUIFrame.setVisible(false);
            UsersListGUI.createUsersListGUI();
        });

        /** Need to check if currentUser is already friends with, or has an outgoing friend request already to desired user **/

        JButton sendFriendRequest = new JButton("Send Friend request");
        sendFriendRequest.addActionListener((ActionEvent event) -> {
           if (currentUser.getFriendsList().isFriendsWith(profile)) {
               JOptionPane.showMessageDialog(null, "You're already friends with " + profile.getUsername()+"!", "Social Media Profile App", JOptionPane.INFORMATION_MESSAGE);
           } else if (currentUser.getFriendsList().hasOutgoingFriendRequest(profile)) {
               JOptionPane.showMessageDialog(null, "You've already sent a friend request to " + profile.getUsername()+"!", "Social Media Profile App", JOptionPane.INFORMATION_MESSAGE);
           } else if (currentUser.getFriendsList().hasIncomingFriendRequest(profile)) {
               // accept the request
                JOptionPane.showMessageDialog(null, "You're now friends with " + profile.getUsername()+"!", "Social Media Profile App", JOptionPane.INFORMATION_MESSAGE);
                currentUser.getFriendsList().addFriend(profile);
                profile.getFriendsList().addFriend(currentUser);
            } else {
               // no connection to either
               JOptionPane.showMessageDialog(null, "Friend request sent to " + profile.getUsername()+"!", "Social Media Profile App", JOptionPane.INFORMATION_MESSAGE);
               currentUser.getFriendsList().getOutgoingFriendRequests().add(profile);
               profile.getFriendsList().getIncomingFriendRequests().add(profile);
           }
        });

        profileGUISouthComponentPanel.add(sendFriendRequest);
        profileGUISouthComponentPanel.add(backButton);

        //Puts the south component panel into the south section of the
        //profileGUIPanel
        profileGUIPanel.add(profileGUISouthComponentPanel, BorderLayout.SOUTH);

        //EAST SECTION of profileGUIPanel
        //profileGUIPanel.add(new JButton("Layout"), BorderLayout.EAST);

        //WEST SECTION of profileGUIPanel
        JPanel profileGUIWestComponentPanel = new JPanel();
        profileGUIWestComponentPanel.setLayout(new BoxLayout(profileGUIWestComponentPanel,
                BoxLayout.PAGE_AXIS));
        profileGUIWestComponentPanel.add(Box.createRigidArea(new Dimension(0, 30)));

//        for (int i = 0; i < Profile.getProfilesList().size(); i++) {
//            if (Profile.getProfilesList().get(i).getUsername().equals(UserInput.getUsernameAndPassword()[0])) {
//                GUIProfile = Profile.getProfilesList().get(i);
//                break;
//            }
//        }

        //Add 10 spaces before labels
        nameLabel = new JLabel("          Name: " + GUIProfile.getName());
        //Print the username label with the username entered from logging in
        usernameLabel = new JLabel("          Username: " + GUIProfile.getUsername());
        //Age label
        ageLabel = new JLabel("          Age: " + GUIProfile.getAge());
        //Email label
        emailLabel = new JLabel("          Email: " + GUIProfile.getEmail());
        //Website label
        websiteLabel = new JLabel("          Website: " + GUIProfile.getWebsite());
        //Likes/Interests label
        likesInterestsLabel = new JLabel("          Likes/Interests: " + GUIProfile.getInterests());
        //Friends label
        friendsLabel = new JLabel("          Friends: ");
        //About Me label
        aboutMeLabel = new JLabel("          About Me:");
        aboutMeText = new JLabel("          " + GUIProfile.getAboutMe());

        //Change fonts
        Font font = new Font("Cambria", Font.BOLD, 15);
        nameLabel.setFont(font);
        usernameLabel.setFont(font);
        ageLabel.setFont(font);
        emailLabel.setFont(font);
        websiteLabel.setFont(font);
        likesInterestsLabel.setFont(font);
        friendsLabel.setFont(font);
        aboutMeLabel.setFont(font);

        //Adds the components to the West panel and creates blank spaces
        //as appropriate.
        profileGUIWestComponentPanel.add(nameLabel, BorderLayout.WEST);
        profileGUIWestComponentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        profileGUIWestComponentPanel.add(usernameLabel, BorderLayout.WEST);
        profileGUIWestComponentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        profileGUIWestComponentPanel.add(ageLabel, BorderLayout.WEST);
        profileGUIWestComponentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        profileGUIWestComponentPanel.add(emailLabel, BorderLayout.WEST);
        profileGUIWestComponentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        profileGUIWestComponentPanel.add(websiteLabel, BorderLayout.WEST);
        profileGUIWestComponentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        profileGUIWestComponentPanel.add(likesInterestsLabel, BorderLayout.WEST);
        profileGUIWestComponentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        profileGUIWestComponentPanel.add(friendsLabel, BorderLayout.WEST);
        profileGUIWestComponentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        profileGUIWestComponentPanel.add(aboutMeLabel, BorderLayout.WEST);
        profileGUIWestComponentPanel.add(aboutMeText, BorderLayout.WEST);
        //Puts the west component panel into the west section of the
        //profileGUIPanel
        profileGUIPanel.add(profileGUIWestComponentPanel);

        //CENTER section of panel



        //Add profileGUIPanel to the JFrame profileGUIFrame, set screen size,
        //setDefaultCloseOperation and make the frame visible.
        profileGUIFrame.getContentPane().add(profileGUIPanel);
        profileGUIFrame.setSize(1000, 1000);
        profileGUIFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        profileGUIFrame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
