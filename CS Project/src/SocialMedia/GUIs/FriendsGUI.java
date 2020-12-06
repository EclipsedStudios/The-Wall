package SocialMedia.GUIs;

import SocialMedia.Profile;

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

    private static List<Profile> friendsList;
    private static JLabel titleLabel;

    public static void createFriendsGUI() {

        // Testing out the GUI with dummy friends list
        friendsList = new ArrayList<>();
        friendsList.add(new Profile("John Jim", 12, "jonjim@gmail.com", "johnjim.com", (List<String>) Arrays.asList("Fishing", "Hiking"),
                null, "Insert about me", "johnjim", "test123"));
        friendsList.add(new Profile("JackBlack", 16, "jonjim@gmail.com", "johnjim.com", (List<String>) Arrays.asList("Fishing", "Hiking"),
                null, "Insert about me", "JackBlack", "test123"));

        Font font = new Font("Cambria", Font.BOLD, 15);
        JFrame friendsFrame = new JFrame();
        JPanel friendsPanel = new JPanel();
        friendsPanel.setLayout(new BorderLayout());

        // Setting dimensions and alignments
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
