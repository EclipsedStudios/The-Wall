package SocialMedia.GUIs;

import SocialMedia.Profile;
import SocialMedia.ServerAndClient.UserClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Friends GUI.
 * Displays the user's friends and allows friend requests to be accepted
 *
 * @author Aakash Jariwala
 * @version 12/5/2020
 */

public class FriendsGUI extends JFrame implements ActionListener {

    public static List<Profile> friendsList;
    public static JLabel titleLabel;

    public static void createFriendsGUI() {

        friendsList = Profile.getFriendsList();

        Font font = new Font("Cambria", Font.BOLD, 15);
        JFrame friendsFrame = new JFrame();
        JPanel friendsPanel = new JPanel();
        friendsPanel.setLayout(new BorderLayout());

        // NORTH section of friendsPanel
        titleLabel = new JLabel("Friends");
        titleLabel.setFont(font);

        friendsPanel.add(titleLabel, BorderLayout.NORTH);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        friendsFrame.getContentPane().add(friendsPanel);

        friendsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        friendsFrame.setSize(1200, 800);
        friendsFrame.setVisible(true);

        }
        @Override
        public void actionPerformed(ActionEvent e) {

    }
}