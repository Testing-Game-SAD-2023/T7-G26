package requirement_t7.model.util;

import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class FileCreator {
    public static File createFile(String name, String code) {
        File file = new File(name+".java");
        try (FileWriter fw = new FileWriter(file.getAbsoluteFile());
             BufferedWriter bw = new BufferedWriter(fw);){
            bw.write(code);
            bw.close();
            System.out.println("File created successfully.");
        } catch (IOException e) {
        }
        return file;
    }
}
