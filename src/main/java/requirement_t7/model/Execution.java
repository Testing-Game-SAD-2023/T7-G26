package requirement_t7.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import requirement_t7.model.util.CommandExecution;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Execution {

    public static String runTests(){
        String res = "";

        // Maven command for executing the test
        String[] command = null;
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            //Windows
            command = new String[]{"cmd.exe", "/c", "mvn", "test", "-Dtest=InputTestClass"};
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            //Linux or macOS
            command = new String[]{"mvn", "test", "-Dtest=InputTestClass"};
        }

        CommandExecution process = new CommandExecution();
        InputStream inputStream = process.executeCommand(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        //doCoverage();
        try {
            String line;
            boolean f=false;
            while ((line = reader.readLine()) != null) {
                if(line.contains("Results:"))
                    f=true;
                else if(line.contains("--- "))
                    f=false;
                else if(f) {
                    res += line.substring(7) + "\n";
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        res+=getCoverage();
        return res;
    }

    public static String getCoverage(){
        String res="";
        try {
            // Load the XML file
            File inputFile = new File("target/site/jacoco/jacoco.xml");
            Document document = Jsoup.parse(inputFile, null, "", Parser.xmlParser());

            // Find the coverage data
            List<Element> classes = document.select("class");
            int coveredLines = 0;
            int missedLines = 0;

            for (Element clazz : classes) {
                String source = clazz.attr("sourcefilename");
                if (source.equals("InputClass.java")) {
                    List<Element> methods = clazz.select("method");
                    for (Element method : methods) {
                        List<Element> counters = method.select("counter");
                        for (Element counter : counters) {
                            String type = counter.attr("type");
                            if(type.equals("LINE")){
                                coveredLines += Integer.parseInt(counter.attr("covered"));
                                missedLines += Integer.parseInt(counter.attr("missed"));
                            }
                        }
                    }
                }
            }

            // Calculate coverage percentage
            int totalLines = coveredLines + missedLines;
            double coveragePercentage =0;
            if(totalLines!=0)
                coveragePercentage = (double) coveredLines / totalLines * 100;

            // Print the coverage results
            res+="\n Total Lines: " + totalLines;
            res+="\n Covered Lines: " + coveredLines;
            res+="\n Missed Lines: " + missedLines;
            res+="\n Coverage Percentage: " + coveragePercentage + "%";



        } catch (Exception e) {
        }
        // Print coverage percentage
        return res;
    }
}
