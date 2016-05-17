package jenky.codebuddy.controllers;

import jenky.codebuddy.models.restModels.CompleteResultModel;
import jenky.codebuddy.restModelBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wildtornado.scocalc.objects.Score;

@RestController
@RequestMapping(value = "/score") //all requests to the "/score" endpoint
public class ScoreController {

    private CompleteResultModel result;
    @RequestMapping(method = RequestMethod.POST)
    public void createScoreFromMetrics() { //create new completeResultModel on POST request
        setCompleteResultModel(new restModelBuilder().buildCompleteResultModel()); //build completeResultModel in restModelBuilder.class
    }

    @RequestMapping(method = RequestMethod.GET) //return the completeResultModel on GET request, only returns something if POST has been made beforehand
    public CompleteResultModel retrieveCompleteResultModel() {
        return getCompleteResultModel();
    }

    private CompleteResultModel getCompleteResultModel(){
        return result;
    }

    private void setCompleteResultModel(CompleteResultModel result){
        this.result = result;
    }
}