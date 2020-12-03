package SocialMedia;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * A JUnit tester class for all methods that are not concurrency or GUI-based.
 *
 * @author Cole Busa
 * @version 12/3/20
 */
public class Tester {
    Profile testProfile = new Profile("Steve", "username", 18, "steve@purdue.edu", "Password");

    /**
     * First test method to understand JUnit
     */
    @Test
    public void testProfile() {
        String correctPassword = "Cmeeiadp";
        assertEquals(correctPassword, testProfile.getEncryptedPassword());
    }
}
