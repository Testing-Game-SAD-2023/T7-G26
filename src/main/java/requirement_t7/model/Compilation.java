package requirement_t7.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import requirement_t7.model.util.CommandExecution;
import requirement_t7.model.util.FileCreator;
import requirement_t7.model.util.Logger;


public class Compilation {

    public static String compileClass(String inputClassName, String inputClassCode){
        Logger.getInstance().log(Logger.RUNNING,"Class: Compilation.java, method: compileClass()");
        FileCreator.createFile("src/main/java/requirement_t7/"+inputClassName,inputClassCode);

        String res = "";
        // Compile the class
        String[] command = null;
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            //Windows
            command = new String[]{"cmd.exe", "/c", "mvn", "compile"};
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            //Linux o macOS
            command = new String[]{"mvn", "compile"};
        }
        CommandExecution process = new CommandExecution();
        InputStream inputStream = process.executeCommand(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        res = readInputStream(reader);
        return res;
    }

    public static String compileTest(String inputTestClassName, String inputTestClassCode){
        Logger.getInstance().log(Logger.RUNNING,"Class: Compilation.java, method: compileTest()");
        FileCreator.createFile("src/test/java/requirement_t7/"+inputTestClassName,inputTestClassCode);

        String res = "";
        // Compile the test class

        String[] command = null;
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            //Windows
            command = new String[]{"cmd.exe", "/c", "mvn", "test-compile", "-Dtest="+inputTestClassName};
        } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            //Linux or macOS
            command = new String[]{"mvn", "test-compile", "-Dtest="+inputTestClassName};
        }
        CommandExecution process = new CommandExecution();
        InputStream inputStream = process.executeCommand(command);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        res = readInputStream(reader);
        return res;
    }

    public static String readInputStream(BufferedReader reader) {
        Logger.getInstance().log(Logger.RUNNING,"Class: Compilation.java, method: readInputStream()");
        String res = "";
        try {
            String line;
            boolean f = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains("COMPILATION ERROR :"))
                    f = true;
                else if (line.contains("INFO") && !line.contains("[INFO] -------------------------------------------------------------"))
                    f = false;
                else if (f && !line.contains("[INFO] -------------------------------------------------------------")) {
                    res += line + "\n";
                }
            }

        } catch (IOException e) {
            Logger.getInstance().log(Logger.ERROR,e.getMessage());
        }
        return res;
    }
}
