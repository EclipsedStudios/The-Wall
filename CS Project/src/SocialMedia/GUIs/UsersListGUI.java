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


/**
 * The users list GUI.
 * Displays when an users clicks 'Users' in Social Profile GUI, showing all users on site.
 *
 * @author Max Fuligni
 * @version 12/5/2020
 */
public class UsersListGUI extends JFrame implements ActionListener {

    public static JLabel titleLabel;

    public static List<Profile> profilesList;


    // Design layout
    /**
     * Users (size)
     * 1. Username1
     * 2. Username2
     * ..
     * ..
     * [View User?] [Back Button]
     * each one clickable which shows profile
     *
     *
     */

    /** Initalizes variables for users list gui**/
    public static void createUsersListGUI(){
        // set the profiles list to all the lists





        List<Profile> allProfiles = UserClient.profilesList;
        List<Profile> profilesToShow = new ArrayList<>();
        // getting rid of the user viewing so they can't view themself
        for (Profile p : allProfiles) {
            if (!p.getUsername().equalsIgnoreCase(UserClient.profile.getUsername())){
                profilesToShow.add(p);
            }
        }


        profilesList = profilesToShow;
        Font font = new Font("Cambria", Font.BOLD, 15);
        JFrame usersListFrame = new JFrame();
        JPanel usersListPanel = new JPanel();
        JPanel optionsSouthPanel = new JPanel();
        optionsSouthPanel.setLayout(new FlowLayout());

        //sets the layout for the social profile GUI
        usersListPanel.setLayout(new BorderLayout());

        //NORTH section of profileGUIPanel
        titleLabel = new JLabel("                                                   " +
                "                                                     Users (" + profilesList.size() + ")");
        titleLabel.setFont(font);

        usersListPanel.add(titleLabel, BorderLayout.NORTH);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT
        );

        int counter = 0;


        JList<String> list;
        String[] usernames = new String[profilesList.size()];
        for (Profile profile : profilesList) {
//            JLabel profileLink = new JLabel(counter + ". " + profile.getUsername());
//            profileLink.addMouseListener(new MouseAdapter() {
//
//                @Override
//                public void mouseClicked(MouseEvent e) {
//                    // the user clicks on the label
//                    // open user's profile
//                    usersListFrame.setVisible(false);
//                    SocialProfileGUI.createProfileGUIFor(profile.getUsername());
//                }
//
//            });


            usernames[counter] = profile.getUsername();
            counter++;

        }

        list = new JList<>(usernames);
        list.setSelectedIndex(0);

        JButton confirmView = new JButton("View " + usernames[0] + "?");
        list.addListSelectionListener((ListSelectionEvent event) -> {
            if (!event.getValueIsAdjusting()) {
                confirmView.setText("View " + list.getSelectedValue() + "?");
            }
        });

        confirmView.addActionListener((ActionEvent event) -> {
            String username = confirmView.getText().split(" ")[1].substring(0, confirmView.getText().split(" ")[1].length() - 1);
            usersListFrame.setVisible(false);
            SocialProfileGUI.createProfileGUIFor(username);
        });

        // Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener((ActionEvent event) -> {
            usersListFrame.setVisible(false);
            SocialProfileGUI.createProfileGUI();
        });

        optionsSouthPanel.add(confirmView);
        optionsSouthPanel.add(backButton);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);

        usersListPanel.add(list, BorderLayout.WEST);
        usersListPanel.add(optionsSouthPanel, BorderLayout.SOUTH);
        usersListPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        usersListFrame.getContentPane().add(usersListPanel);

        usersListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        usersListFrame.setSize(1000,1000);
        usersListFrame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
