package jenky.codebuddy.controllers;

import com.google.gson.JsonObject;
import jenky.codebuddy.models.rest.Response;
import jenky.codebuddy.services.SignUpService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by joost on 3-6-2016.
 */

@RestController
@RequestMapping(value = "/signup")  //all requests to the "/signup" endpoint
public class SignUpController {

    SignUpService signUpService;

    public SignUpController() {
        setSignUpService(new SignUpService());
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Response signUpNewUser(@RequestHeader Map<String,String> headers){ //sign up a new user with an email supplied by user
        return getSignUpService().signUpNewUser(headers.get("email"));
    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public @ResponseBody Response checkVerificationCode(@RequestHeader Map<String, String> headers){
        return getSignUpService().checkVerificationCode(headers.get("verificationcode"), headers.get("password"));  //check verification code supplied by user, and if correct set password
    }

    public SignUpService getSignUpService() {
        return signUpService;
    }

    public void setSignUpService(SignUpService signUpService) {
        this.signUpService = signUpService;
    }
}
