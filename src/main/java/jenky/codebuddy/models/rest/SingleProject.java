package jenky.codebuddy.models.rest;

import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.Score;

import java.util.List;

public class SingleProject {

    private List<Score> userScores;
    private List<Item> equippedItems;
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

