package jenky.codebuddy.services.achievementchecker.achievements.projectachievements.ach;

import jenky.codebuddy.services.achievementchecker.achievements.projectachievements.BaseProjectAchievement;

/**
 * Created by Calamity on 20-6-2016.
 */
public class TenProjectAchievement extends BaseProjectAchievement {

    public boolean check(String email) {
        int achievement_id = 9;
        int x = 10;
        return xProjectsAchievement(email, achievement_id, x);
    }
}
