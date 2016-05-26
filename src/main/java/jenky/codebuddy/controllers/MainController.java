package jenky.codebuddy.controllers;

import jenky.codebuddy.BusinessLogicController;
import jenky.codebuddy.BusinessLogicDB;
import jenky.codebuddy.models.rest.CompleteResult;
import jenky.codebuddy.modelbuilders.CompleteResultModelBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController //all requests to the "/score" endpoint test
public class MainController {

    String sonarqubeResponse;
    Map<String,String> githubInfoMap;
    private CompleteResult result;
    private BusinessLogicController businessLogicController = new BusinessLogicController();
    private BusinessLogicDB businessLogicDB = new BusinessLogicDB();

    @RequestMapping(value = "/score", method = RequestMethod.POST)
    public void createScoreFromMetrics(@RequestHeader Map<String,String> headers) { //create new completeResultModel on POST request
        sonarqubeResponse = headers.get("sonarquberesponse");
        githubInfoMap = businessLogicController.createGithubUserInfoMap(headers);
        setCompleteResultModel(new CompleteResultModelBuilder(sonarqubeResponse, githubInfoMap).buildCompleteResultModel());
        businessLogicDB.storeCompleteResultModel(getCompleteResultModel());
    }

    @RequestMapping(value = "/score", method = RequestMethod.GET)
    private CompleteResult getCompleteResultModel(){
        return result;
    }

    private void setCompleteResultModel(CompleteResult result){
        this.result = result;
    }

 //all requests to the "/score" endpoint

}