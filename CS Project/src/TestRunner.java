import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * A class to run all of the tests in the Tester class.
 *
 * @author Cole Busa
 * @version 12/3/20
 */
public class TestRunner {
    /**
     * A main method that prints any assertion errors that arise, otherwise
     * prints "Working Correctly!"
     */
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(Tester.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        if (result.wasSuccessful()) {
            System.out.println("Working Correctly!");
        }
    }
}