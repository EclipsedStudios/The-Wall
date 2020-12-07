package SocialMedia.GUIs;

import SocialMedia.Profile;
import SocialMedia.ServerAndClient.UserClient;
import SocialMedia.UserInput;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.*;

/**
 * The Editing Profile GUI.
 * Invoked when user clicks edit profile button in SocialProfileGUI.java.
 * @author Paul Gherghetta
 * @version 12/6/2020
 */

public class EditProfileGUI extends JFrame implements ActionListener {

    private static JLabel editProfileInstructions;
    private static JButton nameButton;
    private static JButton emailButton;
    private static JButton websiteButton;
    private static JButton likesInterestsButton;
    private static JButton passwordButton;
    private static JButton aboutMeButton;
    private static JButton doneButton;

    public static void createEditProfileGUI() {
        JFrame editProfileFrame = new JFrame();
        JPanel editProfilePanel = new JPanel();
        //Set the layout for the panel
        editProfilePanel.setLayout(new BoxLayout(editProfilePanel, BoxLayout.PAGE_AXIS));

        //Add buttons for editing each part of the profile
        //First, instructions should be added.
        editProfileInstructions = new JLabel("Click on the button for the part of the current profile" +
                " you want to edit.");

        //Initialize buttons and add actionPerformed method to each of them
        nameButton = new JButton("Name");
        nameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Use JOptionPane input text field to let the user enter a new name
                //Then set the name in the GUI profile
                JFrame setNameFrame = new JFrame();
                String newName = JOptionPane.showInputDialog(setNameFrame,"Input new name:");
                SocialProfileGUI.getGUIProfile().setName(newName);
                //We need to change the corresponding username.txt file next.
                File f = new File("UsernameFiles/" + SocialProfileGUI.getGUIProfile().getUsername() +
                        ".txt");
                FileReader fr = null;
                try {
                    fr = new FileReader(f);
                    BufferedReader bfr = new BufferedReader(fr);
                    String line = bfr.readLine();
                    String name = line;
                    //email
                    line = bfr.readLine();
                    String email = line;
                    //friends
                    line = bfr.readLine();
                    String friends = line;
                    //website
                    line = bfr.readLine();
                    String website = line;
                    //likes/interests
                    line = bfr.readLine();
                    String likesInterests = line;
                    //about me
                    line = bfr.readLine();
                    String aboutMe = line;
                    //age
                    line = bfr.readLine();
                    String age = line;
                    //password
                    line = bfr.readLine();
                    String password = line;

                    FileOutputStream fos = new FileOutputStream(f, false);
                    PrintWriter pw = new PrintWriter(fos);
                    pw.println(newName);
                    pw.println(email);
                    pw.println(friends);
                    pw.println(website);
                    pw.println(likesInterests);
                    pw.println(aboutMe);
                    pw.println(age);
                    pw.println(password);
                    pw.close();
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }
        });

        emailButton = new JButton("Email");
        emailButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame setEmailFrame = new JFrame();
                String newEmail = JOptionPane.showInputDialog(setEmailFrame, "Input new email:");
                SocialProfileGUI.getGUIProfile().setEmail(newEmail);
                File f = new File("UsernameFiles/" + SocialProfileGUI.getGUIProfile().getUsername() +
                        ".txt");
                FileReader fr = null;
                try {
                    fr = new FileReader(f);
                    BufferedReader bfr = new BufferedReader(fr);
                    String line = bfr.readLine();
                    String name = line;
                    //email
                    line = bfr.readLine();
                    String email = line;
                    //friends
                    line = bfr.readLine();
                    String friends = line;
                    //website
                    line = bfr.readLine();
                    String website = line;
                    //likes/interests
                    line = bfr.readLine();
                    String likesInterests = line;
                    //about me
                    line = bfr.readLine();
                    String aboutMe = line;
                    //age
                    line = bfr.readLine();
                    String age = line;
                    //password
                    line = bfr.readLine();
                    String password = line;

                    FileOutputStream fos = new FileOutputStream(f, false);
                    PrintWriter pw = new PrintWriter(fos);
                    pw.println(name);
                    pw.println(newEmail);
                    pw.println(friends);
                    pw.println(website);
                    pw.println(likesInterests);
                    pw.println(aboutMe);
                    pw.println(age);
                    pw.println(password);
                    pw.close();
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }
        });


        websiteButton = new JButton("Website");
        websiteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame setWebsiteFrame = new JFrame();
                String newWebsite = JOptionPane.showInputDialog(setWebsiteFrame, "Input new website:");
                SocialProfileGUI.getGUIProfile().setWebsite(newWebsite);
                File f = new File("UsernameFiles/" + SocialProfileGUI.getGUIProfile().getUsername() +
                        ".txt");
                FileReader fr = null;
                try {
                    fr = new FileReader(f);
                    BufferedReader bfr = new BufferedReader(fr);
                    String line = bfr.readLine();
                    String name = line;
                    //email
                    line = bfr.readLine();
                    String email = line;
                    //friends
                    line = bfr.readLine();
                    String friends = line;
                    //website
                    line = bfr.readLine();
                    String website = line;
                    //likes/interests
                    line = bfr.readLine();
                    String likesInterests = line;
                    //about me
                    line = bfr.readLine();
                    String aboutMe = line;
                    //age
                    line = bfr.readLine();
                    String age = line;
                    //password
                    line = bfr.readLine();
                    String password = line;

                    FileOutputStream fos = new FileOutputStream(f, false);
                    PrintWriter pw = new PrintWriter(fos);
                    pw.println(name);
                    pw.println(email);
                    pw.println(friends);
                    pw.println(newWebsite);
                    pw.println(likesInterests);
                    pw.println(aboutMe);
                    pw.println(age);
                    pw.println(password);
                    pw.close();
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }
        });

        likesInterestsButton = new JButton("Likes/Interests");
        ArrayList<String> newLikesInterests = new ArrayList<>();
        likesInterestsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame setLikesInterestsFrame = new JFrame();
                String likesInterests = null;
                String finalStringofLikesInterests = null;
                likesInterests = JOptionPane.showInputDialog(setLikesInterestsFrame, "Input " +
                        "your new likes/interests.");
                while (!likesInterests.contains(", ")) {
                    likesInterests = JOptionPane.showInputDialog(setLikesInterestsFrame, "Input " +
                            "your new likes/interests again.");
                    int onlyOneLikeInterest = JOptionPane.showConfirmDialog(null, "Are " +
                                    "you only inputting one like/interest?", "Social Profile App",
                            JOptionPane.YES_NO_OPTION);
                    if (onlyOneLikeInterest == JOptionPane.YES_OPTION) {
                        newLikesInterests.add(likesInterests);
                        SocialProfileGUI.getGUIProfile().setInterests(newLikesInterests);
                        finalStringofLikesInterests = likesInterests;
                        break;
                    } else if (onlyOneLikeInterest == JOptionPane.NO_OPTION) {
                        JOptionPane.showMessageDialog(null, "Make sure you separate " +
                                "likes/interests by a comma and space. If you included a comma and space after " +
                                "receiving this message, the profile will update.");
                    }
                }
                if (finalStringofLikesInterests == null) {
                    String[] splitNewLikesInterests = likesInterests.split(", ");
                    for (String element : splitNewLikesInterests) {
                        newLikesInterests.add(element);
                    }
                    SocialProfileGUI.getGUIProfile().setInterests(newLikesInterests);
                    finalStringofLikesInterests = likesInterests;
                }
                File f = new File("UsernameFiles/" + SocialProfileGUI.getGUIProfile().getUsername() +
                        ".txt");
                FileReader fr = null;
                try {
                    fr = new FileReader(f);
                    BufferedReader bfr = new BufferedReader(fr);
                    String line = bfr.readLine();
                    String name = line;
                    //email
                    line = bfr.readLine();
                    String email = line;
                    //friends
                    line = bfr.readLine();
                    String friends = line;
                    //website
                    line = bfr.readLine();
                    String website = line;
                    //likes/interests
                    line = bfr.readLine();
                    String likesAndInterests = line;
                    //about me
                    line = bfr.readLine();
                    String aboutMe = line;
                    //age
                    line = bfr.readLine();
                    String age = line;
                    //password
                    line = bfr.readLine();
                    String password = line;

                    FileOutputStream fos = new FileOutputStream(f, false);
                    PrintWriter pw = new PrintWriter(fos);
                    pw.println(name);
                    pw.println(email);
                    pw.println(friends);
                    pw.println(website);
                    pw.println("[" + finalStringofLikesInterests + "]");
                    pw.println(aboutMe);
                    pw.println(age);
                    pw.println(password);
                    pw.close();
                } catch (IOException io) {
                    io.printStackTrace();
                }

            }
        });

        aboutMeButton = new JButton("About Me");
        aboutMeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame setAboutMeFrame = new JFrame();
                String newAboutMeText = JOptionPane.showInputDialog(setAboutMeFrame, "Input new information " +
                        "about yourself." );
                SocialProfileGUI.getGUIProfile().setAboutMe(newAboutMeText);
                File f = new File("UsernameFiles/" + SocialProfileGUI.getGUIProfile().getUsername() +
                        ".txt");
                FileReader fr = null;
                try {
                    fr = new FileReader(f);
                    BufferedReader bfr = new BufferedReader(fr);
                    String line = bfr.readLine();
                    String name = line;
                    //email
                    line = bfr.readLine();
                    String email = line;
                    //friends
                    line = bfr.readLine();
                    String friends = line;
                    //website
                    line = bfr.readLine();
                    String website = line;
                    //likes/interests
                    line = bfr.readLine();
                    String likesInterests = line;
                    //about me
                    line = bfr.readLine();
                    String aboutMe = line;
                    //age
                    line = bfr.readLine();
                    String age = line;
                    //password
                    line = bfr.readLine();
                    String password = line;

                    FileOutputStream fos = new FileOutputStream(f, false);
                    PrintWriter pw = new PrintWriter(fos);
                    pw.println(name);
                    pw.println(email);
                    pw.println(friends);
                    pw.println(website);
                    pw.println(likesInterests);
                    pw.println(newAboutMeText);
                    pw.println(age);
                    pw.println(password);
                    pw.close();
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }
        });

        passwordButton = new JButton("Password");
        passwordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame setPasswordFrame = new JFrame();
                String newPassword = "";
                String confirmNewPassword = "empty";
                while (!newPassword.equals(confirmNewPassword)) {
                    newPassword = JOptionPane.showInputDialog(setPasswordFrame, "Input new password:");
                    confirmNewPassword = JOptionPane.showInputDialog(setPasswordFrame, "Confirm password:");
                    if (!newPassword.equals(confirmNewPassword)) {
                        JOptionPane.showMessageDialog(null, "Passwords did not match!",
                                "Social Profile App", JOptionPane.ERROR_MESSAGE);
                    }
                }
                File f = new File("UsernameFiles/" + SocialProfileGUI.getGUIProfile().getUsername() +
                        ".txt");
                FileReader fr = null;
                try {
                    fr = new FileReader(f);
                    BufferedReader bfr = new BufferedReader(fr);
                    String line = bfr.readLine();
                    String name = line;
                    //email
                    line = bfr.readLine();
                    String email = line;
                    //friends
                    line = bfr.readLine();
                    String friends = line;
                    //website
                    line = bfr.readLine();
                    String website = line;
                    //likes/interests
                    line = bfr.readLine();
                    String likesInterests = line;
                    //about me
                    line = bfr.readLine();
                    String aboutMe = line;
                    //age
                    line = bfr.readLine();
                    String age = line;
                    //password
                    line = bfr.readLine();
                    String password = line;

                    FileOutputStream fos = new FileOutputStream(f, false);
                    PrintWriter pw = new PrintWriter(fos);
                    pw.println(name);
                    pw.println(email);
                    pw.println(friends);
                    pw.println(website);
                    pw.println(likesInterests);
                    pw.println(aboutMe);
                    pw.println(age);
                    pw.println(newPassword);
                    pw.close();
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }
        });

        //Button to confirm you are done editing the profile
        doneButton = new JButton("Done");
        doneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editProfileFrame.setVisible(false);
                SocialProfileGUI.createProfileGUI();
            }
        });

        //Set fonts
        Font font = new Font("Cambria", Font.BOLD, 15);
        editProfileInstructions.setFont(font);

        //Add components to editProfilePanel
        editProfilePanel.add(editProfileInstructions);
        editProfilePanel.add(nameButton);
        editProfilePanel.add(emailButton);
        editProfilePanel.add(websiteButton);
        editProfilePanel.add(likesInterestsButton);
        editProfilePanel.add(aboutMeButton);
        editProfilePanel.add(passwordButton);
        editProfilePanel.add(Box.createRigidArea(new Dimension(0, 30)));
        editProfilePanel.add(doneButton);

        editProfileFrame.getContentPane().add(editProfilePanel);
        editProfileFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        editProfileFrame.setSize(1000, 1000);
        editProfileFrame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
