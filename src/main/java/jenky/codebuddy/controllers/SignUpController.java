package jenky.codebuddy.controllers;

import jenky.codebuddy.models.rest.Response;
import jenky.codebuddy.services.SignUpService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * This endpoints used for when a new user wants to sing up.
 */

@RestController
@RequestMapping(value = "/signup")  //all requests to the "/signup" endpoint
public class SignUpController {

    SignUpService signUpService;

    public SignUpController() {
        this.signUpService = new SignUpService();
    }

    /**
     * @param headers contains an email
     * @return 200 or 400 with description
     */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Response signUpNewUser(@RequestHeader Map<String,String> headers){
        return signUpService.signUpNewUser(headers.get("email"));
    }

    /**
     * @param headers contains the verification code
     * @return 200 or 400 with description
     */
    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public @ResponseBody Response checkVerificationCode(@RequestHeader Map<String, String> headers){
        return signUpService.checkVerificationCode(headers.get("verificationcode"), headers.get("password"));  //check verification code supplied by user, and if correct set password
    }
}
