package requirement_t7.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import requirement_t7.model.util.FileDeletor;

public class TestExecution {
    @Test
    void testExecutionCorrectly(){
        Compilation.compileClass("InputClass", """
                package requirement_t7;
                public class InputClass {

                    public InputClass(){}
                    public String evenOrOdd(int num) {
                        if (num % 2 == 0) {
                            return "even";
                        } else {
                            return "odd";
                        }
                    }
                }""");
        Compilation.compileTest("InputTestClass", """
                package requirement_t7;
                import org.junit.BeforeClass;
                import org.junit.Test;

                import static org.junit.Assert.assertEquals;


                public class InputTestClass {
                    private static InputClass inputClass;
                    @BeforeClass
                    public static void init(){
                        inputClass = new InputClass();
                    }

                    @Test
                    public void testEvenNumber() {
                        int num = 4;
                        String result = inputClass.evenOrOdd(num);
                        assertEquals("even", result);
                    }

                    @Test
                    public void testOddNumber() {
                        int num = 7;
                        String result = inputClass.evenOrOdd(num);
                        assertEquals("odd", result);
                    }
                }""");

        assertTrue(Execution.runTest("InputClass","InputTestClass").contains("Tests run: 2"));
        FileDeletor.deleteFile("src/test/java/requirement_t7/InputTestClass.java");
        FileDeletor.deleteFile("src/main/java/requirement_t7/InputClass.java");
    }

    @Test
    void testExecutionFailure(){
        Compilation.compileClass("InputClass", """
                package requirement_t7;
                public class InputClass {

                    public InputClass(){}
                    public String evenOrOdd(int num) {
                        if (num % 2 == 0) {
                            return "even";
                        } else {
                            return "odd";
                        }
                    }
                }""");
        Compilation.compileTest("InputTestClass", """
                package requirement_t7;
                import org.junit.BeforeClass;
                import org.junit.Test;

                import static org.junit.Assert.assertEquals;


                public class InputTestClass {
                    private static InputClass inputClass;
                    @BeforeClass
                    public static void init(){
                        inputClass = new InputClass();
                    }

                    @Test
                    public void testEvenNumber() {
                        int num = 4;
                        String result = inputClass.evenOrOdd(num);
                        assertEquals("odd", result);
                    }

                    @Test
                    public void testOddNumber() {
                        int num = 7;
                        String result = inputClass.evenOrOdd(num);
                        assertEquals("even", result);
                    }
                }""");

        assertTrue(Execution.runTest("InputClass", "InputTestClass").contains("Failures: 2"));
        FileDeletor.deleteFile("src/test/java/requirement_t7/InputTestClass.java");
        FileDeletor.deleteFile("src/main/java/requirement_t7/InputClass.java");
    }
}
