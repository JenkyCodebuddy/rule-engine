package jenky.codebuddy.controllers;

import jenky.codebuddy.models.restModels.CompleteResult;
import jenky.codebuddy.restModelBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(value = "/score") //all requests to the "/score" endpoint 
public class ScoreController {
    String sonarqubeResponse;
    Map<String,String> githubInfoMap;
    private CompleteResult result;
    @RequestMapping(method = RequestMethod.POST)
    public void createScoreFromMetrics(@RequestHeader Map<String,String> headers) { //create new completeResultModel on POST request
        sonarqubeResponse = headers.get("sonarquberesponse");
        githubInfoMap = createGithubUserInfoMap(headers);
        setCompleteResultModel(new restModelBuilder(sonarqubeResponse, githubInfoMap).buildCompleteResultModel()); //build completeResultModel in restModelBuilder.class
    }

    @RequestMapping(method = RequestMethod.GET) //return the completeResultModel on GET request, only returns something if POST has been made beforehand
    public CompleteResult retrieveCompleteResultModel() {
        return getCompleteResultModel();
    }

    private CompleteResult getCompleteResultModel(){
        return result;
    }

    private void setCompleteResultModel(CompleteResult result){
        this.result = result;
    }

    private void filterMap(Map<String,String> headers){

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
}