package jenky.codebuddy.controllers;

import jenky.codebuddy.models.rest.Achievements;
import jenky.codebuddy.models.rest.Response;
import jenky.codebuddy.services.AchievementsService;
import jenky.codebuddy.services.AuthenticationService;
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

    AchievementsService achievementsService;

    public AchievementsController() {
        this.achievementsService = new AchievementsService();
    }
    /**
     * @param headers Contains the token of the user.
     * @return achievements or "Token is invalid"
     */
    @RequestMapping(method = RequestMethod.GET)
    private Achievements getAllAchievements(@RequestHeader Map<String,String> headers){
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return achievementsService.returnAchievements(headers.get("token"));
        }
        else{
            return new Achievements(400,"Token not valid");
        }
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    private Response achievemtsTest(@RequestHeader Map<String,String> headers){
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return achievementsService.testMethod(headers.get("token"));
        }
        else{
            return new Response(400,"Token not valid");
        }
    }
}
