package jenky.codebuddy.models.rest;

import jenky.codebuddy.models.entities.Achievement;

import java.util.List;

public class Achievements {

    private List<Achievement> achievements;
    private String error;

    public Achievements() {
    }

    public Achievements(String error) {
        this.error = error;
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
