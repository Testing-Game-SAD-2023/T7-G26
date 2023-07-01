package requirement_t7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import requirement_t7.model.util.Logger;

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