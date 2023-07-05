package requirement_t7.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import requirement_t7.model.util.CommandExecution;
import requirement_t7.model.util.FileCreator;
import requirement_t7.model.util.FileDeletor;
import requirement_t7.model.util.Logger;

/**
 * This class represents the compilation task.
 *
 * @author Pedro Zahonero Mangas
 * @author Pablo Garcia Fernandez
 * @version 1.0
 * @since 2023-04-15
 */
public class Compilation {

    /**
     * Converts a txt file in a java class and compiles it
     * @param inputClassName Class name of the class to compile
     * @param inputClassCode Class code of the class to compile
     * @return Information of the result of the compilation
     */
    public static String compileClass(String inputClassName, String inputClassCode) {
        Logger logger = Logger.getInstance();
        logger.log(Logger.RUNNING, "Class: Compilation.java, method: compileClass()");

        String classFilePath = "src/main/java/requirement_t7/" + inputClassName;
        FileCreator.createFile(classFilePath, inputClassCode);

        String res;

        String[] command = getCompileCommand();
        CommandExecution process = new CommandExecution();
        InputStream inputStream = process.executeCommand(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        res = readInputStream(reader);
        if(!res.equals(""))
            FileDeletor.deleteFile("src/main/java/requirement_t7/InputClass.java");

        return res;
    }

    /**
     * Converts a txt file in a java test class and compiles it
     * @param inputTestClassName Class name of the class test to compile
     * @param inputTestClassCode Class code of the class test to compile
     * @return Information of the result of the compilation
     */
    public static String compileTest(String inputTestClassName, String inputTestClassCode) {
        Logger logger = Logger.getInstance();
        logger.log(Logger.RUNNING, "Class: Compilation.java, method: compileTest()");

        String testFilePath = "src/test/java/requirement_t7/" + inputTestClassName;
        FileCreator.createFile(testFilePath, inputTestClassCode);

        String res;

        String[] command = getTestCompileCommand(inputTestClassName);
        CommandExecution process = new CommandExecution();
        InputStream inputStream = process.executeCommand(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        res = readInputStream(reader);
        if(!res.equals("")){
            FileDeletor.deleteFile("src/main/java/requirement_t7/InputClass.java");
            FileDeletor.deleteFile("src/test/java/requirement_t7/InputTestClass.java");
        }

        return res;
    }

    /**
     * Gets the compile command to execute it
     * @return Command to execute in the command line depending on the OS
     */
    private static String[] getCompileCommand() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return new String[]{"cmd.exe", "/c", "mvn", "compile"};
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            return new String[]{"mvn", "compile"};
        } else {
            throw new UnsupportedOperationException("Unsupported operating system: " + os);
        }
    }

    /**
     * Gets the compile command of the test to execute it
     * @param testClassName Class test name to compile
     * @return Command to execute in the command line depending on the OS
     */
    private static String[] getTestCompileCommand(String testClassName) {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return new String[]{"cmd.exe", "/c", "mvn", "test-compile", "-Dtest=" + testClassName};
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            return new String[]{"mvn", "test-compile", "-Dtest=" + testClassName};
        } else {
            throw new UnsupportedOperationException("Unsupported operating system: " + os);
        }
    }

    /**
     * Reads the console log of the execution of the commands and sums it up
     * @param reader The reader of the inputStream
     * @return The summarized output
     */
    private static String readInputStream(BufferedReader reader) {
        Logger logger = Logger.getInstance();
        logger.log(Logger.RUNNING, "Class: Compilation.java, method: readInputStream()");

        StringBuilder res = new StringBuilder();
        try {
            String line;
            boolean f = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains("COMPILATION ERROR :")) {
                    f = true;
                } else if (line.contains("INFO") && !line.contains("[INFO] -------------------------------------------------------------")) {
                    f = false;
                } else if (f && !line.contains("[INFO] -------------------------------------------------------------")) {
                    res.append(line).append("\n");
                    logger.log(Logger.ERROR, line);
                }
            }
        } catch (IOException e) {
            logger.log(Logger.ERROR, e.getMessage());
        }
        return res.toString();
    }
}
