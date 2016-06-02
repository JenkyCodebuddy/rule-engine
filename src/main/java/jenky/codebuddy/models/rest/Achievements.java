package jenky.codebuddy.models.rest;

import jenky.codebuddy.models.entities.Achievement;

import java.util.List;

public class Achievements {

    private List<Achievement> achievements;

    public Achievements() {
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
    }
}
