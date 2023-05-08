package requirement_t7.model;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Execution {
    public static String runTests(Class<?> testClass) {
        String res = "";
        // Run the JUnit tests
        Result result = JUnitCore.runClasses(testClass);

        // Print the results
        for (Failure failure : result.getFailures()) {
            res += failure.toString() + "\n";
        }

        res += "\n Tests run: " + result.getRunCount() + ", Failures: " + result.getFailureCount() + ", Ignored: " + result.getIgnoreCount();
        return res;
    }
}
