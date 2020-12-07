package SocialMedia.GUIs;

import SocialMedia.FriendsList;
import SocialMedia.Profile;
import SocialMedia.ServerAndClient.UserClient;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Friends GUI.
 * Displays the user's friends and allows friend requests to be accepted or denied
 *
 * @author Aakash Jariwala
 * @version 12/5/2020
 */

public class FriendsGUI extends JFrame implements ActionListener {

    public static List<Profile> friendsList;
    public static List<Profile> incomingList;
    public static List<Profile> outgoingList;
    public static JLabel titleLabel;
    public static JLabel incomingLabel;
    public static JLabel outgoingLabel;

    public static void createFriendsGUI() {

        friendsList = UserClient.profile.getFriendsList().friends;
        incomingList = UserClient.profile.friendsList.getIncomingFriendRequests();
        outgoingList = UserClient.profile.friendsList.getOutgoingFriendRequests();

        Font font = new Font("Cambria", Font.BOLD, 15);
        JFrame friendsFrame = new JFrame("Friends Menu");
        JPanel friendsPanel = new JPanel();
        JPanel incomingPanel = new JPanel();
        JPanel outgoingPanel = new JPanel();
        friendsPanel.setLayout(new BorderLayout());
        incomingPanel.setLayout(new BorderLayout());
        outgoingPanel.setLayout(new BorderLayout());

        JPanel optionsSouthPanel = new JPanel();
        optionsSouthPanel.setLayout(new FlowLayout());

        // friendsPanel stuff
        titleLabel = new JLabel("Friends: (" + friendsList.size() + ")");
        titleLabel.setFont(font);
        friendsPanel.add(titleLabel, BorderLayout.NORTH);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        int counter = 0;
        JList<String> list;
        String[] friends = new String[friendsList.size()];
        for (int i = 0; i < friendsList.size(); i++) {
            friends[counter] = friendsList.get(i).getName();
            counter++;
        }
        list = new JList<>(friends);
        list.setSelectedIndex(0);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);

        // incomingPanel stuff
        incomingLabel = new JLabel("Accept Friends:");
        incomingLabel.setFont(font);
        incomingPanel.add(incomingLabel, BorderLayout.NORTH);
        incomingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        int counter2 = 0;
        JList<String> list2;
        String[] incoming = new String[incomingList.size()];
        for (int i = 0; i < incomingList.size(); i++) {
            incoming[counter2] = incomingList.get(i).getName();
            counter2++;
        }
        list2 = new JList<>(incoming);
        list2.setSelectedIndex(0);

        list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list2.setLayoutOrientation(JList.VERTICAL);
        list2.setVisibleRowCount(-1);

        // Accept friend request button
        JButton acceptButton;
        if (incoming.length == 0) {
            acceptButton = new JButton("No action available");
        } else {
            acceptButton = new JButton("Accept " + incoming[0] + "?");
        }
        list2.addListSelectionListener((ListSelectionEvent event) -> {
            if (!event.getValueIsAdjusting()) {
                acceptButton.setText("Accept " + list.getSelectedValue() + "?");
            }
        });

        acceptButton.addActionListener((ActionEvent event) -> {
            String username = acceptButton.getText().split(" ")[1].substring(0, acceptButton.getText().split(" ")[1].length() - 1);
            friendsList.add(UserClient.getProfileWith(username));
            friendsFrame.setVisible(false);
            FriendsGUI.createFriendsGUI();
        });

        incomingPanel.add(acceptButton);

        // outgoingPanel stuff

        outgoingLabel = new JLabel("Pending requests:");
        outgoingLabel.setFont(font);
        incomingPanel.add(outgoingLabel, BorderLayout.NORTH);
        outgoingLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        int counter3 = 0;
        JList<String> list3;
        String[] outgoing = new String[outgoingList.size()];
        for (int i = 0; i < outgoingList.size(); i++) {
            outgoing[3] = outgoingList.get(i).getName();
            counter3++;
        }
        list3 = new JList<>(outgoing);
        list3.setSelectedIndex(0);

        list3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list3.setLayoutOrientation(JList.VERTICAL);
        list3.setVisibleRowCount(-1);

        // Deny friend request button
        JButton denyButton;
        if (outgoing.length == 0) {
            denyButton = new JButton("No action available");
        } else {
            denyButton = new JButton("Cancel request to " + incoming[0] + "?");
        }
        list3.addListSelectionListener((ListSelectionEvent event) -> {
            if (!event.getValueIsAdjusting()) {
                denyButton.setText("Cancel request to " + list.getSelectedValue() + "?");
            }
        });

        denyButton.addActionListener((ActionEvent event) -> {
            String username = denyButton.getText().split(" ")[1].substring(0, denyButton.getText().split(" ")[1].length() - 1);
            outgoingList.remove(UserClient.getProfileWith(username));
            friendsFrame.setVisible(false);
            FriendsGUI.createFriendsGUI();
        });

        outgoingPanel.add(denyButton);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener((ActionEvent event) -> {
            friendsFrame.setVisible(false);
            SocialProfileGUI.createProfileGUI();
        });

        // optionsSouthPanel stuff
        optionsSouthPanel.add(backButton);

        friendsPanel.add(list, BorderLayout.WEST);
        friendsPanel.add(list2, BorderLayout.CENTER);
        friendsPanel.add(list3, BorderLayout.EAST);
        friendsPanel.add(optionsSouthPanel, BorderLayout.SOUTH);

        friendsFrame.getContentPane().add(friendsPanel);
        friendsFrame.getContentPane().add(incomingPanel);
        friendsFrame.getContentPane().add(outgoingPanel);


        friendsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        friendsFrame.setSize(1000,1000);
        friendsFrame.setVisible(true);
    }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
}
