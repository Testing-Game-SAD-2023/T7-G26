package requirement_t7.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import requirement_t7.model.Game;
import requirement_t7.model.util.Logger;

/**
 * This class is the controller of the task Compilation-Execution.
 *
 * @author Pedro Zahonero Mangas
 * @author Pablo Garcia Fernandez
 * @version 1.0
 * @since 2023-04-17
 */
@RestController
public class GameController {

    private final Game game;

    public GameController(Game game) {
        this.game = game;
    }

    /**
     * Executes the test class
     * @return Result of the execution
     */
    @PostMapping(value = "/execute")
    public String execute(){
        Logger.getInstance().log(Logger.RUNNING,"Class: GameController.java, method: execute()");
        return game.execute();
    }

    /**
     * Compiles the classes of the game
     *
     * @param inputClassName The name of the txt of the class to test
     * @param inputTestClassName The name of the txt of the test class
     * @return The result of the compilation
     */
    @PostMapping(value = "/compile")
    public String compile(@RequestParam(value="inputClassName", defaultValue = "InputClass") String inputClassName,
                          @RequestParam(value="inputTestClassName", defaultValue = "InputTestClass") String inputTestClassName) {
        Logger.getInstance().log(Logger.RUNNING, "Class: GameController.java, method: compile()");
        if (isValidInputClassName(inputClassName)) {
            return "Invalid input class name";
        }

        if (isValidInputClassName(inputTestClassName)) {
            return "Invalid input test class name";
        }
        game.setInputClassName(inputClassName);
        game.setInputTestClassName(inputTestClassName);
        return game.compile();
    }

    /**
     * Validates the name of the class in order to avoid vulnerabilities
     * @param inputClassName Name of the class to validate
     * @return true if it is valid
     */
    public boolean isValidInputClassName(String inputClassName) {
        return inputClassName.contains("/") || inputClassName.contains(".") || inputClassName.contains("\"");
    }

}
