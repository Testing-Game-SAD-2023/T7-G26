package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import requirement_t7.model.Game;

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
    public void testFileInputTestClassNameIncorrectly(){
        game.setInputTestClassName("IncorrectName");
        assertThrows(RuntimeException.class, () -> game.compile());
    }

    @Test()
    public void testFileInputClassNameIncorrectly(){
        game.setInputClassName("IncorrectName");
        assertThrows(RuntimeException.class, () -> game.compile());
    }

    @Test
    public void testCompileCorrectly(){
        assertEquals("Compiled", game.compile() );
    }

    @Test
    public void testErrorInCompilation(){
        game.setInputTestClassName("TestingFile");
        assertTrue(game.compile().contains("Error in compiling"));
    }

    @Test
    public void testErrorInCompilation2(){
        game.setInputClassName("TestingFile");
        assertTrue(game.compile().contains("Error in compiling"));
    }

    @Test
    public void testExecuteNotCompiled(){
        assertEquals("Cannot execute because you have not compiled", game.execute());
    }

    @Test
    public void testExecuteCorrectly(){
        game.compile();
        assertNotEquals("Cannot execute because you have not compiled", game.execute());
    }
}
