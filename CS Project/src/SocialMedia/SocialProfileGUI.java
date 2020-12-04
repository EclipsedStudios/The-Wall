package SocialMedia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

/**
 * The Social SocialMedia.Profile GUI.
 * It displays after the user logs in.
 * This class can only be run from UserInput.java!
 * @author Paul Gherghetta
 * @version 12/3/2020
 */

public class SocialProfileGUI extends JFrame implements ActionListener {

    //Components
    private static JLabel titleLabel;
    private static JButton usersButton;
    private static JButton myProfileButton;
    private static JButton friendsListButton;
    private static JButton logoutButton;
    private static JLabel nameLabel;
    private static JLabel usernameLabel;

    public static void createProfileGUI() {
        JFrame profileGUIFrame = new JFrame();
        JPanel profileGUIPanel = new JPanel();
        //sets the layout for the social profile GUI
        profileGUIPanel.setLayout(new BorderLayout());

        //NORTH section of panel
        titleLabel = new JLabel("My SocialMedia.Profile");
        profileGUIPanel.add(titleLabel, BorderLayout.NORTH);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //SOUTH section of panel
        //Makes a component panel for the south section
        JPanel profileGUISouthComponentPanel = new JPanel();
        profileGUISouthComponentPanel.setLayout(new FlowLayout());
        usersButton = new JButton("Users");
        myProfileButton = new JButton("My SocialMedia.Profile");
        friendsListButton = new JButton("Friends List");
        logoutButton = new JButton("Log Out");
        profileGUISouthComponentPanel.add(usersButton);
        profileGUISouthComponentPanel.add(myProfileButton);
        profileGUISouthComponentPanel.add(friendsListButton);
        profileGUISouthComponentPanel.add(logoutButton);
        //Puts the south component panel into the south section of the
        //profileGUIPanel
        profileGUIPanel.add(profileGUISouthComponentPanel, BorderLayout.SOUTH);

        //EAST section of panel
        //profileGUIPanel.add(new JButton("Layout"), BorderLayout.EAST);

        //WEST section of panel
        JPanel profileGUIWestComponentPanel = new JPanel();
        profileGUIWestComponentPanel.setLayout(new BoxLayout(profileGUIWestComponentPanel,
                BoxLayout.PAGE_AXIS));
        profileGUIWestComponentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        nameLabel = new JLabel("                                  Name:");
        //Print the username label with the username entered from logging in
        usernameLabel = new JLabel("Username: " + UserInput.getUsernameAndPassword()[0]);
        Font font = new Font("Cambria", Font.BOLD,15);
        nameLabel.setFont(font);
        usernameLabel.setFont(font);
        profileGUIWestComponentPanel.add(nameLabel, BorderLayout.WEST);
        profileGUIWestComponentPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        profileGUIWestComponentPanel.add(usernameLabel, BorderLayout.WEST);
        //Puts the center component panel into the center section of the
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
