package requirement_t7.model.util;

import org.springframework.stereotype.Component;

import java.io.File;

/**
 * This class is responsible of deleting a file
 *
 * @author Pedro Zahonero Mangas
 * @author Pablo Garcia Fernandez
 * @version 1.0
 * @since 2023-06-26
 */
@Component
public class FileDeletor {

    /**
     * Deletes a file.
     * @param path The path of the file to delete.
     */
    public static void deleteFile(String path){
        Logger logger = Logger.getInstance();
        logger.log(Logger.RUNNING, "Class: FileDeletor.java, method: deleteFile()");

        File file = new File(path);
        if (file.exists()) {
            if (file.delete()) {
                logger.log(Logger.INFO, "File deleted successfully.");
            } else {
                logger.log(Logger.INFO, "Failed to delete the file.");
            }
        } else {
            logger.log(Logger.INFO, "File does not exist.");
        }
    }
}

