package jenky.codebuddy.controllers;

/**
 * Created by joost on 17-6-2016.
 */

import jenky.codebuddy.models.rest.Achievements;
import jenky.codebuddy.services.AuthenticationService;
import jenky.codebuddy.services.CloudMessagingService;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * This endpoint is used for google cloud messaging.
 */
@RestController
@RequestMapping(value = "/gcm")
public class CloudMessagingController {

    CloudMessagingService cloudMessagingService;

    public CloudMessagingController() {
        this.cloudMessagingService = new CloudMessagingService();
    }

    /**
     * @param headers Contains the token of the user.
     * @return achievements or "Token is invalid"
     */
    @RequestMapping(method = RequestMethod.GET)
    private Achievements getAllAchievements(@RequestHeader Map<String,String> headers){
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            System.out.println("MEMES");
            return null;
            //return cloudMessagingService.returnAchievements(headers.get("token"));
        }
        else{
            return new Achievements(400,"Token not valid");
        }
    }
}

