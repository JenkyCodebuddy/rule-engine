package jenky.codebuddy;

import jenky.codebuddy.models.restModels.CompleteResultModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wildtornado.scocalc.objects.Score;

@RestController
@RequestMapping(value = "/score")
public class ScoreController {
    private Score score;
    private CompleteResultModel result;
    restModelBuilder modelbuilder = new restModelBuilder();
    @RequestMapping(method = RequestMethod.POST)
    public void createScoreFromMetrics() { //create new ScoreModel using the generated score on POST request
        setCompleteResultModel(modelbuilder.buildCompleteResultModel());
    }

    @RequestMapping(method = RequestMethod.GET) //return the ScoreModel on GET request, only returns something if POST has been made beforehand
    public CompleteResultModel retrieveCompleteResultModel() {
        return getCompleteResultModel();
    }

    private void setScore(Score score) { //get and set for ScoreModel
        this.score = score;
    }

    private Score getScore() {
        return score;
    }

    private CompleteResultModel getCompleteResultModel(){
        return result;
    }

    private void setCompleteResultModel(CompleteResultModel result){
        this.result = result;
    }
}