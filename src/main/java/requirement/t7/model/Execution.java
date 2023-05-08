package requirement.t7.model;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Execution {
    public static void runTests(Class<?> testClass) {

        // Run the JUnit tests
        Result result = JUnitCore.runClasses(testClass);

        // Print the results
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println("Tests run: " + result.getRunCount() + ", Failures: " + result.getFailureCount() + ", Ignored: " + result.getIgnoreCount());
    }
}
