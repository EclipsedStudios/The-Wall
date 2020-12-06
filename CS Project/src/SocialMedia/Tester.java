package SocialMedia;


import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.io.*;
import java.net.Socket;
import java.util.List;

import SocialMedia.ServerAndClient.*;
import org.junit.Test;
import org.junit.Assert;
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
     * A tester method for the entire profile class.
     */
    @Test
    public void testProfileClass() {
        //test password encryption
        Profile testProfile = new Profile("Steve", "username", 18, "steve@purdue.edu", "Password");
        String correctPassword = "Cmeeiadp";
        assertEquals(correctPassword, testProfile.getEncryptedPassword());
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
