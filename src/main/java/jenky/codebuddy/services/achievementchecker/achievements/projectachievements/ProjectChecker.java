package jenky.codebuddy.services.achievementchecker.achievements.projectachievements;

import jenky.codebuddy.services.achievementchecker.achievements.AchievementChecker;
import jenky.codebuddy.services.achievementchecker.achievements.projectachievements.ach.FiveProjectAchievement;
import jenky.codebuddy.services.achievementchecker.achievements.projectachievements.ach.OneProjectAchievement;
import jenky.codebuddy.services.achievementchecker.achievements.projectachievements.ach.TenProjectAchievement;

/**
 * Created by Calamity on 21-6-2016.
 */
public class ProjectChecker implements AchievementChecker {

    public void check(String identifier) {
        ProjectAchievement oneProjectAchievement = new OneProjectAchievement();
        oneProjectAchievement.check(identifier);
        ProjectAchievement fiveProjectAchievement = new FiveProjectAchievement();
        fiveProjectAchievement.check(identifier);
        ProjectAchievement tenProjectAchievement = new TenProjectAchievement();
        tenProjectAchievement.check(identifier);
    }
}
