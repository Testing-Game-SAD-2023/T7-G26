package requirement_t7.model.util;

import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class FileCreator {
    public static File createFile(String name, String code) {
        Logger.getInstance().log(Logger.RUNNING,"Class: FileCreator.java, method: createFile()");
        File file = new File(name+".java");
        try (FileWriter fw = new FileWriter(file.getAbsoluteFile());
             BufferedWriter bw = new BufferedWriter(fw);){
            bw.write(code);
            bw.close();
            Logger.getInstance().log(Logger.INFO,"File created successfully.");
        } catch (IOException e) {
            Logger.getInstance().log(Logger.ERROR,e.getMessage());
        }
        return file;
    }
}
