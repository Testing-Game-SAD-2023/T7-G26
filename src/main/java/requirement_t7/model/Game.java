package requirement_t7.model;

import org.springframework.stereotype.Service;

import requirement_t7.model.util.FileDeletor;
import requirement_t7.model.util.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class Game {

    private String inputTestClassName;
    private String inputClassName;

    private boolean compiled;

    public Game() {
    }

    /**
     * Obtains the code of the txt file
     *
     * @param input the file name of the txt file to obtain the code
     * @return The code of the txt file
     */
    private String obtainCode(String input) {
        Logger logger = Logger.getInstance();
        logger.log(Logger.RUNNING, "Class: Game.java, method: obtainCode()");
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(input + ".txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            logger.log(Logger.ERROR, e.getMessage());
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }

    /**
     * Compiles the classes of the game
     *
     * @return Result of the compilation
     */
    public String compile() {
        Logger logger = Logger.getInstance();
        logger.log(Logger.RUNNING, "Class: Game.java, method: compile()");
        String inputClassCode = obtainCode(inputClassName);
        String inputTestClassCode = obtainCode(inputTestClassName);

        StringBuilder res = new StringBuilder();
        compiled = false;

        res.append(Compilation.compileClass(inputClassName, inputClassCode));
        res.append(Compilation.compileTest(inputTestClassName, inputTestClassCode));

        if (res.length() == 0) {
            compiled = true;
            res.append("Compiled");
        }
        return res.toString();
    }

    /**
     * Executes the classes of the game
     *
     * @return Result of execution
     */
    public String execute() {
        Logger logger = Logger.getInstance();
        logger.log(Logger.RUNNING, "Class: Game.java, method: execute()");
        if (!compiled) {
            return "Cannot execute because you have not compiled";
        }

        String res = Execution.runTest(inputClassName, inputTestClassName);

        FileDeletor.deleteFile("src/main/java/requirement_t7/" + inputClassName + ".java");
        FileDeletor.deleteFile("src/test/java/requirement_t7/" + inputTestClassName + ".java");

        return res;
    }

    /**
     * Sets the test class name of the game
     *
     * @param inputTestClassName Name of the test of the game
     */
    public void setInputTestClassName(String inputTestClassName) {
        Logger.getInstance().log(Logger.RUNNING, "Class: Game.java, method: setInputTestClassName()");
        this.inputTestClassName = inputTestClassName;
    }

    /**
     * Sets the class name of the game
     *
     * @param inputClassName Name of the class of the game
     */
    public void setInputClassName(String inputClassName) {
        Logger.getInstance().log(Logger.RUNNING, "Class: Game.java, method: setInputClassName()");
        this.inputClassName = inputClassName;
    }
}
