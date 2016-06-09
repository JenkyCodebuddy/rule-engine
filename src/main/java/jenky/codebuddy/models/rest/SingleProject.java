package jenky.codebuddy.models.rest;

import jenky.codebuddy.models.entities.Score;
import jenky.codebuddy.models.entities.User;

import java.util.List;

/**
 * Created by joost on 9-6-2016.
 */
public class SingleProject {

    private List<Score> userScores;
    private String error;

    public SingleProject() {
    }

    public SingleProject(String error) {
        this.error = error;
    }

    public SingleProject(List<Score> userScores) {
        this.userScores = userScores;
    }

    public List<Score> getUserScores() {
        return userScores;
    }

    public void setUserScores(List<Score> userScores) {
        this.userScores = userScores;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
