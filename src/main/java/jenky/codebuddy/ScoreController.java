package jenky.codebuddy;

import jenky.codebuddy.models.restModels.CompleteResultModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wildtornado.scocalc.objects.Score;

@RestController
@RequestMapping(value = "/score")
public class ScoreController {

    private CompleteResultModel result;
    @RequestMapping(method = RequestMethod.POST)
    public void createScoreFromMetrics() { //create new completeResultModel on POST request
        setCompleteResultModel(new restModelBuilder().buildCompleteResultModel()); //build completeResultModel in restModelBuilder.class
    }

    @RequestMapping(method = RequestMethod.GET) //return the ScoreModel on GET request, only returns something if POST has been made beforehand
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