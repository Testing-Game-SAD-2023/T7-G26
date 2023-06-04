package requirement_t7.model;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import org.jacoco.agent.rt.RT;

public class Execution {
    public static String runTests(Class<?> testClass) {
        String res = "";

        try {
            // Start the JaCoCo agent
            RT.getAgent().dump(true);

            // Run the JUnit tests
            Result result = JUnitCore.runClasses(testClass);

            // Print the results
            for (Failure failure : result.getFailures()) {
                res += failure.toString() + "\n";
            }

            res += "\n Tests run: " + result.getRunCount() + ", Failures: " + result.getFailureCount() + ", Ignored: " + result.getIgnoreCount();

            // Stop the JaCoCo agent and collect coverage information
            RT.getAgent().dump(true);
        } catch (Exception e) {
            // Handle any exceptions that occur during test execution or coverage collection
            res = "Exception occurred: " + e.getMessage();
        }

        return res;
    }
}
