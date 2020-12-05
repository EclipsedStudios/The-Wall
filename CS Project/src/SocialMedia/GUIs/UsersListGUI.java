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
 * The users list GUI.
 * Displays when an users clicks 'Users' in Social Profile GUI, showing all users on site.
 *
 * @author Max Fuligni
 * @version 12/5/2020
 */
public class UsersListGUI extends JFrame implements ActionListener {

    private static JLabel titleLabel;

    private static List<Profile> profilesList;


    // Design layout
    /**
     * Users (size)
     * 1. Username1
     * 2. Username2
     * ..
     * ..
     * [Back Button]
     * each one clickable which shows profile
     */

    /** Initalizes variables for users list gui**/
    public static void createUsersListGUI(){
        // set the profiles list to all the lists
        profilesList = new ArrayList<>();
        // for now I'm using a dummy list w/ fake profiles just to test
        profilesList.add(new Profile("John Jim", 12, "jonjim@gmail.com", "johnhim.com", (List<String>) Arrays.asList("Fishing", "Hiking"),
                        null, "Insert about me", "johnjim", "test123"));

        profilesList.add(new Profile("JackBlack", 16, "jonjim@gmail.com", "johnhim.com", (List<String>) Arrays.asList("Fishing", "Hiking"),
                null, "Insert about me", "JackBlack", "test123"));


        Font font = new Font("Cambria", Font.BOLD, 15);
        JFrame usersListFrame = new JFrame();
        JPanel usersListPanel = new JPanel();
        //sets the layout for the social profile GUI
        usersListPanel.setLayout(new BorderLayout());

        //NORTH section of profileGUIPanel
        titleLabel = new JLabel("                                                   " +
                "                                                     Users (" + profilesList.size() + ")");
        titleLabel.setFont(font);

        usersListPanel.add(titleLabel, BorderLayout.NORTH);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        usersListFrame.getContentPane().add(usersListPanel);

        usersListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        usersListFrame.setSize(1000,1000);
        usersListFrame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
