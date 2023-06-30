package requirement_t7.controller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import requirement_t7.T7Application;
import requirement_t7.model.Game;

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

        verify(game, times(1)).setInputClassName("InputClass");
        verify(game, times(1)).setInputTestClassName("InputTestClass");
        verify(game, times(1)).compile();
    }
}
