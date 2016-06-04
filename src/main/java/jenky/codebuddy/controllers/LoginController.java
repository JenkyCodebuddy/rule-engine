package jenky.codebuddy.controllers;

import jenky.codebuddy.models.rest.Response;
import jenky.codebuddy.services.LoginService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    private Response login(@RequestHeader Map<String,String> headers) {
        return loginService.login(headers.get("email"), headers.get("password"));
    }

}
