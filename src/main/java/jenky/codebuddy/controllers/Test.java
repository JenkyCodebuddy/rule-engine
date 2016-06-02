package jenky.codebuddy.controllers;

import jenky.codebuddy.services.ValidationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by joost on 2-6-2016.
 */
@RestController
@RequestMapping(value = "/testing")
public class Test {
    @RequestMapping(value = "/lol")
    private String test(@RequestParam String token){
        if(ValidationService.checkIfTokenIsValid(token)){
            return "It works!";
        }
        else{
            return "404";
        }
    }

}
