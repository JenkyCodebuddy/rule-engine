package jenky.codebuddy.services.achievementchecker.achievements.commitachievements.ach;

import jenky.codebuddy.services.achievementchecker.achievements.commitachievements.BaseCommitAchievement;

/**
 * Created by Calamity on 20-6-2016.
 */
public class TenCommitAchievement extends BaseCommitAchievement {

    public boolean check(String email) {
        int achievement_id = 1;
        int x = 10;
        return xCommitAchievement(email, achievement_id, x);
    }
}
