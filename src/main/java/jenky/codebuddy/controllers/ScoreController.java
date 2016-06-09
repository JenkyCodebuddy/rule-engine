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


@RestController
@RequestMapping(value = "/score")
public class ScoreController {

    Map<String,String> githubInfoMap;


    public ScoreController(){

    }


    @RequestMapping(method = RequestMethod.GET)
    private void returnModel(){
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    private void saveScore(@RequestHeader Map<String, String> headers){
        ScoreUserService scoreUserService = new ScoreUserService();
        Gson gson = new Gson();
        String sonarqubeResponse = headers.get("sonarquberesponse");
        Type sonar = new TypeToken<List<SonarResponse>>(){}.getType();
        System.out.println(sonarqubeResponse.replaceAll("\\s",""));
        List<SonarResponse> sonarResponseList = gson.fromJson(sonarqubeResponse.replaceAll("\\s",""), sonar);
        SonarResponse sonarResponse = sonarResponseList.get(0);
        System.out.println("headers = " + headers);
        githubInfoMap = scoreUserService.createGithubUserInfoMap(headers);
        CommitModelBuilder commitModelBuilder = new CommitModelBuilder(githubInfoMap);

        ScoreModelBuilder scoreModelBuilder = new ScoreModelBuilder(sonarResponse, commitModelBuilder.getUserCommitModel());
        
        scoreUserService = new ScoreUserService(scoreModelBuilder.getScoreModel(), sonarResponse, commitModelBuilder.getUserCommitModel());
    }

    public Map<String, String> getGithubInfoMap() {
        return githubInfoMap;
    }

    public void setGithubInfoMap(Map<String, String> githubInfoMap) {
        this.githubInfoMap = githubInfoMap;
    }


}
