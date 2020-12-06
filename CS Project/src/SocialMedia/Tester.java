package SocialMedia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.invoke.TypeDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.io.*;
import java.net.Socket;
import java.util.List;

import SocialMedia.GUIs.FriendsGUI;
import SocialMedia.GUIs.SocialProfileGUI;
import SocialMedia.GUIs.UsersListGUI;
import SocialMedia.ProfileExceptions.DuplicateUserException;
import SocialMedia.ProfileExceptions.InvalidPasswordException;
import SocialMedia.ServerAndClient.*;
import org.junit.Test;
import org.junit.Assert;

import javax.swing.*;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit tester class for all complicated methods that are not concurrency or GUI-based.
 *
 * @author Cole Busa
 * @version 12/5/20
 */
public class Tester {
    /**
     * A method to verify the server client thread class exists.
     */
    @Test
    public void serverClientThreadExists() {
        try {
            Class.forName("SocialMedia.ServerAndClient.ServerClientThread");
        } catch (ClassNotFoundException e) {
            Assert.fail("Need a ServerClientThread class");
        }
    }

    /**
     * A method to verify the server client thread class extends the right classes.
     */
    @Test
    public void serverClientThreadExtends() {
        assertEquals(Thread.class, ServerClientThread.class.getSuperclass());
    }

    /**
     * A method to verify the server client thread class has correctly formatted fields.
     */
    @Test
    public void serverClientThreadFields() {
        try {
            assertEquals(String.class, ServerClientThread.class.getField("line").getType());
            assertEquals(true, Modifier.isPublic(ServerClientThread.class.getField("line").getModifiers()));

            assertEquals(BufferedReader.class, ServerClientThread.class.getField("bufferedReader").getType());
            assertEquals(true, Modifier.isPublic(ServerClientThread.class.getField("bufferedReader").getModifiers()));

            assertEquals(PrintWriter.class, ServerClientThread.class.getField("printWriter").getType());
            assertEquals(true, Modifier.isPublic(ServerClientThread.class.getField("printWriter").getModifiers()));

            assertEquals(Socket.class, ServerClientThread.class.getField("socket").getType());
            assertEquals(true, Modifier.isPublic(ServerClientThread.class.getField("socket").getModifiers()));

            assertEquals(ObjectOutputStream.class, ServerClientThread.class.getField("objectOutputStream").getType());
            assertEquals(true, Modifier.isPublic(ServerClientThread.class.getField("objectOutputStream").getModifiers()));

            assertEquals(ObjectInputStream.class, ServerClientThread.class.getField("objectInputStream").getType());
            assertEquals(true, Modifier.isPublic(ServerClientThread.class.getField("objectInputStream").getModifiers()));

            assertEquals(ServerObjectStorage.class, ServerClientThread.class.getField("serverObjectStorage").getType());
            assertEquals(true, Modifier.isPublic(ServerClientThread.class.getField("serverObjectStorage").getModifiers()));

            assertEquals(Profile.class, ServerClientThread.class.getField("profile").getType());
            assertEquals(true, Modifier.isPublic(ServerClientThread.class.getField("profile").getModifiers()));
        } catch (NoSuchFieldException e) {
            Assert.fail("Missing fields");
        }
    }

    /**
     * A method to verify the server client thread class has correctly formatted methods.
     */
    @Test
    public void serverClientThreadMethods() {
        try {
            //Constructor Test
            assertEquals(true, Modifier.isPublic(ServerClientThread.class.getConstructors()[0].getModifiers()));
            Socket socket = new Socket();
            ServerObjectStorage serverObjectStorage = new ServerObjectStorage();
            Profile profile = new Profile("Steve", "username", 18, "steve@purdue.edu", "Password");
            ServerClientThread serverClientThread = new ServerClientThread(socket, serverObjectStorage, profile);
            assertEquals(socket, serverClientThread.socket);
            assertEquals(serverObjectStorage, serverClientThread.serverObjectStorage);
            assertEquals(profile, serverClientThread.profile);

            assertEquals(void.class, ServerClientThread.class.getMethod("StopThread").getReturnType());
            assertEquals(true, Modifier.isPublic(ServerClientThread.class.getMethod("StopThread").getModifiers()));

            assertEquals(void.class, ServerClientThread.class.getMethod("run").getReturnType());
            assertEquals(true, Modifier.isPublic(ServerClientThread.class.getMethod("run").getModifiers()));
        } catch (NoSuchMethodException e) {
            Assert.fail("Missing methods");
        }
    }

    /**
     * A method to verify the central server class exists.
     */
    @Test
    public void centralServerExists() {
        try {
            Class.forName("SocialMedia.ServerAndClient.CentralServer");
        } catch (ClassNotFoundException e) {
            Assert.fail("Need a CentralServer class");
        }
    }

    /**
     * A method to verify the central server class extends the right classes.
     */
    @Test
    public void centralServerExtends() {
        assertEquals(Object.class, CentralServer.class.getSuperclass());
    }

    /**
     * A method to verify the central server class has correctly formatted fields.
     */
    @Test
    public void centralServerFields() {
        try {
            assertEquals(ServerObjectStorage.class, CentralServer.class.getField("serverObjectStorage").getType());
            assertEquals(true, Modifier.isPublic(CentralServer.class.getField("serverObjectStorage").getModifiers()));
            assertEquals(true, Modifier.isStatic(CentralServer.class.getField("serverObjectStorage").getModifiers()));

            assertEquals(int.class, CentralServer.class.getField("numberOfConnections").getType());
            assertEquals(true, Modifier.isPublic(CentralServer.class.getField("numberOfConnections").getModifiers()));
            assertEquals(true, Modifier.isStatic(CentralServer.class.getField("numberOfConnections").getModifiers()));

            assertEquals(ArrayList.class, CentralServer.class.getField("serverClientThreads").getType());
            assertEquals(true, Modifier.isPublic(CentralServer.class.getField("serverClientThreads").getModifiers()));
            assertEquals(true, Modifier.isStatic(CentralServer.class.getField("serverClientThreads").getModifiers()));
        } catch (NoSuchFieldException e) {
            Assert.fail("Missing fields");
        }
    }

