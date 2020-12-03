package SocialMedia.ProfileExceptions;

/**
 * Represents a user entering in the wrong password
 *
 * @author everyone
 * @version 11/21/20
 */
public class InvalidPasswordException extends Exception {

    public InvalidPasswordException(String message) {
        super(message);
    }

}
