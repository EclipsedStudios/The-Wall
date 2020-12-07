package SocialMedia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that constructs and stores profiles of users.
 *
 * @version 12/4/2020
 * @authors Max Fuligni, Paul Gherghetta, Cole Busa, Aakash Jariwala, Jaden Baker
 */

/**
 * 1. Creating accounts - Jaden
 * 2. Friends list GUI - Aakash
 * 3. Send / accept friend requests -  Max
 * 4. Edit profiles - Paul
 *
 */
public class Profile implements Serializable {

    public static ArrayList<Profile> profilesList = new ArrayList<>();
    public final int age;
    /**
     * identifier
     **/
    public final String username;
    public String name;
    public String email;
    public FriendsList friendsList;
    public String website;
    public ArrayList<String> interests;
    public String aboutMe;
    public String rawPassword;


    // Initial construction
    public Profile(String name, String username, int age, String email, String rawPassword) {
        this.name = name;
        this.username = username;
        this.email = email;

        //Current skip is 12 (Moves each letter over by 12). This can be changed later.
        this.rawPassword = rawPassword;
        this.age = age;
    }

    // If user decides to input all fields on construction
    public Profile(String name, int age, String email, String website, List<String> interests,
                   FriendsList friendsList, String aboutMe, String username, String rawPassword) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.website = website;
        this.interests = new ArrayList<>(interests);
        this.friendsList = friendsList;
        this.aboutMe = aboutMe;
        this.username = username;
        this.rawPassword = rawPassword;
    }

    public Profile(String name, int age, String email, String website, ArrayList<String> interests,
                   FriendsList friendsList, String aboutMe, String username, String rawPassword) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.website = website;
        this.interests = interests;
        this.friendsList = friendsList;
        this.aboutMe = aboutMe;
        this.username = username;
        this.rawPassword = rawPassword;
    }

    // Getters and Setters
    public static ArrayList<Profile> getProfilesList() {
        return profilesList;
    }

    // Gets profile with desired username, returns null if doesn't exist
    public static Profile getProfileWith(String username) {
        for (Profile profile : getProfilesList()) {
            if (profile.getUsername().equalsIgnoreCase(username)) {
                return profile;
            }
        }
        return null;

    }

    public static void setProfilesList(ArrayList<Profile> profiles) {
        profilesList = profiles;
    }

    public FriendsList getFriendsList() {
        return friendsList;
    }

    public int getAge() {
        return age;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public ArrayList<String> getInterests() {
        return interests;
    }

    public void setInterests(ArrayList<String> interests) {
        this.interests = interests;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public void setRawPassword(String password) {
        this.rawPassword = password;
    }

}