    /**
     * A method to verify the central server class has correctly formatted methods.
     */
    @Test
    public void centralServerMethods() {
        try {
            assertEquals(void.class, CentralServer.class.getMethod("getUsersFromDatabase").getReturnType());
            assertEquals(true, Modifier.isPublic(CentralServer.class.getMethod("getUsersFromDatabase").getModifiers()));
            assertEquals(true, Modifier.isStatic(CentralServer.class.getMethod("getUsersFromDatabase").getModifiers()));

            assertEquals(void.class, CentralServer.class.getMethod("main", String[].class).getReturnType());
            assertEquals(true, Modifier.isPublic(CentralServer.class.getMethod("main", String[].class).getModifiers()));
            assertEquals(true, Modifier.isStatic(CentralServer.class.getMethod("main", String[].class).getModifiers()));
        } catch (NoSuchMethodException e) {
            Assert.fail("Missing methods");
        }
    }

    /**
     * A method to verify the server object storage class exists.
     */
    @Test
    public void serverObjectStorageExists() {
        try {
            Class.forName("SocialMedia.ServerAndClient.ServerObjectStorage");
        } catch (ClassNotFoundException e) {
            Assert.fail("Need a ServerObjectStorage class");
        }
    }

    /**
     * A method to verify the server object storage class extends the right classes.
     */
    @Test
    public void serverObjectStorageExtends() {
        assertEquals(Object.class, ServerObjectStorage.class.getSuperclass());
    }

    /**
     * A method to verify the server object storage class has correctly formatted fields.
     */
    @Test
    public void serverObjectStorageFields() {
        try {
            assertEquals(ArrayList.class, ServerObjectStorage.class.getField("users").getType());
            assertEquals(true, Modifier.isPublic(ServerObjectStorage.class.getField("users").getModifiers()));
            assertEquals(true, Modifier.isVolatile(ServerObjectStorage.class.getField("users").getModifiers()));
        } catch (NoSuchFieldException e) {
            Assert.fail("Missing fields");
        }
    }

    /**
     * A method to verify the settings and constants class exists.
     */
    @Test
    public void settingsAndConstantsExists() {
        try {
            Class.forName("SocialMedia.ServerAndClient.SettingsAndConstants");
        } catch (ClassNotFoundException e) {
            Assert.fail("Need a SettingsAndConstants class");
        }
    }

    /**
     * A method to verify the settings and constants class extends the right classes.
     */
    @Test
    public void settingsAndConstantsExtends() {
        assertEquals(Object.class, SettingsAndConstants.class.getSuperclass());
    }

    /**
     * A method to verify the settings and constants class has correctly formatted fields.
     */
    @Test
    public void settingsAndConstantsFields() {
        try {
            assertEquals(int.class, SettingsAndConstants.class.getField("SERVER_PORT").getType());
            assertEquals(true, Modifier.isPublic(SettingsAndConstants.class.getField("SERVER_PORT").getModifiers()));
            assertEquals(true, Modifier.isStatic(SettingsAndConstants.class.getField("SERVER_PORT").getModifiers()));
            assertEquals(true, Modifier.isFinal(SettingsAndConstants.class.getField("SERVER_PORT").getModifiers()));

            assertEquals(String.class, SettingsAndConstants.class.getField("WELCOME_MESSAGE_SERVER").getType());
            assertEquals(true, Modifier.isPublic(SettingsAndConstants.class.getField("WELCOME_MESSAGE_SERVER").getModifiers()));
            assertEquals(true, Modifier.isStatic(SettingsAndConstants.class.getField("WELCOME_MESSAGE_SERVER").getModifiers()));
            assertEquals(true, Modifier.isFinal(SettingsAndConstants.class.getField("WELCOME_MESSAGE_SERVER").getModifiers()));
        } catch (NoSuchFieldException e) {
            Assert.fail("Missing fields");
        }
    }

    /**
     * A method to verify the user client class exists.
     */
    @Test
    public void userClientExists() {
        try {
            Class.forName("SocialMedia.ServerAndClient.UserClient");
        } catch (ClassNotFoundException e) {
            Assert.fail("Need a CentralServer class");
        }
    }

    /**
     * A method to verify the user client class extends the right classes.
     */
    @Test
    public void userClientExtends() {
        assertEquals(Object.class, UserClient.class.getSuperclass());
    }

    /**
     * A method to verify the user client class has correctly formatted methods.
     */
    @Test
    public void userClientMethods() {
        try {
            assertEquals(void.class, UserClient.class.getMethod("main", String[].class).getReturnType());
            assertEquals(true, Modifier.isPublic(UserClient.class.getMethod("main", String[].class).getModifiers()));
            assertEquals(true, Modifier.isStatic(UserClient.class.getMethod("main", String[].class).getModifiers()));
        } catch (NoSuchMethodException e) {
            Assert.fail("Missing methods");
        }
    }

    /**
     * A method to verify the friends list class exists.
     */
    @Test
    public void friendsListExists() {
        try {
            Class.forName("SocialMedia.FriendsList");
        } catch (ClassNotFoundException e) {
            Assert.fail("Need a FriendsList class");
        }
    }

    /**
     * A method to verify the friends list class extends the right classes.
     */
    @Test
    public void friendsListExtends() {
        assertEquals(Object.class, FriendsList.class.getSuperclass());
    }

    /**
     * A method to verify the friends list class has correctly formatted fields.
     */
    @Test
    public void friendsListFields() {
        try {
            assertEquals(ArrayList.class, FriendsList.class.getField("friends").getType());
            assertEquals(true, Modifier.isPublic(FriendsList.class.getField("friends").getModifiers()));
        } catch (NoSuchFieldException e) {
            Assert.fail("Missing fields");
        }
    }

