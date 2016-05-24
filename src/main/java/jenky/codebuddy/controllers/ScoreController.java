package jenky.codebuddy.controllers;

import jenky.codebuddy.dao.DatabaseService;
import jenky.codebuddy.dao.DatabaseServiceFactory;
import jenky.codebuddy.models.restModels.CompleteResult;
import jenky.codebuddy.restModelBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController //all requests to the "/score" endpoint
public class ScoreController {
    String sonarqubeResponse;
    Map<String,String> githubInfoMap;
    private CompleteResult result;
    DatabaseServiceFactory factory = new DatabaseServiceFactory();
    @RequestMapping(value = "/score", method = RequestMethod.POST)
    public void createScoreFromMetrics(@RequestHeader Map<String,String> headers) { //create new completeResultModel on POST request
        sonarqubeResponse = headers.get("sonarquberesponse");
        githubInfoMap = createGithubUserInfoMap(headers);
        setCompleteResultModel(new restModelBuilder(sonarqubeResponse, githubInfoMap).buildCompleteResultModel());
        storeCompleteResultModelInDb(getCompleteResultModel());

    }

    @RequestMapping(method = RequestMethod.GET) //return the completeResultModel on GET request, only returns something if POST has been made beforehand
    public CompleteResult retrieveCompleteResultModel() {
        return getCompleteResultModel();
    }

    /*@RequestMapping(value = "/hibernate", method = RequestMethod.POST)
    public String putShitInDB(){
        String test = "Test";
        return test;
    }*/

    private CompleteResult getCompleteResultModel(){
        return result;
    }

    private void setCompleteResultModel(CompleteResult result){
        this.result = result;
    }

    private Map<String, String> createGithubUserInfoMap(Map<String, String> headers){
        githubInfoMap = new HashMap<String, String>();
        githubInfoMap.put("username",headers.get("username"));
        githubInfoMap.put("email", headers.get("email"));
        githubInfoMap.put("branch", headers.get("branch"));
        githubInfoMap.put("sha", headers.get("sha"));
        githubInfoMap.put("projectName", headers.get("projectname"));
        return githubInfoMap;
    }

    private void storeCompleteResultModelInDb(CompleteResult completeResult){
        DatabaseService userService = factory.getDatabaseService("User");
    }
 //all requests to the "/score" endpoint

}