package jenky.codebuddy.models.rest;

import jenky.codebuddy.models.entities.Achievement;

import java.util.List;

public class Achievements {

    private List<Achievement> achievements;
    private int responseCode;
    private String responseMessage;

    public Achievements() {
    }

    public Achievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }

    public Achievements(int responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
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
