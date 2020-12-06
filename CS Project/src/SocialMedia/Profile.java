package SocialMedia;

import SocialMedia.FriendsList;
import SocialMedia.PasswordEncryption;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that constructs and stores profiles of users.
 * @version 12/4/2020
 * @authors Max Fuligni, Paul Gherghetta, Cole Busa, Aakash Jariwala, Jaden Baker
 */
public class Profile implements Serializable {

    public String name;
    public String email;
    public FriendsList friendsList;
    public String website;
    public ArrayList<String> interests;
    public String aboutMe;
    public final int age;
    public String rawPassword;
    public static ArrayList<Profile> profilesList = new ArrayList<>();

    /**
     * identifier
     **/
    public final String username;


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
    public static Profile getProfileWith(String username){
        for (Profile profile : getProfilesList()) {
            if (profile.getUsername().equalsIgnoreCase(username)) {
                return profile;
            }
        }
        return null;

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