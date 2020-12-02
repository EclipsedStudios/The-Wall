import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * A JUnit tester class for all methods that are not concurrency or GUI-based.
 *
 * @author Cole Busa
 * @version 12/2/20
 */
public class Tester {
    String correctMessage = "Robert";

    /**
     * First test method to understand JUnit
     */
    @Test
    public void testSalutationMessage() {
        String message = "Robert";
        assertEquals(message, correctMessage);
    }
}
