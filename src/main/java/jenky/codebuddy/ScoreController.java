package jenky.codebuddy;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/score")
public class ScoreController {
    private ScoreModel score;
    @RequestMapping(method = RequestMethod.POST)
    public ScoreModel createScoreFromMetrics() { //create new ScoreModel using the generated score on POST request
        processJSON process = new processJSON();
        score = new ScoreModel(process.getScore().getLinesOfCodeScore());
        setScore(score);
        return score;
    }


    @RequestMapping(method = RequestMethod.GET) //return the ScoreModel on GET request, only returns something if POST has been made beforehand
    public ScoreModel retrieveScore() {
        return getScore();
    }

    private void setScore(ScoreModel score) { //get and set for ScoreModel
        this.score = score;
    }

    private ScoreModel getScore() {
        return score;
    }
}