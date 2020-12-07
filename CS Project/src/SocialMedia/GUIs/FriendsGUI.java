package SocialMedia.GUIs;

import SocialMedia.FriendsList;
import SocialMedia.Profile;
import SocialMedia.ServerAndClient.UserClient;
import SocialMedia.UserInput;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Friends GUI.
 * Displays the user's friends and allows friend requests to be accepted or denied
 *
 * @author Aakash Jariwala
 * @version 12/5/2020
 *
 * (West)               (Center             (East)
 * Friends list      Incoming list          Outgoing List
 * - friend1           -friend 2            -friend3
 * -
 * -
 *                  [Accept friend] [Deny]        [Cancel outgoing req]
 */


public class FriendsGUI extends JFrame implements ActionListener {

    public static List<Profile> friendsList;
    public static List<Profile> incomingList;
    public static List<Profile> outgoingList;
    public static JLabel titleLabel;
    public static JLabel incomingLabel;
    public static JLabel outgoingLabel;

    public static void createFriendsGUI() {

        // ------------- Initalizing lists --------------//
        friendsList = UserClient.profile.getFriendsList().friends;
        incomingList = UserClient.profile.friendsList.getIncomingFriendRequests();
        outgoingList = UserClient.profile.friendsList.getOutgoingFriendRequests();

        Font font = new Font("Cambria", Font.BOLD, 15);

        // ------------- Initalizing frame and panels --------------//

        JFrame friendsFrame = new JFrame("Friends Menu");

        JPanel friendsPanel = new JPanel(); // main panel

        JPanel rightFriendsPanel = new JPanel(); //shows friends panel
        JPanel incomingPanel = new JPanel(); // middle panel, shows incoming req
        JPanel outgoingPanel = new JPanel(); // far right, shows outgoing req



        rightFriendsPanel.setLayout(new BorderLayout());
        friendsPanel.setLayout(new BorderLayout());
        incomingPanel.setLayout(new BorderLayout());
        outgoingPanel.setLayout(new BorderLayout());

        JPanel optionsSouthPanel = new JPanel();
        optionsSouthPanel.setLayout(new FlowLayout());

        // ------------- Initalizing title label --------------//

        // friendsPanel stuff
        titleLabel = new JLabel("Friends: (" + friendsList.size() + ")");
        titleLabel.setFont(font);
        friendsPanel.add(titleLabel, BorderLayout.NORTH);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);


        // ------------- Populating Jlists and respective labels --------------//

        int counter = 0;
        JList<String> list;
        String[] friends = new String[friendsList.size()];
        for (int i = 0; i < friendsList.size(); i++) {
            friends[counter] = friendsList.get(i).getUsername();
            counter++;
        }
        list = new JList<>(friends);
        list.setSelectedIndex(0);

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);

        rightFriendsPanel.add(list);

        // incomingPanel stuff
        incomingLabel = new JLabel("Accept Friends:");
        incomingLabel.setFont(font);
        incomingPanel.add(incomingLabel, BorderLayout.NORTH);
        incomingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        int counter2 = 0;
        JList<String> list2;
        String[] incoming = new String[incomingList.size()];
        for (int i = 0; i < incomingList.size(); i++) {
            incoming[counter2] = incomingList.get(i).getUsername();
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
            acceptButton = new JButton("You have no friend requests to accept");
        } else {
            acceptButton = new JButton("Accept " + incoming[0] + "?");
        }
        list2.addListSelectionListener((ListSelectionEvent event) -> {
            if (!event.getValueIsAdjusting()) {
                acceptButton.setText("Accept " + list.getSelectedValue() + "?");
            }
        });

        acceptButton.addActionListener((ActionEvent event) -> {
            Profile currentUser = UserClient.profile;
            try {
                UserInput.userClient.refreshPage();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // check if user still has incoming req since may be cancelled
            String username = acceptButton.getText().split(" ")[1].substring(0, acceptButton.getText().split(" ")[1].length() - 1);
            Profile userToAdd = UserClient.getProfileWith(username);
            if (currentUser.getFriendsList().hasIncomingFriendRequest(userToAdd)) {

                UserClient.profile.getFriendsList().addFriend(userToAdd);
                userToAdd.getFriendsList().addFriend(UserClient.profile);


                try {
                    UserInput.userClient.acceptFriend(userToAdd);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "That person has cancelled their friend request to you " +
                        "\n or you have no incoming friend requests!", "Social Media Profile App", JOptionPane.ERROR_MESSAGE);
            }

            friendsFrame.setVisible(false);
            FriendsGUI.createFriendsGUI();
        });

        optionsSouthPanel.add(acceptButton);
        incomingPanel.add(list2);

        // Accept friend request button
        JButton denyIncomingButton;
        if (incoming.length == 0) {
            denyIncomingButton = new JButton("You have no friend requests to deny");
        } else {
            denyIncomingButton = new JButton("Deny " + incoming[0] + "?");
        }
        list2.addListSelectionListener((ListSelectionEvent event) -> {
            if (!event.getValueIsAdjusting()) {
                denyIncomingButton.setText("Accept " + list.getSelectedValue() + "?");
            }
        });

        denyIncomingButton.addActionListener((ActionEvent event) -> {
            Profile currentUser = UserClient.profile;
            try {
                UserInput.userClient.refreshPage();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // check if user still has incoming req since may be cancelled
            String username = denyIncomingButton.getText().split(" ")[1].substring(0, acceptButton.getText().split(" ")[1].length() - 1);
            Profile userToAdd = UserClient.getProfileWith(username);
            if (currentUser.getFriendsList().hasIncomingFriendRequest(userToAdd)) {

                try {
                    UserInput.userClient.cancelFriendRequest(userToAdd);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "That person has cancelled their friend request to you " +
                        "\n or you have no incoming friend requests!", "Social Media Profile App", JOptionPane.ERROR_MESSAGE);
            }
            friendsFrame.setVisible(false);
            FriendsGUI.createFriendsGUI();
            friendsFrame.setVisible(false);
            FriendsGUI.createFriendsGUI();
        });

        optionsSouthPanel.add(denyIncomingButton);


        // outgoingPanel stuff

        outgoingLabel = new JLabel("Pending requests:");
        outgoingLabel.setFont(font);
        outgoingPanel.add(outgoingLabel, BorderLayout.NORTH);
        outgoingLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        int counter3 = 0;
        JList<String> list3;
        String[] outgoing = new String[outgoingList.size()];
        for (int i = 0; i < outgoingList.size(); i++) {
            outgoing[counter3] = outgoingList.get(i).getUsername();
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
            denyButton = new JButton("You have sent no friend requests");
        } else {
            denyButton = new JButton("Cancel request to " + outgoing[0] + "?");
        }
        list3.addListSelectionListener((ListSelectionEvent event) -> {
            if (!event.getValueIsAdjusting()) {
                denyButton.setText("Cancel request to " + list.getSelectedValue() + "?");
            }
        });

        denyButton.addActionListener((ActionEvent event) -> {
            String username = denyButton.getText().split(" ")[3].substring(0, denyButton.getText().split(" ")[3].length() - 1);
            Profile user = UserClient.getProfileWith(username);
            UserClient.profile.getFriendsList().removeOutgoingFriendRequest(user);
            user.getFriendsList().removeIncomingFriendRequest(UserClient.profile);



            try {
                UserInput.userClient.cancelFriendRequest(user);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            friendsFrame.setVisible(false);
            FriendsGUI.createFriendsGUI();
        });

        optionsSouthPanel.add(denyButton);
        outgoingPanel.add(list3);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener((ActionEvent event) -> {
            friendsFrame.setVisible(false);
            SocialProfileGUI.createProfileGUI();
        });

        // optionsSouthPanel stuff
        optionsSouthPanel.add(backButton);

        friendsPanel.add(rightFriendsPanel, BorderLayout.WEST);
        friendsPanel.add(incomingPanel, BorderLayout.CENTER);
        friendsPanel.add(outgoingPanel, BorderLayout.EAST);
        friendsFrame.add(optionsSouthPanel, BorderLayout.SOUTH);

        friendsFrame.getContentPane().add(friendsPanel);


        friendsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        friendsFrame.setSize(1000,1000);
        friendsFrame.setVisible(true);
    }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
}
