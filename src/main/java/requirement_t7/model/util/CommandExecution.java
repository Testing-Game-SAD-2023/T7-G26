package requirement_t7.model.util;

import java.io.IOException;
import java.io.InputStream;

/**
 * This class is responsible of the execution of commands in the command line.
 *
 * @author Pedro Zahonero Mangas
 * @author Pablo Garcia Fernandez
 * @version 1.0
 * @since 2023-06-24
 */
public class CommandExecution {

    /**
     * Method that executes a command in the command line
     * @param command The command to execute
     * @return The result of the execution of the command
     */
    public InputStream executeCommand(String[] command) {
        InputStream inputStream = null;
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();
            inputStream = process.getInputStream();
        } catch (IOException e) {
            Logger.getInstance().log(Logger.ERROR, e.getMessage());
        }
        return inputStream;
    }
}
