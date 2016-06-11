package jenky.codebuddy.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jenky.codebuddy.modelbuilders.CommitModelBuilder;
import jenky.codebuddy.modelbuilders.ScoreModelBuilder;
import jenky.codebuddy.models.gson.SonarResponse;
import jenky.codebuddy.services.ScoreUserService;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
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
     * and header email, header sha, header branh, header project, username
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    private void saveScore(@RequestHeader Map<String, String> headers){
        ScoreUserService scoreUserService = new ScoreUserService();
        scoreUserService.parseResponse(headers);
    }
}
