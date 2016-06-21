package jenky.codebuddy.controllers;

import jenky.codebuddy.services.ScoreUserService;
import jenky.codebuddy.services.ScoreUserServiceImpl;
import jenky.codebuddy.services.achievementchecker.achievements.AchievementChecker;
import jenky.codebuddy.services.achievementchecker.achievements.commitachievements.CommitChecker;
import jenky.codebuddy.services.achievementchecker.achievements.jenkycoinachievements.CoinChecker;
import jenky.codebuddy.services.achievementchecker.achievements.projectachievements.ProjectChecker;
import jenky.codebuddy.services.achievementchecker.achievements.scoreachievements.ScoreChecker;
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
     * and header email, header sha, header branch, header project, header username
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    private void saveScore(@RequestHeader Map<String, String> headers){
        ScoreUserService scoreUserService = new ScoreUserServiceImpl();
        scoreUserService.parseHeaders(headers);

        checkForAchievements(headers.get("email"));
    }

    private void checkForAchievements(String email) {
        AchievementChecker commitChecker = new CommitChecker();
        commitChecker.check(email);

        AchievementChecker coinChecker = new CoinChecker();
        coinChecker.check(email);

        AchievementChecker projectChecker = new ProjectChecker();
        projectChecker.check(email);

        AchievementChecker scoreChecker = new ScoreChecker();
        scoreChecker.check(email);
    }
}
