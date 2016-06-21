package jenky.codebuddy.services.achievementchecker.achievements.scoreachievements.ach;

import jenky.codebuddy.services.achievementchecker.achievements.scoreachievements.BaseScoreAchievement;

/**
 * Created by Calamity on 20-6-2016.
 */
public class MillionScoreAchievement extends BaseScoreAchievement {

    public boolean check(String email) {
        int achievement_id = 10;
        int x = 1000000;
        return xScoreAchievement(email, achievement_id, x);
    }
}
