package jenky.codebuddy.controllers;

import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.services.AchievementsService;
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
     * and header email, header sha, header branch, header project, header username
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    private void saveScore(@RequestHeader Map<String, String> headers){
        ScoreUserService scoreUserService = new ScoreUserServiceImpl();
        scoreUserService.parseHeaders(headers);

        /**
         * Dit is een voorbeeld van hoe er wordt gechecked wanneer Jenkins de resultaten post
         * naar de rule engine. ALs je de email wilt van een user is dat dus header.get("email")
         * Voor username is dit headers.get("username"). Token is niet mogelijk hier.
         */
        //AchievementsService achievementsService = new AchievementsService();
        //achievementsService.checkForAchievements(headers.get("email"));
    }

}
