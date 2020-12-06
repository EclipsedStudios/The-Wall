package SocialMedia.ProfileExceptions;

/**
 * Represents a user entering in an existening username for a profile creation
 *
 * @author everyone
 * @version 11/21/20
 */

public class DuplicateUserException extends Exception {
    public DuplicateUserException(String message) {
        super(message);
    }
}
