package jenky.codebuddy.services.achievementchecker.achievements.scoreachievements;

import jenky.codebuddy.services.achievementchecker.achievements.AchievementChecker;
import jenky.codebuddy.services.achievementchecker.achievements.scoreachievements.ach.MillionScoreAchievement;

/**
 * Created by Calamity on 21-6-2016.
 */
public class ScoreChecker implements AchievementChecker {

    public void check(String identifier) {
        ScoreAchievement millionScoreAchievement = new MillionScoreAchievement();
        millionScoreAchievement.check(identifier);
    }
}
