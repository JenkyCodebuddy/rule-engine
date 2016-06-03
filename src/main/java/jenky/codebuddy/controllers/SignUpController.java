package jenky.codebuddy.controllers;

import jenky.codebuddy.services.SignUpService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by joost on 3-6-2016.
 */

@RestController
@RequestMapping(value = "/signup")
public class SignUpController {

    SignUpService signUpService;

    public SignUpController() {
        setSignUpService(new SignUpService());
    }

    @RequestMapping(method = RequestMethod.POST)
    public void signUpNewUser(@RequestHeader Map<String,String> headers){
        getSignUpService().signUpNewUser(headers.get("email"));
    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public void checkVerificationCode(@RequestHeader Map<String, String> headers){
        getSignUpService().checkVerificationCode(headers.get("verificationcode"), headers.get("password"));

    }

    public SignUpService getSignUpService() {
        return signUpService;
    }

    public void setSignUpService(SignUpService signUpService) {
        this.signUpService = signUpService;
    }
}
