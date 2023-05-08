package requirement.t7.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @RequestMapping("/execute")
    public String execute(){
        return "Execute";
    }

    @RequestMapping("/compile")
    public String compile(){
        return "Compile";
    }

    @RequestMapping(value = "/code",method = RequestMethod.POST)
    public String code(@RequestParam String inputCodeClass, @RequestParam String inputCodeTestClass){
        return "Code Class => " + inputCodeClass + "\n Code TestClass => " + inputCodeTestClass;
    }
}
