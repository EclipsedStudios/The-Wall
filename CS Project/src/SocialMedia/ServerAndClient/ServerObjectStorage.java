package SocialMedia.ServerAndClient;

import SocialMedia.Profile;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
/**
 * A class to store the users
 *
 * @author Jaden Baker
 * @version 12/7/20
 */
public class ServerObjectStorage {
    public volatile ArrayList<Profile> users = new ArrayList<>();

    public void saveUsersToDatabase() {
        File dir = new File("UsernameFiles");
        File[] directoryListing = dir.listFiles();
        assert directoryListing != null;
        for(File file : directoryListing)
            if (!file.isDirectory())
                file.delete();

        if (directoryListing != null) {
            for (Profile p : users) {
                File file = new File("UsernameFiles/" + p.getUsername() + ".txt");
                FileOutputStream fOS = null;
                try {
                    fOS = new FileOutputStream(file, false);
                } catch (FileNotFoundException fileNotFoundException) {
                    JOptionPane.showMessageDialog(null,
                            "File Not Found Exception thrown!", "Social Profile App",
                            JOptionPane.ERROR_MESSAGE);
                }
                assert fOS != null;
                PrintWriter pw = new PrintWriter(fOS);

                if (p.getName() != null)
                    pw.println(p.getName());
                if (p.getEmail() != null)
                    pw.println(p.getEmail());

                pw.println(p.getFriendsList().toString());

                if (p.getWebsite() != null)
                    pw.println(p.getWebsite());
                else
                    pw.println("No Website");

                if (p.getInterests() != null)
                    pw.println(p.getInterests().toString());
                else
                    pw.println("[]");

                if (p.getAboutMe() != null)
                    pw.println(p.getAboutMe());
                else
                    pw.println("I haven't provided any information about myself");

                pw.println(p.getAge());

                if (p.getRawPassword() != null)
                    pw.println(p.getRawPassword());

                pw.close();
            }
        }
        System.out.println("Finished with save");
    }
}
