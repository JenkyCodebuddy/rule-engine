package jenky.codebuddy.services.achievementchecker.achievements.projectachievements.ach;

import jenky.codebuddy.services.achievementchecker.achievements.projectachievements.BaseProjectAchievement;

/**
 * Created by Calamity on 20-6-2016.
 */
public class FiveProjectAchievement extends BaseProjectAchievement {

    public boolean check(String email) {
        int achievement_id = 8;
        int x = 5;
        return xProjectsAchievement(email, achievement_id, x);
    }
}
