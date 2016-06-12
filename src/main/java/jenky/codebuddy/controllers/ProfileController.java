package jenky.codebuddy.controllers;

import jenky.codebuddy.models.rest.Profile;
import jenky.codebuddy.services.UserAuthenticationService;
import jenky.codebuddy.services.UserProfileService;
import jenky.codebuddy.services.UserProfileServiceImpl;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * This endpoints return the profile information of the user.
 */
@RestController
@RequestMapping(value = "/profile")
public class ProfileController {

    UserProfileService userProfileServiceImpl;

    public ProfileController() {
        this.userProfileServiceImpl = new UserProfileServiceImpl();
    }

    /**
     * @param headers token of the user
     * @return profile or "Token is invalid"
     */
    @RequestMapping(method = RequestMethod.GET)
    private Profile getProfile(@RequestHeader Map<String,String> headers) {
        if(UserAuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return userProfileServiceImpl.returnProfile(headers.get("token"));
        }
        else{
            return new Profile(400, "Token is invalid");
        }
    }
}
