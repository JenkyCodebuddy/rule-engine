package jenky.codebuddy.controllers;

import jenky.codebuddy.models.rest.Achievements;
import jenky.codebuddy.services.UserAchievementsService;
import jenky.codebuddy.services.UserAchievementsServiceImpl;
import jenky.codebuddy.services.UserAuthenticationService;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * This endpoints return the achievements that the user has.
 */
@RestController
@RequestMapping(value = "/achievements")
public class AchievementsController {

    UserAchievementsService userAchievementsServiceImpl;

    public AchievementsController() {
        setUserAchievementsServiceImpl(new UserAchievementsServiceImpl());
    }

    /**
     * @param headers Contains the token of the user.
     * @return achievements or "Token is invalid"
     */
    @RequestMapping(method = RequestMethod.GET)
    private Achievements getAllAchievements(@RequestHeader Map<String,String> headers){
        if(UserAuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return getUserAchievementsServiceImpl().returnAchievements(headers.get("token"));
        }
        else{
            return new Achievements(400,"Token not valid");
        }
    }

    public UserAchievementsService getUserAchievementsServiceImpl() {
        return userAchievementsServiceImpl;
    }

    public void setUserAchievementsServiceImpl(UserAchievementsService userAchievementsServiceImpl) {
        this.userAchievementsServiceImpl = userAchievementsServiceImpl;
    }
}
