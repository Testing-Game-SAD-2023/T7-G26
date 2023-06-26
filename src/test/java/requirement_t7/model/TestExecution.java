package requirement_t7.model;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import requirement_t7.model.Compilation;
import requirement_t7.model.Execution;
import requirement_t7.model.Game;
import requirement_t7.model.util.FileDeletor;

public class TestExecution {
    @Test
    public void testExecutionCorrectly(){
        Compilation.compileClass("InputClass", "package requirement_t7;\n" +
                "public class InputClass {\n" +
                "\n" +
                "    public InputClass(){}\n" +
                "    public String evenOrOdd(int num) {\n" +
                "        if (num % 2 == 0) {\n" +
                "            return \"even\";\n" +
                "        } else {\n" +
                "            return \"odd\";\n" +
                "        }\n" +
                "    }\n" +
                "}");
        Compilation.compileTest("InputTestClass", "package requirement_t7;\n" +
                    "import org.junit.BeforeClass;\n" +
                    "import org.junit.Test;\n" +
                    "\n" +
                    "import static org.junit.Assert.assertEquals;\n" +
                    "\n" +
                    "\n" +
                    "public class InputTestClass {\n" +
                    "    private static InputClass inputClass;\n" +
                    "    @BeforeClass\n" +
                    "    public static void init(){\n" +
                    "        inputClass = new InputClass();\n" +
                    "    }\n" +
                    "\n" +
                    "    @Test\n" +
                    "    public void testEvenNumber() {\n" +
                    "        int num = 4;\n" +
                    "        String result = inputClass.evenOrOdd(num);\n" +
                    "        assertEquals(\"even\", result);\n" +
                    "    }\n" +
                    "\n" +
                    "    @Test\n" +
                    "    public void testOddNumber() {\n" +
                    "        int num = 7;\n" +
                    "        String result = inputClass.evenOrOdd(num);\n" +
                    "        assertEquals(\"odd\", result);\n" +
                    "    }\n" +
                    "}");

        assertTrue(Execution.runTests().contains("Tests run:"));
        FileDeletor.deleteFile("src/test/java/requirement_t7/InputTestClass.java");
        FileDeletor.deleteFile("src/main/java/requirement_t7/InputClass.java");
    }

    @Test
    public void testExecutionFailure(){
        Compilation.compileClass("InputClass", "package requirement_t7;\n" +
                "public class InputClass {\n" +
                "\n" +
                "    public InputClass(){}\n" +
                "    public String evenOrOdd(int num) {\n" +
                "        if (num % 2 == 0) {\n" +
                "            return \"even\";\n" +
                "        } else {\n" +
                "            return \"odd\";\n" +
                "        }\n" +
                "    }\n" +
                "}");
        Compilation.compileTest("InputTestClass", "package requirement_t7;\n" +
                "import org.junit.BeforeClass;\n" +
                "import org.junit.Test;\n" +
                "\n" +
                "import static org.junit.Assert.assertEquals;\n" +
                "\n" +
                "\n" +
                "public class InputTestClass {\n" +
                "    private static InputClass inputClass;\n" +
                "    @BeforeClass\n" +
                "    public static void init(){\n" +
                "        inputClass = new InputClass();\n" +
                "    }\n" +
                "\n" +
                "    @Test\n" +
                "    public void testEvenNumber() {\n" +
                "        int num = 4;\n" +
                "        String result = inputClass.evenOrOdd(num);\n" +
                "        assertEquals(\"odd\", result);\n" +
                "    }\n" +
                "\n" +
                "    @Test\n" +
                "    public void testOddNumber() {\n" +
                "        int num = 7;\n" +
                "        String result = inputClass.evenOrOdd(num);\n" +
                "        assertEquals(\"even\", result);\n" +
                "    }\n" +
                "}");

        assertTrue(Execution.runTests().contains("Failures: 2"));
        FileDeletor.deleteFile("src/test/java/requirement_t7/InputTestClass.java");
        FileDeletor.deleteFile("src/main/java/requirement_t7/InputClass.java");
    }
}
