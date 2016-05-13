package jenky.codebuddy;

import jenky.codebuddy.models.restModels.CommitModel;
import jenky.codebuddy.models.restModels.CompleteResultModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wildtornado.scocalc.objects.Score;

@RestController
@RequestMapping(value = "/score")
public class ScoreController {
    private Score score;
    private CommitModel commitModel;
    private CompleteResultModel result;
    @RequestMapping(method = RequestMethod.POST)
    public CompleteResultModel createScoreFromMetrics() { //create new ScoreModel using the generated score on POST request
        processSonarqubeJson process = new processSonarqubeJson();
        score = process.getScore();
        processGitHubJson github = new processGitHubJson();
        commitModel = github.doEverything();
        result = new CompleteResultModel(score,commitModel);
        setResult(result);
        return result;
    }


    @RequestMapping(method = RequestMethod.GET) //return the ScoreModel on GET request, only returns something if POST has been made beforehand
    public CompleteResultModel retrieveResult() {
        return getResult();
    }

    private void setScore(Score score) { //get and set for ScoreModel
        this.score = score;
    }

    private Score getScore() {
        return score;
    }

    private CompleteResultModel getResult(){
        return result;
    }

    private void setResult(CompleteResultModel result){
        this.result = result;
    }
}