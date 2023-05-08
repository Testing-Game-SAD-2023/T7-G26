package requirement.t7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import requirement.t7.model.Game;

@RestController
public class Controller {

    @Autowired
    private Game game;

    @RequestMapping(value = "/execute",method = RequestMethod.POST)
    public String execute(){
        return game.execute();
    }

    @RequestMapping(value = "/compile",method = RequestMethod.POST)
    public String compile(){
        return game.compile();
    }
}
