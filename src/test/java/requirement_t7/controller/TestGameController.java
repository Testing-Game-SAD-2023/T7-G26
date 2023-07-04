package requirement_t7.controller;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import requirement_t7.T7Application;
import requirement_t7.model.Game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitConfig
@SpringBootTest(classes = T7Application.class)
@AutoConfigureMockMvc
public class TestGameController{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Game game;

    private GameController gameController;

    @Test
    void testExecuteEndpoint() throws Exception {
        when(game.execute()).thenReturn("Execution Result");

        mockMvc.perform(post("/execute")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Execution Result"));

        verify(game, times(1)).execute();
    }

    @Test
    void testCompileEndpoint() throws Exception {
        when(game.compile()).thenReturn("Compilation Result");

        mockMvc.perform(post("/compile")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Compilation Result"));
        verify(game, times(1)).compile();
    }

    @ParameterizedTest
    @ValueSource(strings = { "ValidClassName", "AnotherValidClassName123" })
    void testIsValidInputClassName_ValidInput_ReturnsTrue(String className) {
        gameController = new GameController(new Game());
        // Act
        boolean isValid = gameController.isValidInputClassName(className);

        // Assert
        assertFalse(isValid);
    }

    @ParameterizedTest
    @ValueSource(strings = { "Invalid/ClassName", "ClassName.With.Dots", "ClassName\"WithQuotes\"" })
    void testIsValidInputClassName_InvalidInput_ReturnsFalse(String className) {
        gameController = new GameController(new Game());
        // Act
        boolean isValid = gameController.isValidInputClassName(className);

        // Assert
        assertTrue(isValid);
    }
}
