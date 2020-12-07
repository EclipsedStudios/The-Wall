package SocialMedia.GUIs;

import SocialMedia.FriendsList;
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

        friendsList = UserClient.profile.getFriendsList().getFriends();

        Font font = new Font("Cambria", Font.BOLD, 15);
        JFrame friendsFrame = new JFrame("Friends Menu");
        JPanel friendsPanel = new JPanel();
        friendsPanel.setLayout(new BorderLayout());

        JPanel optionsSouthPanel = new JPanel();
        optionsSouthPanel.setLayout(new FlowLayout());

        // NORTH section of friendsPanel
        titleLabel = new JLabel("                                                   " +
                "                                                     Friends: (" + friendsList.size() + ")");
        titleLabel.setFont(font);

        friendsPanel.add(titleLabel, BorderLayout.NORTH);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        int counter = 0;
        JList<String> list;
        String[] friends = new String[friendsList.size()];
        for (int i = 0; i < friendsList.size(); i++) {
            friends[counter] = friendsList.get(i).getName();
            counter++;
        }

        list = new JList<>(friends);
        list.setSelectedIndex(0);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener((ActionEvent event) -> {
            friendsFrame.setVisible(false);
            SocialProfileGUI.createProfileGUI();
        });

        optionsSouthPanel.add(backButton);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);

        friendsPanel.add(list, BorderLayout.WEST);
        friendsPanel.add(optionsSouthPanel, BorderLayout.SOUTH);
        // friendsPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        friendsFrame.getContentPane().add(friendsPanel);

        friendsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        friendsFrame.setSize(1000,1000);
        friendsFrame.setVisible(true);
    }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
}
