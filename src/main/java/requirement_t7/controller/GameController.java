package requirement_t7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import requirement_t7.model.Game;

@RestController
public class GameController {

    @Autowired
    private Game game;

    @PostMapping(value = "/execute")
    public String execute(){
        return game.execute();
    }

    @PostMapping(value = "/compile")
    public String compile(){
        game.setInputClassName("InputClass");
        game.setInputTestClassName("InputTestClass");
        return game.compile();
    }
}
