package jenky.codebuddy.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by joost on 2-6-2016.
 */
@RestController
@RequestMapping(value = "/testing")
public class Test {
    @RequestMapping(value = "/lol")
    private String test(){
        return "It works!";
    }

}
