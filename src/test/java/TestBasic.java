import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;

import requirement_t7.model.Game;

public class TestBasic {
    private Game game;

    @BeforeEach
    public void init(){
        game = new Game();
    }

    //@Test
    //public void testCompileCorrectly(){
    //    assertEquals("Compiled", game.compile() );
    //}

    @Test
    public void testExecuteNotCompiled(){
        assertEquals("Cannot execute because you have not compiled", game.execute());
    }

    //@Test
    //public void testExecuteCorrectly(){
    //    game.compile();
    //    assertNotEquals("Cannot execute because you have not compiled", game.execute());
    //}
}
