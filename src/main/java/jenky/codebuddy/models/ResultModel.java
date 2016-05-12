package jenky.codebuddy.models;

import wildtornado.scocalc.objects.Score;

/**
 * Created by joost on 12-5-2016.
 */
public class ResultModel {

    private Score score;

    public ResultModel(Score score){
        this.score = score;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
