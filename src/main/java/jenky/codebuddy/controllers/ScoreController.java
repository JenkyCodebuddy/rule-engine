package jenky.codebuddy.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jenky.codebuddy.BusinessLogicController;
import jenky.codebuddy.BusinessLogicDB;
import jenky.codebuddy.modelbuilders.CommitModelBuilder;
import jenky.codebuddy.modelbuilders.ScoreModelBuilder;
import jenky.codebuddy.models.gson.SonarResponse;
import jenky.codebuddy.services.SaveScoreService;
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
    private BusinessLogicController businessLogicController;
    private BusinessLogicDB businessLogicDB;

    public ScoreController(){
        setBusinessLogicDB(new BusinessLogicDB());
        setBusinessLogicController(new BusinessLogicController());
    }


    @RequestMapping(method = RequestMethod.POST)
    public String createScoreFromMetrics(@RequestHeader Map<String,String> headers) { //create new completeResultModel on POST request
        return "test";
    }

    @RequestMapping(method = RequestMethod.GET)
    private void returnModel(){
    }

    @RequestMapping(value = "/testdb", method = RequestMethod.POST)
    private void test(@RequestHeader Map<String, String> headers){
        Gson gson = new Gson();
        String sonarqubeResponse = headers.get("sonarquberesponse");
        Type test = new TypeToken<List<SonarResponse>>(){}.getType();
        List<SonarResponse> sonarResponseList = gson.fromJson(sonarqubeResponse, test);
        SonarResponse sonarResponse = sonarResponseList.get(0);
        ScoreModelBuilder scoreModelBuilder = new ScoreModelBuilder(sonarResponse);
        githubInfoMap = getBusinessLogicController().createGithubUserInfoMap(headers);
        CommitModelBuilder commitModelBuilder = new CommitModelBuilder(githubInfoMap);

        SaveScoreService saveScoreService = new SaveScoreService(scoreModelBuilder.getScoreModel(), sonarResponse, commitModelBuilder.getUserCommitModel());
    }

    public Map<String, String> getGithubInfoMap() {
        return githubInfoMap;
    }

    public void setGithubInfoMap(Map<String, String> githubInfoMap) {
        this.githubInfoMap = githubInfoMap;
    }

    public BusinessLogicController getBusinessLogicController() {
        return businessLogicController;
    }

    public void setBusinessLogicController(BusinessLogicController businessLogicController) {
        this.businessLogicController = businessLogicController;
    }

    public BusinessLogicDB getBusinessLogicDB() {
        return businessLogicDB;
    }

    public void setBusinessLogicDB(BusinessLogicDB businessLogicDB) {
        this.businessLogicDB = businessLogicDB;
    }
}
