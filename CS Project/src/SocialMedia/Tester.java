package SocialMedia;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * A JUnit tester class for all complicated methods that are not concurrency or GUI-based.
 *
 * @author Cole Busa
 * @version 12/5/20
 */
public class Tester {
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
