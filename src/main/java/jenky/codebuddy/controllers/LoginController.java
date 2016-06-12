package jenky.codebuddy.controllers;

import jenky.codebuddy.models.rest.Response;
import jenky.codebuddy.services.UserLoginService;
import jenky.codebuddy.services.UserLoginServiceImpl;
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

    UserLoginService userLoginServiceImpl;

    public LoginController() {
        setUserLoginServiceImpl(new UserLoginServiceImpl());
    }

    /**
     * @param headers email and password of the user
     * @return response 200 or 400 with description
     */
    @RequestMapping(method = RequestMethod.POST) //
    private Response login(@RequestHeader Map<String,String> headers) {
        return getUserLoginServiceImpl().login(headers.get("email"), headers.get("password"));
    }

    public UserLoginService getUserLoginServiceImpl() {
        return userLoginServiceImpl;
    }

    public void setUserLoginServiceImpl(UserLoginService userLoginServiceImpl) {
        this.userLoginServiceImpl = userLoginServiceImpl;
    }
}
