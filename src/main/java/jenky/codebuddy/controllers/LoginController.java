package jenky.codebuddy.controllers;

import jenky.codebuddy.services.LoginService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by joost on 2-6-2016.
 */

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    LoginService loginService = new LoginService();
    public LoginController() {

    }

    @RequestMapping(method = RequestMethod.POST) //
    private String login(@RequestParam(value = "email") String email,
                         @RequestParam(value = "password") String password ){
        return loginService.login(email,password);
    }

}
