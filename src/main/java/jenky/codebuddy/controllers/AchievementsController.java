package jenky.codebuddy.controllers;

import jenky.codebuddy.models.entities.Authentication;
import jenky.codebuddy.models.rest.Achievements;
import jenky.codebuddy.services.AchievementsService;
import jenky.codebuddy.services.AuthenticationService;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by joost on 4-6-2016.
 */

@RestController
@RequestMapping(value = "/achievements")
public class AchievementsController {

    AchievementsService achievementsService;

    public AchievementsController() {
        setAchievementsService(new AchievementsService());
    }

    @RequestMapping(method = RequestMethod.GET)
    private Achievements getAllAchievements(@RequestHeader Map<String,String> headers){
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return achievementsService.getAllAchievements();
        }
        else{
            return new Achievements("Token not valid");
        }
    }

    public AchievementsService getAchievementsService() {
        return achievementsService;
    }

    public void setAchievementsService(AchievementsService achievementsService) {
        this.achievementsService = achievementsService;
    }
}
