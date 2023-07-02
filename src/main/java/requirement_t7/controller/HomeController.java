package requirement_t7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import requirement_t7.model.util.Logger;

/**
 * This class is the controller of the game.
 *
 * @author Pedro Zahonero Mangas
 * @author Pablo Garcia Fernandez
 * @version 1.0
 * @since 2023-04-17
 */
@Controller
public class HomeController {

    /**
     * The home page
     * @return The home page
     */
    @RequestMapping(method = RequestMethod.GET, path = "/")
    public String index() {
        Logger.getInstance().log(Logger.RUNNING,"Class: HomeController.java, method: index()");
        return "home";
    }
}