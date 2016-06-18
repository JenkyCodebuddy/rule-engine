package jenky.codebuddy.controllers;

import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.services.DatabaseFactory;
import jenky.codebuddy.services.ScoreUserService;
import jenky.codebuddy.services.ScoreUserServiceImpl;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
        System.out.println(headers.get("buildresult"));
        ScoreUserService scoreUserService = new ScoreUserServiceImpl();
        scoreUserService.parseHeaders(headers);
    }

//    @RequestMapping(value = "/tips", method = RequestMethod.GET)
//    private void tips(@RequestHeader Map<String, String> headers){
//        ScoreUserService scoreUserService = new ScoreUserServiceImpl();
//        scoreUserService.generateTips();
//    }
}
