package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import requirement_t7.model.Compilation;
import requirement_t7.model.Execution;
import requirement_t7.model.Game;

public class TestExecution {
    @Test
    public void testExecutionCorrectly(){
        Game game = new Game();
        game.setInputClassName("InputClass");
        game.setInputTestClassName("InputTestClass");
        game.compile();
        Class<?> clazz = null;
        try {
            clazz = Compilation.compileClass("requirement_t7.model.InputTestClass", "package requirement_t7.model;\n" +
                    "import org.junit.Test;\n" +
                    "import static org.junit.Assert.assertEquals;\n" +
                    "\n" +
                    "\n" +
                    "public class InputTestClass {\n" +
                    "    @Test\n" +
                    "    public void testEvenNumber() {\n" +
                    "        int num = 4;\n" +
                    "        String result = InputClass.evenOrOdd(num);\n" +
                    "        assertEquals(\"even\", result);\n" +
                    "    }\n" +
                    "\n" +
                    "    @Test\n" +
                    "    public void testOddNumber() {\n" +
                    "        int num = 7;\n" +
                    "        String result = InputClass.evenOrOdd(num);\n" +
                    "        assertEquals(\"odd\", result);\n" +
                    "    }\n" +
                    "}");
        } catch (Exception e) {throw new RuntimeException(e);}

        assertTrue(Execution.runTests(clazz).contains("Tests run:"));
    }
}
