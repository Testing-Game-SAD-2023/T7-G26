package model;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
/*import org.jacoco.core.analysis.CoverageBuilder;
import org.jacoco.core.analysis.ICoverage;
import org.jacoco.core.runtime.IRuntime;
import org.jacoco.core.runtime.RuntimeSetup;
import org.jacoco.report.IReport;
import org.jacoco.report.ReportEngine;
import org.jacoco.report.html.HtmlReportEngine;*/

public class Execution {
    public static void runTests(Class<?> testClass) {

        // Run the JUnit tests
        Result result = JUnitCore.runClasses(testClass);

        // Print the results
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

       /* IRuntime runtime = RuntimeSetup.createRuntime();

        // Execute the test
        runtime.run(args);

        // Create a coverage builder
        CoverageBuilder coverageBuilder = new CoverageBuilder();

        // Collect the coverage data
        coverageBuilder.merge(runtime.getExecutionData());

        // Create a report engine
        ReportEngine reportEngine = new HtmlReportEngine();

        // Generate the coverage report
        reportEngine.generate(coverageBuilder, "target/coverage-report");*/

        System.out.println("Tests run: " + result.getRunCount() + ", Failures: " + result.getFailureCount() + ", Ignored: " + result.getIgnoreCount());
    }
}
