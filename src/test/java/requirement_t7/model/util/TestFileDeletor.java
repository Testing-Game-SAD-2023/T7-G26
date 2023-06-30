package requirement_t7.model.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFileDeletor {
    @Test
    void testDeleteNotExistingFile(){
        FileDeletor.deleteFile("asdasdasdasd.txt");
        assertTrue(true);
    }
}
