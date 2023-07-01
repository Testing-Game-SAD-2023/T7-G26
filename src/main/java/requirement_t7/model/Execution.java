package requirement_t7.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import requirement_t7.model.util.CommandExecution;
import requirement_t7.model.util.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class Execution {

    /**
     * Executes a test class
     *
     * @param inputClassName The class name of the class that will be tested
     * @param inputTestClassName The class name of the test to execute
     * @return The output of the result of the test execution
     */
    public static String runTest(String inputClassName, String inputTestClassName) {
        Logger logger = Logger.getInstance();
        logger.log(Logger.RUNNING, "Class: Execution.java, method: runTests()");
        StringBuilder res = new StringBuilder();

        String[] command = getTestExecutionCommand(inputTestClassName);
        CommandExecution process = new CommandExecution();
        InputStream inputStream = process.executeCommand(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String line;
            boolean f = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Results:")) {
                    f = true;
                } else if (line.contains("---")) {
                    f = false;
                } else if (f) {
                    res.append(line.substring(7)).append("\n");
                }
            }
        } catch (IOException e) {
            logger.log(Logger.ERROR, e.getMessage());
        }
        res.append(getCoverage(inputClassName));
        return res.toString();
    }

    /**
     * Get the coverage of a class
     *
     * @param inputClassName Name of the class from which coverage is obtained
     * @return Coverage of the class
     */
    public static String getCoverage(String inputClassName) {
        Logger logger = Logger.getInstance();
        logger.log(Logger.RUNNING, "Class: Execution.java, method: getCoverage()");
        StringBuilder res = new StringBuilder();
        try {
            File inputFile = new File("target/site/jacoco/jacoco.xml");
            Document document = Jsoup.parse(inputFile, null, "", Parser.xmlParser());

            List<Element> classes = document.select("class");
            int coveredLines = 0;
            int missedLines = 0;

            for (Element clazz : classes) {
                String source = clazz.attr("sourcefilename");
                if (source.equals(inputClassName+".java")) {
                    List<Element> methods = clazz.select("method");
                    for (Element method : methods) {
                        List<Element> counters = method.select("counter");
                        for (Element counter : counters) {
                            String type = counter.attr("type");
                            if (type.equals("LINE")) {
                                coveredLines += Integer.parseInt(counter.attr("covered"));
                                missedLines += Integer.parseInt(counter.attr("missed"));
                            }
                        }
                    }
                }
            }

            int totalLines = coveredLines + missedLines;
            double coveragePercentage = 0;
            if (totalLines != 0) {
                coveragePercentage = (double) coveredLines / totalLines * 100;
            }

            res.append("\n Total Lines: ").append(totalLines);
            res.append("\n Covered Lines: ").append(coveredLines);
            res.append("\n Missed Lines: ").append(missedLines);
            res.append("\n Coverage Percentage: ").append(coveragePercentage).append("%");
        } catch (Exception e) {
            logger.log(Logger.ERROR, e.getMessage());
        }
        return res.toString();
    }

    /**
     * Gets the run test command of the test to execute it
     * @param inputTestClassName The class test name to execute
     * @return Command to execute in the command line depending on the OS
     */
    private static String[] getTestExecutionCommand(String inputTestClassName) {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return new String[]{"cmd.exe", "/c", "mvn", "test", "-Dtest=" + inputTestClassName};
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            return new String[]{"mvn", "test", "-Dtest=" + inputTestClassName};
        }
        return null;
    }
}
