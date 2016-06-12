package jenky.codebuddy.controllers;

import jenky.codebuddy.models.rest.Response;
import jenky.codebuddy.services.LoginService;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * This endpoints is used for login
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    LoginService loginService;

    public LoginController() {
        setLoginService(new LoginService());
    }

    /**
     * @param headers email and password of the user
     * @return response 200 or 400 with description
     */
    @RequestMapping(method = RequestMethod.POST) //
    private Response login(@RequestHeader Map<String,String> headers) {
        return getLoginService().login(headers.get("email"), headers.get("password"));
    }

    private LoginService getLoginService() {
        return loginService;
    }

    private void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
}
