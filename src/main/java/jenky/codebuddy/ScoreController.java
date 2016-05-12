package jenky.codebuddy;

import jenky.codebuddy.models.ResultModel;
import jenky.codebuddy.processJSON;
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
        setScore(score);
        return new ResultModel(score);
    }


    @RequestMapping(method = RequestMethod.GET) //return the ScoreModel on GET request, only returns something if POST has been made beforehand
    public Score retrieveScore() {
        return getScore();
    }

    private void setScore(Score score) { //get and set for ScoreModel
        this.score = score;
    }

    private Score getScore() {
        return score;
    }
}