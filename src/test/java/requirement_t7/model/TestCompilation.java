package requirement_t7.model;

import org.junit.jupiter.api.Test;
import requirement_t7.model.util.FileDeletor;

import static org.junit.jupiter.api.Assertions.*;

public class TestCompilation {
    @Test
    public void testCompilationTestError(){
        assertTrue(Compilation.compileTest("TestingFile", "I am an error").contains("[ERROR]"));
        FileDeletor.deleteFile("src/test/java/requirement_t7/TestingFile.java");
    }

    @Test
    public void testCompilationTestSuccessful(){
        assertTrue(Compilation.compileTest("InputTestClass", "package requirement_t7;\n" +
                "import org.junit.Test;\n" +
                "\n" +
                "import static org.junit.Assert.assertEquals;\n" +
                "\n" +
                "\n" +
                "public class InputTestClass {\n" +
                "    @Test\n" +
                "    public void test1(){\n" +
                "        assertEquals(5, 5);\n" +
                "    }" +
                "}").equals(""));
        FileDeletor.deleteFile("src/test/java/requirement_t7/InputTestClass.java");
    }

    @Test
    public void testCompilationClassSuccessful(){
        assertTrue(Compilation.compileClass("InputClass", "package requirement_t7;\n" +
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
                "}").equals(""));
        FileDeletor.deleteFile("src/main/java/requirement_t7/InputClass.java");
    }

    @Test
    public void testCompilationClassError(){
        assertTrue(Compilation.compileClass("TestingFile", "I am an error").contains("[ERROR]"));
        FileDeletor.deleteFile("src/main/java/requirement_t7/TestingFile.java");
    }
}
