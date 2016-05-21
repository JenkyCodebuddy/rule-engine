package jenky.codebuddy.controllers;

import jenky.codebuddy.models.restModels.CompleteResult;
import jenky.codebuddy.restModelBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/score") //all requests to the "/score" endpoint 
public class ScoreController {

    private CompleteResult result;
    @RequestMapping(method = RequestMethod.POST)
    public void createScoreFromMetrics(@RequestParam Map<String,String> requestParams, @RequestHeader("sonarqubeResponse") String sonarqubeResponse) { //create new completeResultModel on POST request
        setCompleteResultModel(new restModelBuilder(requestParams, sonarqubeResponse).buildCompleteResultModel()); //build completeResultModel in restModelBuilder.class
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
}