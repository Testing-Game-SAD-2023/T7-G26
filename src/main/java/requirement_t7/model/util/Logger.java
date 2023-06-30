package requirement_t7.model.util;

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
    private File log;
    private String path;
    private Logger(){
        String folderPath = "logs/";
        String fileName = "LOG-T7-G26-" + getFormattedDateTime("file") + ".txt";
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
            e.printStackTrace();
        }
    }

    private String getFormattedDateTime(String type) {
        SimpleDateFormat dateFormat = null;
        if(type == "file"){
            dateFormat = new SimpleDateFormat("d-EEE-yyyy--HH-mm-ss");
        } else if(type == "log"){
            dateFormat = new SimpleDateFormat("dd/MM/yyyy--HH:mm:ss");
        }
        return dateFormat.format(new Date());
    }

    public void log(String type, String message) {
        try {
            FileWriter fileWriter = new FileWriter(this.path, true);
            fileWriter.write(getFormattedMessage(type, message) + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFormattedMessage(String type, String message){
        return getFormattedDateTime("log") + type + message;
    }
}