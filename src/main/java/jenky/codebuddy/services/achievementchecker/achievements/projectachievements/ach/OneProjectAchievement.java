package jenky.codebuddy.services.achievementchecker.achievements.projectachievements.ach;

import jenky.codebuddy.services.achievementchecker.achievements.projectachievements.BaseProjectAchievement;

/**
 * Created by Calamity on 20-6-2016.
 */
public class OneProjectAchievement extends BaseProjectAchievement {

    public boolean check(String email) {
        int achievement_id = 7;
        int x = 1;
        return xProjectsAchievement(email, achievement_id, x);
    }
}
