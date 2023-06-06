package requirement_t7.model;

import org.jdom2.input.SAXBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import javax.lang.model.util.Elements;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Execution {
    public static String runTests(Class<?> testClass) {
        String res = "";
        // Run the JUnit tests
        Result result = JUnitCore.runClasses(testClass);

        // Print the results
        for (Failure failure : result.getFailures()) {
            res += failure.toString() + "\n";
        }

        try {
            doCoverage();
        } catch (InterruptedException e) {

        }

        res += "\n Tests run: " + result.getRunCount() + ", Failures: " + result.getFailureCount() + ", Ignored: " + result.getIgnoreCount();
        res+= "\n"+getCoverage();
        return res;
    }

    public static void doCoverage() throws InterruptedException {
        try {
            // Obtener la ruta del directorio actual del proyecto
            String projectDir = System.getProperty("user.dir");

            // Definir la ruta al archivo JAR de jacococli.jar (relativa al directorio del proyecto)
            String jacocoJarPath = "lib/jacococli.jar";

            // Definir la ruta al archivo coverage.exec (relativa al directorio del proyecto)
            String coverageExecPath = "coverage/coverage.exec";

            // Definir la ruta al directorio de clase (classfiles) (relativa al directorio del proyecto)
            String classFilesPath = "target";

            // Definir la ruta al archivo de informe XML (relativa al directorio del proyecto)
            String reportXmlPath = "coverage/report.xml";

            // Crear el proceso para ejecutar el comando
            List<String> command = new ArrayList<>();
            command.add("java");
            command.add("-jar");
            command.add(jacocoJarPath);
            command.add("report");
            command.add(coverageExecPath);
            command.add("--xml");
            command.add(reportXmlPath);
            command.add("--classfiles");
            command.add(classFilesPath);

            // Establecer el directorio base del proceso
            ProcessBuilder processBuilder = new ProcessBuilder(command);

            // Establecer el directorio base del proceso
            processBuilder.directory(new File(projectDir));

            // Redirigir la salida estándar y la salida de error del proceso a la consola
            processBuilder.inheritIO();

            // Ejecutar el comando
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Informe de cobertura generado exitosamente.");
            } else {
                System.err.println("Ocurrió un error al generar el informe de cobertura.");
            }
        } catch (IOException e) {
        }
    }

    public static String getCoverage(){
        String res="";
        try {
            // Load the XML file
            File inputFile = new File("coverage/report.xml");
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
                                System.out.println(counter.getAllElements());
                                coveredLines += Integer.parseInt(counter.attr("covered"));
                                missedLines += Integer.parseInt(counter.attr("missed"));
                            }
                        }
                    }
                }
            }

            // Calculate coverage percentage
            int totalLines = coveredLines + missedLines;
            double coveragePercentage = (double) coveredLines / totalLines * 100;

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