    /**
     * A method to verify the friends list class has correctly formatted methods.
     */
    @Test
    public void friendsListMethods() {
        try {
            //Constructor Test
            assertEquals(true, Modifier.isPublic(FriendsList.class.getConstructors()[0].getModifiers()));
            ArrayList<Profile> friend1 = new ArrayList<>();
            FriendsList friendsList1 = new FriendsList();
            assertEquals(friend1, friendsList1.friends);
            assertEquals(true, Modifier.isPublic(FriendsList.class.getConstructors()[1].getModifiers()));
            Profile steve = new Profile("Steve", "username", 18, "steve@purdue.edu", "Password");
            Profile mike = new Profile("Mike", "michaelb", 21, "michaelb@purdue.edu", "Password123");
            ArrayList<Profile> friend2 = new ArrayList<>();
            friend2.add(steve);
            friend2.add(mike);
            FriendsList friendsList2 = new FriendsList(friend2);
            assertEquals(friend2, friendsList2.friends);

            assertEquals(ArrayList.class, FriendsList.class.getMethod("getFriends").getReturnType());
            assertEquals(true, Modifier.isPublic(FriendsList.class.getMethod("getFriends").getModifiers()));

            assertEquals(void.class, FriendsList.class.getMethod("addFriend", Profile.class).getReturnType());
            assertEquals(true, Modifier.isPublic(FriendsList.class.getMethod("addFriend", Profile.class).getModifiers()));

            assertEquals(int.class, FriendsList.class.getMethod("removeFriend", String.class).getReturnType());
            assertEquals(true, Modifier.isPublic(FriendsList.class.getMethod("removeFriend", String.class).getModifiers()));

            assertEquals(ArrayList.class, FriendsList.class.getMethod("mutualFriends", FriendsList.class).getReturnType());
            assertEquals(true, Modifier.isPublic(FriendsList.class.getMethod("mutualFriends", FriendsList.class).getModifiers()));

            assertEquals(String.class, FriendsList.class.getMethod("toString").getReturnType());
            assertEquals(true, Modifier.isPublic(FriendsList.class.getMethod("toString").getModifiers()));
        } catch (NoSuchMethodException e) {
            Assert.fail("Missing methods");
        }
    }

    /**
     * A method to verify the password encryption class exists.
     */
    @Test
    public void passwordEncryptionExists() {
        try {
            Class.forName("SocialMedia.PasswordEncryption");
        } catch (ClassNotFoundException e) {
            Assert.fail("Need a PasswordEncryption class");
        }
    }

    /**
     * A method to verify the password encryption class extends the right classes.
     */
    @Test
    public void passwordEncryptionExtends() {
        assertEquals(Object.class, PasswordEncryption.class.getSuperclass());
    }

