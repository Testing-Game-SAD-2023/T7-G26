package requirement_t7.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import requirement_t7.model.Game;
import requirement_t7.model.util.FileDeletor;

import static org.junit.jupiter.api.Assertions.*;

public class TestGame {
    private Game game;

    @BeforeEach
    public void init(){
        game = new Game();
        game.setInputClassName("InputClass");
        game.setInputTestClassName("InputTestClass");
    }

    @Test()
    void testFileInputTestClassNameIncorrectly(){
        game.setInputTestClassName("IncorrectName");
        assertThrows(RuntimeException.class, () -> game.compile());
    }

    @Test()
    void testFileInputClassNameIncorrectly(){
        game.setInputClassName("IncorrectName");
        assertThrows(RuntimeException.class, () -> game.compile());
    }

    @Test
    void testCompileCorrectly(){
        assertEquals("Compiled", game.compile() );
    }

    @Test
    void testErrorInCompilation(){
        game.setInputTestClassName("TestingFile");
        assertTrue(game.compile().contains("[ERROR]"));
        FileDeletor.deleteFile("src/main/java/requirement_t7/InputClass.java");
        FileDeletor.deleteFile("src/test/java/requirement_t7/TestingFile.java");
    }

    @Test
    void testExecuteNotCompiled(){
        assertEquals("Cannot execute because you have not compiled", game.execute());
    }

    @Test
    void testExecuteCorrectly(){
        game.compile();
        assertNotEquals("Cannot execute because you have not compiled", game.execute());
    }
}
