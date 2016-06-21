package jenky.codebuddy.services.achievementchecker.achievements.commitachievements;

import jenky.codebuddy.services.achievementchecker.achievements.AchievementChecker;
import jenky.codebuddy.services.achievementchecker.achievements.commitachievements.ach.HundredCommitAchievement;
import jenky.codebuddy.services.achievementchecker.achievements.commitachievements.ach.TenCommitAchievement;
import jenky.codebuddy.services.achievementchecker.achievements.commitachievements.ach.ThousandCommitAchievement;

/**
 * Created by Calamity on 21-6-2016.
 */
public class CommitChecker implements AchievementChecker {

    public void check(String identifier) {
        CommitAchievement tenCommitAchievement = new TenCommitAchievement();
        tenCommitAchievement.check(identifier);
        CommitAchievement hundredCommitAchievement = new HundredCommitAchievement();
        hundredCommitAchievement.check(identifier);
        CommitAchievement thousandCommitAchievement = new ThousandCommitAchievement();
        thousandCommitAchievement.check(identifier);
    }
}
