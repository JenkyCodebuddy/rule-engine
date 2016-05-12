package jenky.codebuddy;

import jenky.codebuddy.models.ResultModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wildtornado.scocalc.objects.Score;

@RestController
@RequestMapping(value = "/score")
public class ScoreController {
    private Score score;
    private ResultModel result;
    @RequestMapping(method = RequestMethod.POST)
    public ResultModel createScoreFromMetrics() { //create new ScoreModel using the generated score on POST request
        processJSON process = new processJSON();
        score = process.getScore();
        result = new ResultModel(score);
        setResult(result);
        return result;
    }


    @RequestMapping(method = RequestMethod.GET) //return the ScoreModel on GET request, only returns something if POST has been made beforehand
    public ResultModel retrieveResult() {
        return getResult();
    }

    private void setScore(Score score) { //get and set for ScoreModel
        this.score = score;
    }

    private Score getScore() {
        return score;
    }

    private ResultModel getResult(){
        return result;
    }

    private void setResult(ResultModel result){
        this.result = result;
    }
}