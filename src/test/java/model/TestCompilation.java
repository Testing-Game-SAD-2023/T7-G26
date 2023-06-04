package model;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requirement_t7.model.Compilation;
import requirement_t7.model.Game;
import requirement_t7.model.InputClass;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestCompilation {
    private Compilation compilation;

    @BeforeEach
    public void init(){
        new Compilation();
    }

    @Test
    public void testCompilationError(){
        assertThrows(Exception.class, () -> compilation.compileClass("Name", "I am an error in the code"));
    }

    @Test
    public void testCompilationSuccessful(){
        Game game = new Game();
        game.setInputClassName("InputClass");
        game.setInputTestClassName("InputTestClass");
        game.compile();

        try {
            assertNotEquals(InputClass.class , compilation.compileClass("InputClass", "package requirement_t7.model;\n" +
                    "public class InputClass {\n" +
                    "    public static String evenOrOdd(int num) {\n" +
                    "        if (num % 2 == 0) {\n" +
                    "            return \"even\";\n" +
                    "        } else {\n" +
                    "            return \"odd\";\n" +
                    "        }\n" +
                    "    }\n" +
                    "}"));
        } catch (Exception e) {
        }
    }
}
