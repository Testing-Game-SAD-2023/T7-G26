package requirement_t7.model.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFileCreator {
    @Test
    void testCreateNotExistingFile(){
        FileCreator.createFile("asasas/asdasdasdasd.txt","asass");
        assertTrue(true);
    }
}
