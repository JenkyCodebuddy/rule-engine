package jenky.codebuddy.controllers;

import jenky.codebuddy.models.rest.Response;
import jenky.codebuddy.services.UserSignUpService;
import jenky.codebuddy.services.UserSignUpServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * This endpoints used for when a new user wants to sing up.
 */

@RestController
@RequestMapping(value = "/signup")  //all requests to the "/signup" endpoint
public class SignUpController {

    UserSignUpService userSignUpServiceImpl;

    public SignUpController() {
        setUserSignUpServiceImpl(new UserSignUpServiceImpl());
    }

    /**
     * @param headers contains an email
     * @return 200 or 400 with description
     */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Response signUpNewUser(@RequestHeader Map<String,String> headers){
        return getUserSignUpServiceImpl().signUpNewUser(headers.get("email"));
    }

    /**
     * @param headers contains the verification code
     * @return 200 or 400 with description
     */
    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public @ResponseBody Response checkVerificationCode(@RequestHeader Map<String, String> headers){
        return getUserSignUpServiceImpl().checkVerificationCode(headers.get("verificationcode"), headers.get("password"));  //check verification code supplied by user, and if correct set password
    }

    public UserSignUpService getUserSignUpServiceImpl() {
        return userSignUpServiceImpl;
    }

    public void setUserSignUpServiceImpl(UserSignUpService userSignUpServiceImpl) {
        this.userSignUpServiceImpl = userSignUpServiceImpl;
    }
}
