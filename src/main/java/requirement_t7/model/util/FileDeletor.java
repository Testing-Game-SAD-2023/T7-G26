package requirement_t7.model.util;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileDeletor {
    public static void deleteFile(String path){
        Logger.getInstance().log(Logger.RUNNING,"Class: FileDeletor.java, method: deleteFile()");
        File file = new File(path);
        if (file.exists()) {
            if (file.delete()) {
                Logger.getInstance().log(Logger.INFO,"File deleted successfully.");
            } else {
                Logger.getInstance().log(Logger.INFO,"Failed to delete the file.");
            }
        } else {
            Logger.getInstance().log(Logger.INFO,"File does not exist.");
        }
    }
}
