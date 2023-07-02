package requirement_t7.model.util;

import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is responsible of creating a File.
 *
 * @author Pedro Zahonero Mangas
 * @author Pablo Garcia Fernandez
 * @version 1.0
 * @since 2023-04-15
 */
@Component
public class FileCreator {

    /**
     * Method that creates a java file
     * @param name Name of the java file to create
     * @param code Code of the java file
     * @return file
     */
    public static File createFile(String name, String code) {
        Logger logger = Logger.getInstance();
        logger.log(Logger.RUNNING, "Class: FileCreator.java, method: createFile()");

        File file = new File(name + ".java");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(code);
            logger.log(Logger.INFO, "File created successfully.");
        } catch (IOException e) {
            logger.log(Logger.ERROR, e.getMessage());
        }
        return file;
    }
}
