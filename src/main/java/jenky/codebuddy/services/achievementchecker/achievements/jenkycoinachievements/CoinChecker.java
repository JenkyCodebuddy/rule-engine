package jenky.codebuddy.services.achievementchecker.achievements.jenkycoinachievements;

import jenky.codebuddy.services.achievementchecker.achievements.AchievementChecker;
import jenky.codebuddy.services.achievementchecker.achievements.jenkycoinachievements.ach.HundredCoinAchievement;
import jenky.codebuddy.services.achievementchecker.achievements.jenkycoinachievements.ach.TenCoinAchievement;
import jenky.codebuddy.services.achievementchecker.achievements.jenkycoinachievements.ach.ThousandCoinAchievement;

/**
 * Created by Calamity on 20-6-2016.
 */
public class CoinChecker implements AchievementChecker {

    public void check(String identifier) {
        CoinAchievement tenCoinAchievement = new TenCoinAchievement();
        tenCoinAchievement.check(identifier);
        CoinAchievement hundredCoinAchievement = new HundredCoinAchievement();
        hundredCoinAchievement.check(identifier);
        CoinAchievement thousandCoinAchievement = new ThousandCoinAchievement();
        thousandCoinAchievement.check(identifier);
    }
}
