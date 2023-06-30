package requirement_t7.model.util;

import org.junit.jupiter.api.Test;
import requirement_t7.model.util.CommandExecution;
import requirement_t7.model.util.FileCreator;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCommandExecution {
    @Test
    void testCommandNotExisting(){
        CommandExecution ce = new CommandExecution();
        String[] commands = {"",""};
        ce.executeCommand(commands);
        assertTrue(true);
    }
}
