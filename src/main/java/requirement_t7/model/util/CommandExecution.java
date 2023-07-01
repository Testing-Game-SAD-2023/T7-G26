package requirement_t7.model.util;

import java.io.IOException;
import java.io.InputStream;

public class CommandExecution {
    public InputStream executeCommand(String[] command) {
        InputStream inputStream = null;
        try {
            // Create a process to execute the command
            ProcessBuilder processBuilder = new ProcessBuilder(command);

            // Redirect the process output to the console
            processBuilder.redirectErrorStream(true);

            // Start the process
            Process process = null;

            process = processBuilder.start();

            // Read the process output
            inputStream = process.getInputStream();
        } catch (IOException e) {
            Logger.getInstance().log(Logger.ERROR,e.getMessage());
        }
        return inputStream;
    }

}
