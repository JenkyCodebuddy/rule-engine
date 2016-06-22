package jenky.codebuddy.controllers;

/**
 * Created by joost on 17-6-2016.
 */

import jenky.codebuddy.models.rest.Response;
import jenky.codebuddy.services.AuthenticationService;
import jenky.codebuddy.services.MessagingService;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

/**
 * This endpoint is used for google cloud messaging.
 */
@RestController
@RequestMapping(value = "/messaging")
public class MessagingController {

    MessagingService messagingService;

    public MessagingController() {
        this.messagingService = new MessagingService();
    }

    /**
     * @param headers Contains the token of the user.
     * @return achievements or "Token is invalid"
     */
    @RequestMapping(value = "/notifications", method = RequestMethod.POST)
    private Response saveMessagingTokenForUser(@RequestHeader Map<String,String> headers){
        Optional result = Optional.ofNullable(headers.get("token"));
        if(result.isPresent()) {
            if (AuthenticationService.checkIfTokenIsValid(headers.get("token"))) {
                return messagingService.saveMessagingTokenForUser(headers.get("token"), headers.get("messagingtoken"));
            } else {
                return null;
            }
        }
        else{
            return new Response(200,"No auth token supplied");
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    private Response deleteMessagingTokenForUser(@RequestHeader Map<String,String> headers){
        if(AuthenticationService.checkIfTokenIsValid(headers.get("token"))){
            return messagingService.deleteMessagingTokenForUser(headers.get("token"));
        }
        else{
            return new Response(400,"Token not valid");
        }
    }
}

