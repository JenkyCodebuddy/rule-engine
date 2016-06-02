package jenky.codebuddy.controllers;

import jenky.codebuddy.services.SignUpService;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by joost on 3-6-2016.
 */

@RestController
@RequestMapping(value = "/signup")
public class SignUpController {

    @RequestMapping(method = RequestMethod.POST)
    public void signUpNewUser(@RequestHeader Map<String,String> headers){
        SignUpService signUpService = new SignUpService();
        signUpService.signUpNewUser(headers.get("email"),headers.get("password"));
    }
}
