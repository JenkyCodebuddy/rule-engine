package jenky.codebuddy.models.rest;

import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.Score;

import java.util.List;

public class SingleProject {

    private List<Score> userScores;
    private List<Item> equippedItems;
    private int responseCode;
    private String responseMessage;

    public SingleProject() {
    }

    public SingleProject(int responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public SingleProject(List<Score> userScores, int responseCode) {
        this.userScores = userScores;
        this.responseCode = responseCode;
    }

    public List<Score> getUserScores() {
        return userScores;
    }

    public void setUserScores(List<Score> userScores) {
        this.userScores = userScores;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}

