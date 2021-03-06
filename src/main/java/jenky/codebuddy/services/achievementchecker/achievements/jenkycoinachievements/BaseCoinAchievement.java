package jenky.codebuddy.services.achievementchecker.achievements.jenkycoinachievements;

import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.services.DatabaseFactory;
import jenky.codebuddy.services.achievementchecker.AchievementUtil;

/**
 * Created by Calamity on 20-6-2016.
 */
public abstract class BaseCoinAchievement implements CoinAchievement {

    public boolean xCoinsAchievement(String email, int achievement_id, int x) {
        AchievementUtil achievementUtil = new AchievementUtil();
        User user = achievementUtil.getEmailUser(email);

        return (!achievementUtil.checkIfAchievementHasBeenCompleted(user, achievement_id)
                && achievementUtil.attemptAchievement(user, achievement_id, getProgress(user, x)));
    }

    protected int getProgress(User user, int x) {
        double amount = DatabaseFactory.getUserService().getJenkyCoinsFromUser(user.getUser_id());
        int val = (int) Math.floor((amount / x) * 100d);
        return val >= 100 ? 100 : val;
    }
}
