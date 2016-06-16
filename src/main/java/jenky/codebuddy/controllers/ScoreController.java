package jenky.codebuddy.controllers;

import jenky.codebuddy.services.ScoreUserService;
import jenky.codebuddy.services.ScoreUserServiceImpl;
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
@RequestMapping(value = "/save")
public class ScoreController {

    /**
     *
     * @param headers must contains a sonarresponse(json)
     * and header email, header sha, header branch, header project, username
     */
    @RequestMapping(value = "/score", method = RequestMethod.POST)
    private void saveCommit(@RequestHeader Map<String, String> headers){
        ScoreUserService scoreUserService = new ScoreUserServiceImpl();
        scoreUserService.parseHeaders(headers);
    }

    @RequestMapping(value = "/json", method = RequestMethod.POST)
    private void saveJson(@RequestHeader Map<String, String> headers){
        ScoreUserService scoreUserService = new ScoreUserServiceImpl();
        System.out.println(headers);
        scoreUserService.parseHeaders(headers);
    }
}
