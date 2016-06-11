package jenky.codebuddy.models.rest;

import java.util.List;

public class SingleProject {

    private List<Object> userScores;
    private int responseCode;
    private String responseMessage;

    public SingleProject() {
    }

    public SingleProject(int responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public SingleProject(List<Object> userScores, int responseCode) {
        this.userScores = userScores;
        this.responseCode = responseCode;
    }

    public List<Object> getUserScores() {
        return userScores;
    }

    public void setUserScores(List<Object> userScores) {
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

