package requirement_t7.model.util;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileDeletor {
    public static void deleteFile(String path){
        File file = new File(path);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Failed to delete the file.");
            }
        } else {
            System.out.println("File does not exist.");
        }
    }
}