    /**
     * A method to verify the password encryption class has correctly formatted methods.
     */
    @Test
    public void passwordEncryptionMethods() {
        try {
            assertEquals(String.class, PasswordEncryption.class.getMethod("encode", String.class, int.class).getReturnType());
            assertEquals(true, Modifier.isPublic(PasswordEncryption.class.getMethod("encode", String.class, int.class).getModifiers()));
            assertEquals(true, Modifier.isStatic(PasswordEncryption.class.getMethod("encode", String.class, int.class).getModifiers()));

            assertEquals(String.class, PasswordEncryption.class.getMethod("decode", String.class, int.class).getReturnType());
            assertEquals(true, Modifier.isPublic(PasswordEncryption.class.getMethod("decode", String.class, int.class).getModifiers()));
            assertEquals(true, Modifier.isStatic(PasswordEncryption.class.getMethod("decode", String.class, int.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            Assert.fail("Missing methods");
        }
    }

    /**
     * A method to verify the profile class exists.
     */
    @Test
    public void profileExists() {
        try {
            Class.forName("SocialMedia.Profile");
        } catch (ClassNotFoundException e) {
            Assert.fail("Need a Profile class");
        }
    }

    /**
     * A method to verify the profile class extends the right classes.
     */
    @Test
    public void profileExtends() {
        assertEquals(Object.class, Profile.class.getSuperclass());
        assertEquals(Serializable.class, Profile.class.getInterfaces()[0]);
    }

    /**
     * A method to verify the profile class has correctly formatted fields.
     */
    @Test
    public void profileFields() {
        try {
            assertEquals(String.class, Profile.class.getField("name").getType());
            assertEquals(true, Modifier.isPublic(Profile.class.getField("name").getModifiers()));

            assertEquals(String.class, Profile.class.getField("email").getType());
            assertEquals(true, Modifier.isPublic(Profile.class.getField("email").getModifiers()));

            assertEquals(FriendsList.class, Profile.class.getField("friendsList").getType());
            assertEquals(true, Modifier.isPublic(Profile.class.getField("friendsList").getModifiers()));

            assertEquals(String.class, Profile.class.getField("website").getType());
            assertEquals(true, Modifier.isPublic(Profile.class.getField("website").getModifiers()));

            assertEquals(List.class, Profile.class.getField("interests").getType());
            assertEquals(true, Modifier.isPublic(Profile.class.getField("interests").getModifiers()));

            assertEquals(String.class, Profile.class.getField("aboutMe").getType());
            assertEquals(true, Modifier.isPublic(Profile.class.getField("aboutMe").getModifiers()));

            assertEquals(int.class, Profile.class.getField("age").getType());
            assertEquals(true, Modifier.isPublic(Profile.class.getField("age").getModifiers()));
            assertEquals(true, Modifier.isFinal(Profile.class.getField("age").getModifiers()));

            assertEquals(String.class, Profile.class.getField("rawPassword").getType());
            assertEquals(true, Modifier.isPublic(Profile.class.getField("rawPassword").getModifiers()));

            assertEquals(ArrayList.class, Profile.class.getField("profilesList").getType());
            assertEquals(true, Modifier.isPublic(Profile.class.getField("profilesList").getModifiers()));
            assertEquals(true, Modifier.isStatic(Profile.class.getField("profilesList").getModifiers()));

            assertEquals(String.class, Profile.class.getField("username").getType());
            assertEquals(true, Modifier.isPublic(Profile.class.getField("username").getModifiers()));
            assertEquals(true, Modifier.isFinal(Profile.class.getField("username").getModifiers()));

            assertEquals(String.class, Profile.class.getField("encryptedPassword").getType());
            assertEquals(true, Modifier.isPublic(Profile.class.getField("encryptedPassword").getModifiers()));
        } catch (NoSuchFieldException e) {
            Assert.fail("Missing fields");
        }
    }

    /**
     * A method to verify the profile class has correctly formatted methods.
     */
    @Test
    public void profileMethods() {
        try {
            //Constructor Test
            assertEquals(true, Modifier.isPublic(FriendsList.class.getConstructors()[0].getModifiers()));
            Profile profile1 = new Profile("Steve", "username", 18, "steve@purdue.edu", "Password");
            String correctPassword = "Cmeeiadp";
            assertEquals("Steve", profile1.name);
            assertEquals("username", profile1.username);
            assertEquals(18, profile1.age);
            assertEquals("steve@purdue.edu", profile1.email);
            assertEquals("Password", profile1.rawPassword);
            assertEquals(correctPassword, profile1.getRawPassword());
            assertEquals(true, Modifier.isPublic(FriendsList.class.getConstructors()[1].getModifiers()));
            List<String> interests = new ArrayList<String>();
            interests.add("soccer");
            interests.add("running");
            Profile mike = new Profile("Mike", "michaelb", 21, "michaelb@purdue.edu", "Password123");
            Profile cam = new Profile("Cam", "cameron", 19, "cam@purdue.edu", "pass");
            FriendsList friendsList = new FriendsList();
            friendsList.addFriend(mike);
            friendsList.addFriend(cam);
            Profile profile2 = new Profile("Steve", 18, "steve@purdue.edu", "google.com", interests, friendsList, "I am Steve",
                    "username", "Password");
            assertEquals("Steve", profile2.name);
            assertEquals("username", profile2.username);
            assertEquals(18, profile2.age);
            assertEquals("steve@purdue.edu", profile2.email);
            assertEquals("Password", profile2.rawPassword);
            assertEquals(correctPassword, profile2.getRawPassword());
            assertEquals("google.com", profile2.website);
            assertEquals(interests, profile2.interests);
            assertEquals(friendsList, profile2.friendsList);
            assertEquals("I am Steve", profile2.aboutMe);

            assertEquals(ArrayList.class, Profile.class.getMethod("getProfilesList").getReturnType());
            assertEquals(true, Modifier.isPublic(Profile.class.getMethod("getProfilesList").getModifiers()));
            assertEquals(true, Modifier.isStatic(Profile.class.getMethod("getProfilesList").getModifiers()));

            assertEquals(Profile.class, Profile.class.getMethod("getProfileWith", String.class).getReturnType());
            assertEquals(true, Modifier.isPublic(Profile.class.getMethod("getProfileWith", String.class).getModifiers()));
            assertEquals(true, Modifier.isStatic(Profile.class.getMethod("getProfileWith", String.class).getModifiers()));

            assertEquals(FriendsList.class, Profile.class.getMethod("getFriendsList").getReturnType());
            assertEquals(true, Modifier.isPublic(Profile.class.getMethod("getFriendsList").getModifiers()));

            assertEquals(int.class, Profile.class.getMethod("getAge").getReturnType());
            assertEquals(true, Modifier.isPublic(Profile.class.getMethod("getAge").getModifiers()));

            assertEquals(String.class, Profile.class.getMethod("getUsername").getReturnType());
            assertEquals(true, Modifier.isPublic(Profile.class.getMethod("getUsername").getModifiers()));

            assertEquals(String.class, Profile.class.getMethod("getName").getReturnType());
            assertEquals(true, Modifier.isPublic(Profile.class.getMethod("getName").getModifiers()));

            assertEquals(void.class, Profile.class.getMethod("setName", String.class).getReturnType());
            assertEquals(true, Modifier.isPublic(Profile.class.getMethod("setName", String.class).getModifiers()));

            assertEquals(String.class, Profile.class.getMethod("getEmail").getReturnType());
            assertEquals(true, Modifier.isPublic(Profile.class.getMethod("getEmail").getModifiers()));

            assertEquals(void.class, Profile.class.getMethod("setEmail", String.class).getReturnType());
            assertEquals(true, Modifier.isPublic(Profile.class.getMethod("setEmail", String.class).getModifiers()));

            assertEquals(String.class, Profile.class.getMethod("getWebsite").getReturnType());
            assertEquals(true, Modifier.isPublic(Profile.class.getMethod("getWebsite").getModifiers()));

            assertEquals(void.class, Profile.class.getMethod("setWebsite", String.class).getReturnType());
            assertEquals(true, Modifier.isPublic(Profile.class.getMethod("setWebsite", String.class).getModifiers()));

            assertEquals(List.class, Profile.class.getMethod("getInterests").getReturnType());
            assertEquals(true, Modifier.isPublic(Profile.class.getMethod("getInterests").getModifiers()));

            assertEquals(void.class, Profile.class.getMethod("setInterests", List.class).getReturnType());
            assertEquals(true, Modifier.isPublic(Profile.class.getMethod("setInterests", List.class).getModifiers()));

            assertEquals(String.class, Profile.class.getMethod("getAboutMe").getReturnType());
            assertEquals(true, Modifier.isPublic(Profile.class.getMethod("getAboutMe").getModifiers()));

            assertEquals(void.class, Profile.class.getMethod("setAboutMe", String.class).getReturnType());
            assertEquals(true, Modifier.isPublic(Profile.class.getMethod("setAboutMe", String.class).getModifiers()));

            assertEquals(String.class, Profile.class.getMethod("getEncryptedPassword").getReturnType());
            assertEquals(true, Modifier.isPublic(Profile.class.getMethod("getEncryptedPassword").getModifiers()));

            assertEquals(void.class, Profile.class.getMethod("setEncryptedPassword", String.class).getReturnType());
            assertEquals(true, Modifier.isPublic(Profile.class.getMethod("setEncryptedPassword", String.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            Assert.fail("Missing methods");
        }
    }

    /**
     * A method to verify the user input class exists.
     */
    @Test
    public void userInputExists() {
        try {
            Class.forName("SocialMedia.UserInput");
        } catch (ClassNotFoundException e) {
            Assert.fail("Need a UserInput class");
        }
    }

    /**
     * A method to verify the user input class extends the right classes.
     */
    @Test
    public void userInputExtends() {
        assertEquals(JFrame.class, UserInput.class.getSuperclass());
        assertEquals(ActionListener.class, UserInput.class.getInterfaces()[0]);
    }

    /**
     * A method to verify the user input class has correctly formatted fields.
     */
    @Test
    public void userInputFields() {
        try {
            assertEquals(File.class, UserInput.class.getField("UsernamesList").getType());
            assertEquals(true, Modifier.isPublic(UserInput.class.getField("UsernamesList").getModifiers()));
            assertEquals(true, Modifier.isStatic(UserInput.class.getField("UsernamesList").getModifiers()));

            assertEquals(ArrayList.class, UserInput.class.getField("listOfUsernames").getType());
            assertEquals(true, Modifier.isPublic(UserInput.class.getField("listOfUsernames").getModifiers()));
            assertEquals(true, Modifier.isStatic(UserInput.class.getField("listOfUsernames").getModifiers()));

            assertEquals(JFrame.class, UserInput.class.getField("welcomeFrame").getType());
            assertEquals(true, Modifier.isPublic(UserInput.class.getField("welcomeFrame").getModifiers()));
            assertEquals(true, Modifier.isStatic(UserInput.class.getField("welcomeFrame").getModifiers()));

            assertEquals(Profile.class, UserInput.class.getField("currentUserProfile").getType());
            assertEquals(true, Modifier.isPublic(UserInput.class.getField("currentUserProfile").getModifiers()));
            assertEquals(true, Modifier.isStatic(UserInput.class.getField("currentUserProfile").getModifiers()));

            assertEquals(JButton.class, UserInput.class.getField("signInButton").getType());
            assertEquals(true, Modifier.isPublic(UserInput.class.getField("signInButton").getModifiers()));

            assertEquals(JTextField.class, UserInput.class.getField("username").getType());
            assertEquals(true, Modifier.isPublic(UserInput.class.getField("username").getModifiers()));
            assertEquals(true, Modifier.isStatic(UserInput.class.getField("username").getModifiers()));

            assertEquals(JPasswordField.class, UserInput.class.getField("password").getType());
            assertEquals(true, Modifier.isPublic(UserInput.class.getField("password").getModifiers()));
            assertEquals(true, Modifier.isStatic(UserInput.class.getField("password").getModifiers()));

            assertEquals(JTextField.class, UserInput.class.getField("usernameTextField").getType());
            assertEquals(true, Modifier.isPublic(UserInput.class.getField("usernameTextField").getModifiers()));
            assertEquals(true, Modifier.isStatic(UserInput.class.getField("usernameTextField").getModifiers()));

            assertEquals(JPasswordField.class, UserInput.class.getField("passwordTextField").getType());
            assertEquals(true, Modifier.isPublic(UserInput.class.getField("passwordTextField").getModifiers()));
            assertEquals(true, Modifier.isStatic(UserInput.class.getField("passwordTextField").getModifiers()));

            assertEquals(JTextField.class, UserInput.class.getField("nameTextField").getType());
            assertEquals(true, Modifier.isPublic(UserInput.class.getField("nameTextField").getModifiers()));
            assertEquals(true, Modifier.isStatic(UserInput.class.getField("nameTextField").getModifiers()));

            assertEquals(JTextField.class, UserInput.class.getField("ageTextField").getType());
            assertEquals(true, Modifier.isPublic(UserInput.class.getField("ageTextField").getModifiers()));
            assertEquals(true, Modifier.isStatic(UserInput.class.getField("ageTextField").getModifiers()));

            assertEquals(JTextField.class, UserInput.class.getField("emailTextField").getType());
            assertEquals(true, Modifier.isPublic(UserInput.class.getField("emailTextField").getModifiers()));
            assertEquals(true, Modifier.isStatic(UserInput.class.getField("emailTextField").getModifiers()));

            assertEquals(JTextField.class, UserInput.class.getField("websiteTextField").getType());
            assertEquals(true, Modifier.isPublic(UserInput.class.getField("websiteTextField").getModifiers()));
            assertEquals(true, Modifier.isStatic(UserInput.class.getField("websiteTextField").getModifiers()));

            assertEquals(JTextField.class, UserInput.class.getField("likesInterestsTextField").getType());
            assertEquals(true, Modifier.isPublic(UserInput.class.getField("likesInterestsTextField").getModifiers()));
            assertEquals(true, Modifier.isStatic(UserInput.class.getField("likesInterestsTextField").getModifiers()));

            assertEquals(JTextArea.class, UserInput.class.getField("aboutMeTextArea").getType());
            assertEquals(true, Modifier.isPublic(UserInput.class.getField("aboutMeTextArea").getModifiers()));
            assertEquals(true, Modifier.isStatic(UserInput.class.getField("aboutMeTextArea").getModifiers()));

            assertEquals(JPasswordField.class, UserInput.class.getField("confirmPasswordTextField").getType());
            assertEquals(true, Modifier.isPublic(UserInput.class.getField("confirmPasswordTextField").getModifiers()));
            assertEquals(true, Modifier.isStatic(UserInput.class.getField("confirmPasswordTextField").getModifiers()));

            assertEquals(JFrame.class, UserInput.class.getField("createAccountFrame").getType());
            assertEquals(true, Modifier.isPublic(UserInput.class.getField("createAccountFrame").getModifiers()));
            assertEquals(true, Modifier.isStatic(UserInput.class.getField("createAccountFrame").getModifiers()));

            assertEquals(String[].class, UserInput.class.getField("usernameAndPassword").getType());
            assertEquals(true, Modifier.isPublic(UserInput.class.getField("usernameAndPassword").getModifiers()));
            assertEquals(true, Modifier.isStatic(UserInput.class.getField("usernameAndPassword").getModifiers()));
        } catch (NoSuchFieldException e) {
            Assert.fail("Missing fields");
        }
    }

    /**
     * A method to verify the user input class has correctly formatted methods.
     */
    @Test
    public void userInputMethods() {
        try {
            assertEquals(void.class, UserInput.class.getMethod("main", String[].class).getReturnType());
            assertEquals(true, Modifier.isPublic(UserInput.class.getMethod("main", String[].class).getModifiers()));
            assertEquals(true, Modifier.isStatic(UserInput.class.getMethod("main", String[].class).getModifiers()));

            assertEquals(void.class, UserInput.class.getMethod("createWelcomeScreen").getReturnType());
            assertEquals(true, Modifier.isPublic(UserInput.class.getMethod("createWelcomeScreen").getModifiers()));
            assertEquals(true, Modifier.isStatic(UserInput.class.getMethod("createWelcomeScreen").getModifiers()));

            assertEquals(void.class, UserInput.class.getMethod("validateLoginCredentials").getReturnType());
            assertEquals(true, Modifier.isPublic(UserInput.class.getMethod("validateLoginCredentials").getModifiers()));
            assertEquals(true, Modifier.isStatic(UserInput.class.getMethod("validateLoginCredentials").getModifiers()));
            assertEquals(IOException.class, UserInput.class.getMethod("validateLoginCredentials").getExceptionTypes()[0]);

            assertEquals(void.class, UserInput.class.getMethod("createAccount").getReturnType());
            assertEquals(true, Modifier.isPublic(UserInput.class.getMethod("createAccount").getModifiers()));
            assertEquals(true, Modifier.isStatic(UserInput.class.getMethod("createAccount").getModifiers()));

            assertEquals(int.class, UserInput.class.getMethod("performCreateAccountConfirmButtonAction").getReturnType());
            assertEquals(true, Modifier.isPublic(UserInput.class.getMethod("performCreateAccountConfirmButtonAction").getModifiers()));
            assertEquals(true, Modifier.isStatic(UserInput.class.getMethod("performCreateAccountConfirmButtonAction").getModifiers()));

            assertEquals(String[].class, UserInput.class.getMethod("getUsernameAndPassword").getReturnType());
            assertEquals(true, Modifier.isPublic(UserInput.class.getMethod("getUsernameAndPassword").getModifiers()));
            assertEquals(true, Modifier.isStatic(UserInput.class.getMethod("getUsernameAndPassword").getModifiers()));

            assertEquals(void.class, UserInput.class.getMethod("makeLoginScreenVisible").getReturnType());
            assertEquals(true, Modifier.isPublic(UserInput.class.getMethod("makeLoginScreenVisible").getModifiers()));
            assertEquals(true, Modifier.isStatic(UserInput.class.getMethod("makeLoginScreenVisible").getModifiers()));

            assertEquals(void.class, UserInput.class.getMethod("createUserProfile").getReturnType());
            assertEquals(true, Modifier.isPublic(UserInput.class.getMethod("createUserProfile").getModifiers()));
            assertEquals(true, Modifier.isStatic(UserInput.class.getMethod("createUserProfile").getModifiers()));
            assertEquals(IOException.class, UserInput.class.getMethod("createUserProfile").getExceptionTypes()[0]);

            assertEquals(void.class, UserInput.class.getMethod("actionPerformed", ActionEvent.class).getReturnType());
            assertEquals(true, Modifier.isPublic(UserInput.class.getMethod("actionPerformed", ActionEvent.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            Assert.fail("Missing methods");
        }
    }

    /**
     * A method to verify the duplicate user exception class exists.
     */
    @Test
    public void duplicateUserExceptionExists() {
        try {
            Class.forName("SocialMedia.ProfileExceptions.DuplicateUserException");
        } catch (ClassNotFoundException e) {
            Assert.fail("Need a DuplicateUserException class");
        }
    }

    /**
     * A method to verify the duplicate user exception class extends the right classes.
     */
    @Test
    public void duplicateUserExceptionExtends() {
        assertEquals(Exception.class, DuplicateUserException.class.getSuperclass());
    }

    /**
     * A method to verify the invalid password exception class exists.
     */
    @Test
    public void invalidPasswordExceptionExists() {
        try {
            Class.forName("SocialMedia.ProfileExceptions.InvalidPasswordException");
        } catch (ClassNotFoundException e) {
            Assert.fail("Need a InvalidPasswordException class");
        }
    }

    /**
     * A method to verify the invalid password exception class extends the right classes.
     */
    @Test
    public void invalidPasswordExceptionExtends() {
        assertEquals(Exception.class, InvalidPasswordException.class.getSuperclass());
    }

    /**
     * A method to verify the friends GUI class exists.
     */
    @Test
    public void friendsGUIExists() {
        try {
            Class.forName("SocialMedia.GUIs.FriendsGUI");
        } catch (ClassNotFoundException e) {
            Assert.fail("Need a FriendsGUI class");
        }
    }

    /**
     * A method to verify the friends GUI class extends the right classes.
     */
    @Test
    public void friendsGUIExtends() {
        assertEquals(JFrame.class, FriendsGUI.class.getSuperclass());
        assertEquals(ActionListener.class, FriendsGUI.class.getInterfaces()[0]);
    }

    /**
     * A method to verify the friends GUI class has correctly formatted fields.
     */
    @Test
    public void friendsGUIFields() {
        try {
            assertEquals(List.class, FriendsGUI.class.getField("friendsList").getType());
            assertEquals(true, Modifier.isPublic(FriendsGUI.class.getField("friendsList").getModifiers()));
            assertEquals(true, Modifier.isStatic(FriendsGUI.class.getField("friendsList").getModifiers()));

            assertEquals(JLabel.class, FriendsGUI.class.getField("titleLabel").getType());
            assertEquals(true, Modifier.isPublic(FriendsGUI.class.getField("titleLabel").getModifiers()));
            assertEquals(true, Modifier.isStatic(FriendsGUI.class.getField("titleLabel").getModifiers()));
        } catch (NoSuchFieldException e) {
            Assert.fail("Missing fields");
        }
    }

    /**
     * A method to verify the friends GUI class has correctly formatted methods.
     */
    @Test
    public void friendsGUIMethods() {
        try {
            assertEquals(void.class, FriendsGUI.class.getMethod("createFriendsGUI").getReturnType());
            assertEquals(true, Modifier.isPublic(FriendsGUI.class.getMethod("createFriendsGUI").getModifiers()));
            assertEquals(true, Modifier.isStatic(FriendsGUI.class.getMethod("createFriendsGUI").getModifiers()));
        } catch (NoSuchMethodException e) {
            Assert.fail("Missing methods");
        }
    }

    /**
     * A method to verify the social profile GUI class exists.
     */
    @Test
    public void socialProfileGUIExists() {
        try {
            Class.forName("SocialMedia.GUIs.SocialProfileGUI");
        } catch (ClassNotFoundException e) {
            Assert.fail("Need a SocialProfileGUI class");
        }
    }

    /**
     * A method to verify the social profile GUI class extends the right classes.
     */
    @Test
    public void socialProfileGUIExtends() {
        assertEquals(JFrame.class, SocialProfileGUI.class.getSuperclass());
        assertEquals(ActionListener.class, SocialProfileGUI.class.getInterfaces()[0]);
    }

    /**
     * A method to verify the social profile GUI class has correctly formatted fields.
     */
    @Test
    public void socialProfileGUIFields() {
        try {
            assertEquals(JFrame.class, SocialProfileGUI.class.getField("listOfUsersFrame").getType());
            assertEquals(true, Modifier.isPublic(SocialProfileGUI.class.getField("listOfUsersFrame").getModifiers()));
            assertEquals(true, Modifier.isStatic(SocialProfileGUI.class.getField("listOfUsersFrame").getModifiers()));

            assertEquals(Profile.class, SocialProfileGUI.class.getField("GUIProfile").getType());
            assertEquals(true, Modifier.isPublic(SocialProfileGUI.class.getField("GUIProfile").getModifiers()));
            assertEquals(true, Modifier.isStatic(SocialProfileGUI.class.getField("GUIProfile").getModifiers()));

            assertEquals(JLabel.class, SocialProfileGUI.class.getField("titleLabel").getType());
            assertEquals(true, Modifier.isPublic(SocialProfileGUI.class.getField("titleLabel").getModifiers()));
            assertEquals(true, Modifier.isStatic(SocialProfileGUI.class.getField("titleLabel").getModifiers()));

            assertEquals(JButton.class, SocialProfileGUI.class.getField("usersButton").getType());
            assertEquals(true, Modifier.isPublic(SocialProfileGUI.class.getField("usersButton").getModifiers()));
            assertEquals(true, Modifier.isStatic(SocialProfileGUI.class.getField("usersButton").getModifiers()));

            assertEquals(JButton.class, SocialProfileGUI.class.getField("myProfileButton").getType());
            assertEquals(true, Modifier.isPublic(SocialProfileGUI.class.getField("myProfileButton").getModifiers()));
            assertEquals(true, Modifier.isStatic(SocialProfileGUI.class.getField("myProfileButton").getModifiers()));

            assertEquals(JButton.class, SocialProfileGUI.class.getField("friendsListButton").getType());
            assertEquals(true, Modifier.isPublic(SocialProfileGUI.class.getField("friendsListButton").getModifiers()));
            assertEquals(true, Modifier.isStatic(SocialProfileGUI.class.getField("friendsListButton").getModifiers()));

            assertEquals(JButton.class, SocialProfileGUI.class.getField("logoutButton").getType());
            assertEquals(true, Modifier.isPublic(SocialProfileGUI.class.getField("logoutButton").getModifiers()));
            assertEquals(true, Modifier.isStatic(SocialProfileGUI.class.getField("logoutButton").getModifiers()));

            assertEquals(JLabel.class, SocialProfileGUI.class.getField("nameLabel").getType());
            assertEquals(true, Modifier.isPublic(SocialProfileGUI.class.getField("nameLabel").getModifiers()));
            assertEquals(true, Modifier.isStatic(SocialProfileGUI.class.getField("nameLabel").getModifiers()));

            assertEquals(JLabel.class, SocialProfileGUI.class.getField("usernameLabel").getType());
            assertEquals(true, Modifier.isPublic(SocialProfileGUI.class.getField("usernameLabel").getModifiers()));
            assertEquals(true, Modifier.isStatic(SocialProfileGUI.class.getField("usernameLabel").getModifiers()));

            assertEquals(JLabel.class, SocialProfileGUI.class.getField("ageLabel").getType());
            assertEquals(true, Modifier.isPublic(SocialProfileGUI.class.getField("ageLabel").getModifiers()));
            assertEquals(true, Modifier.isStatic(SocialProfileGUI.class.getField("ageLabel").getModifiers()));

            assertEquals(JLabel.class, SocialProfileGUI.class.getField("emailLabel").getType());
            assertEquals(true, Modifier.isPublic(SocialProfileGUI.class.getField("emailLabel").getModifiers()));
            assertEquals(true, Modifier.isStatic(SocialProfileGUI.class.getField("emailLabel").getModifiers()));

            assertEquals(JLabel.class, SocialProfileGUI.class.getField("websiteLabel").getType());
            assertEquals(true, Modifier.isPublic(SocialProfileGUI.class.getField("websiteLabel").getModifiers()));
            assertEquals(true, Modifier.isStatic(SocialProfileGUI.class.getField("websiteLabel").getModifiers()));

            assertEquals(JLabel.class, SocialProfileGUI.class.getField("likesInterestsLabel").getType());
            assertEquals(true, Modifier.isPublic(SocialProfileGUI.class.getField("likesInterestsLabel").getModifiers()));
            assertEquals(true, Modifier.isStatic(SocialProfileGUI.class.getField("likesInterestsLabel").getModifiers()));

            assertEquals(JLabel.class, SocialProfileGUI.class.getField("friendsLabel").getType());
            assertEquals(true, Modifier.isPublic(SocialProfileGUI.class.getField("friendsLabel").getModifiers()));
            assertEquals(true, Modifier.isStatic(SocialProfileGUI.class.getField("friendsLabel").getModifiers()));

            assertEquals(JLabel.class, SocialProfileGUI.class.getField("aboutMeLabel").getType());
            assertEquals(true, Modifier.isPublic(SocialProfileGUI.class.getField("aboutMeLabel").getModifiers()));
            assertEquals(true, Modifier.isStatic(SocialProfileGUI.class.getField("aboutMeLabel").getModifiers()));

            assertEquals(JLabel.class, SocialProfileGUI.class.getField("aboutMeText").getType());
            assertEquals(true, Modifier.isPublic(SocialProfileGUI.class.getField("aboutMeText").getModifiers()));
            assertEquals(true, Modifier.isStatic(SocialProfileGUI.class.getField("aboutMeText").getModifiers()));
        } catch (NoSuchFieldException e) {
            Assert.fail("Missing fields");
        }
    }

    /**
     * A method to verify the social profile GUI class has correctly formatted methods.
     */
    @Test
    public void socialProfileGUIMethods() {
        try {
            assertEquals(void.class, SocialProfileGUI.class.getMethod("createProfileGUI").getReturnType());
            assertEquals(true, Modifier.isPublic(SocialProfileGUI.class.getMethod("createProfileGUI").getModifiers()));
            assertEquals(true, Modifier.isStatic(SocialProfileGUI.class.getMethod("createProfileGUI").getModifiers()));

            assertEquals(void.class, SocialProfileGUI.class.getMethod("createProfileGUIFor", String.class).getReturnType());
            assertEquals(true, Modifier.isPublic(SocialProfileGUI.class.getMethod("createProfileGUIFor", String.class).getModifiers()));
            assertEquals(true, Modifier.isStatic(SocialProfileGUI.class.getMethod("createProfileGUIFor", String.class).getModifiers()));

            assertEquals(void.class, SocialProfileGUI.class.getMethod("createProfileGUIFor", String.class).getReturnType());
            assertEquals(true, Modifier.isPublic(SocialProfileGUI.class.getMethod("createProfileGUIFor", String.class).getModifiers()));
            assertEquals(true, Modifier.isStatic(SocialProfileGUI.class.getMethod("createProfileGUIFor", String.class).getModifiers()));

            assertEquals(void.class, SocialProfileGUI.class.getMethod("actionPerformed", ActionEvent.class).getReturnType());
            assertEquals(true, Modifier.isPublic(SocialProfileGUI.class.getMethod("actionPerformed", ActionEvent.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            Assert.fail("Missing methods");
        }
    }

    /**
     * A method to verify the users list GUI class exists.
     */
    @Test
    public void usersListGUIExists() {
        try {
            Class.forName("SocialMedia.GUIs.UsersListGUI");
        } catch (ClassNotFoundException e) {
            Assert.fail("Need a UsersListGUI class");
        }
    }

    /**
     * A method to verify the users list GUI class extends the right classes.
     */
    @Test
    public void usersListGUIExtends() {
        assertEquals(JFrame.class, UsersListGUI.class.getSuperclass());
        assertEquals(ActionListener.class, UsersListGUI.class.getInterfaces()[0]);
    }

    /**
     * A method to verify the users list GUI class has correctly formatted fields.
     */
    @Test
    public void usersListGUIFields() {
        try {
            assertEquals(JLabel.class, UsersListGUI.class.getField("titleLabel").getType());
            assertEquals(true, Modifier.isPublic(UsersListGUI.class.getField("titleLabel").getModifiers()));
            assertEquals(true, Modifier.isStatic(UsersListGUI.class.getField("titleLabel").getModifiers()));

            assertEquals(List.class, UsersListGUI.class.getField("profilesList").getType());
            assertEquals(true, Modifier.isPublic(UsersListGUI.class.getField("profilesList").getModifiers()));
            assertEquals(true, Modifier.isStatic(UsersListGUI.class.getField("profilesList").getModifiers()));
        } catch (NoSuchFieldException e) {
            Assert.fail("Missing fields");
        }
    }

    /**
     * A method to verify the users list GUI class has correctly formatted methods.
     */
    @Test
    public void usersListGUIMethods() {
        try {
            assertEquals(void.class, UsersListGUI.class.getMethod("createUsersListGUI").getReturnType());
            assertEquals(true, Modifier.isPublic(UsersListGUI.class.getMethod("createUsersListGUI").getModifiers()));
            assertEquals(true, Modifier.isStatic(UsersListGUI.class.getMethod("createUsersListGUI").getModifiers()));

            assertEquals(void.class, UsersListGUI.class.getMethod("actionPerformed", ActionEvent.class).getReturnType());
            assertEquals(true, Modifier.isPublic(UsersListGUI.class.getMethod("actionPerformed", ActionEvent.class).getModifiers()));
        } catch (NoSuchMethodException e) {
            Assert.fail("Missing methods");
        }
    }

    /**
     * A tester method for the entire friends list class.
     */
    @Test
    public void testFriendsListClass() {
        //test mutual friend method
        ArrayList<Profile> friend1 = new ArrayList<Profile>();
        ArrayList<Profile> friend2 = new ArrayList<Profile>();
        Profile steve = new Profile("Steve", "username", 18, "steve@purdue.edu", "Password");
        Profile mike = new Profile("Mike", "michaelb", 21, "michaelb@purdue.edu", "Password123");
        Profile cam = new Profile("Cam", "cameron", 19, "cam@purdue.edu", "pass");
        Profile emily = new Profile("Emily", "emilyf", 20, "emilyf@purdue.edu", "notpassword");
        friend1.add(steve);
        friend1.add(mike);
        friend1.add(emily);
        friend2.add(mike);
        friend2.add(cam);
        friend2.add(steve);
        FriendsList f1 = new FriendsList(friend1);
        FriendsList f2 = new FriendsList(friend2);
        ArrayList<Profile> correctMutualFriends = new ArrayList<Profile>();
        correctMutualFriends.add(steve);
        correctMutualFriends.add(mike);
        assertEquals(correctMutualFriends, f1.mutualFriends(f2));
    }
}
