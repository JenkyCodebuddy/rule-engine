package jenky.codebuddy.services.achievementchecker.achievements.jenkycoinachievements.ach;

import jenky.codebuddy.services.achievementchecker.achievements.jenkycoinachievements.BaseCoinAchievement;

/**
 * Created by Calamity on 20-6-2016.
 */
public class TenCoinAchievement extends BaseCoinAchievement {

    public boolean check(String email) {
        int achievement_id = 4;
        int x = 10;
        return xCoinsAchievement(email, achievement_id, x);
    }
}
