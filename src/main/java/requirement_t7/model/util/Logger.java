package requirement_t7.model.util;

import org.apache.juli.logging.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Logger {
    public static final String ERROR = " [ERROR] -> ";
    public static final String RUNNING = " [RUNNING] -> ";
    public static final String INFO = " [INFO] -> ";
    private static Logger instance;
    private String path;
    private Logger(){
        String folderPath = "logs/";
        String fileName = "LOG-T7-G26-" + getFormattedDateTimeFile() + ".txt";
        this.path = folderPath + fileName;
        createLog(this.path);
    }

    public static Logger getInstance(){
        if(instance == null){
            instance = new Logger();
        }
        return instance;
    }

    private void createLog(String filePath) {
        try {
            Files.createDirectories(Paths.get("logs"));
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.close();
            System.out.println("File '" + filePath + "' created successfully.");
        } catch (IOException e) {
            Logger.getInstance().log(Logger.ERROR, e.getMessage());
        }
    }

    private String getFormattedDateTimeFile() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d-EEE-yyyy--HH-mm-ss");
        return dateFormat.format(new Date());
    }

    private String getFormattedDateTimeLog() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy--HH:mm:ss");
        return dateFormat.format(new Date());
    }

    public void log(String type, String message) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(this.path, true);
            fileWriter.write(getFormattedMessage(type, message) + "\n");
        } catch (IOException e) {
            Logger.getInstance().log(Logger.ERROR, e.getMessage());
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    Logger.getInstance().log(Logger.ERROR, e.getMessage());
                }
            }
        }
    }

    private String getFormattedMessage(String type, String message){
        return getFormattedDateTimeLog() + type + message;
    }
}
