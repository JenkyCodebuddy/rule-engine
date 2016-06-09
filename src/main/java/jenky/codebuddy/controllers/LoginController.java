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

    LoginService loginService;

    public LoginController() {
        setLoginService(new LoginService());
    }

    @RequestMapping(method = RequestMethod.POST) //
    private Response login(@RequestHeader Map<String,String> headers) {
        return getLoginService().login(headers.get("email"), headers.get("password"));
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
}
