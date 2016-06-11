package jenky.codebuddy.controllers;

import jenky.codebuddy.models.rest.Profile;
import jenky.codebuddy.services.AuthenticationService;
import jenky.codebuddy.services.ProfileService;
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

    ProfileService profileService;

    public ProfileController() {
        setProfileService(new ProfileService());
    }

    /**
     * @param headers token of the user
     * @return profile or "Token is invalid"
     */
    @RequestMapping(method = RequestMethod.GET)
    private Profile getProfile(@RequestHeader Map<String,String> headers) {
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return getProfileService().returnProfile(headers.get("token"));
        }
        else{
            return new Profile(400, "Token is invalid");
        }
    }

    public ProfileService getProfileService() {
        return profileService;
    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }
}
