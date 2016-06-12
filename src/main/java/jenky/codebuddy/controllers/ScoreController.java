package jenky.codebuddy.controllers;

import jenky.codebuddy.services.UserScoreService;
import jenky.codebuddy.services.UserScoreServiceImpl;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * This endpoints is used by the CI server
 * to post the sonar results and commiter information to be saved in the database
 */
@RestController
@RequestMapping(value = "/score")
public class ScoreController {

    /**
     *
     * @param headers must contains a sonarresponse(json)
     * and header email, header sha, header branch, header project, username
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    private void saveScore(@RequestHeader Map<String, String> headers){
        UserScoreService userScoreService = new UserScoreServiceImpl();
        userScoreService.parseHeaders(headers);
    }
}
