package jenky.codebuddy.services.achievementchecker;

import jenky.codebuddy.models.entities.AchievementUser;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.services.DatabaseFactory;

/**
 * Created by Calamity on 20-6-2016.
 */
public class AchievementUtil {



    public boolean attemptAchievement(User user, int achievement_id, int progress) {
        if (checkIfAchievementHasAlreadyBeenGranted(user, achievement_id)) {
            AchievementUser achievementUser = getAchievementUser(user, achievement_id);
            return setAchievement(user, achievementUser, achievement_id, progress);
        }
        return setAchievement(user, new AchievementUser(), achievement_id, progress);
    }

    private boolean checkIfAchievementHasAlreadyBeenGranted(User user, int achievement_id) {
        return DatabaseFactory.getAchievementUserService().checkIfAchievementHasBeenGranted(user, achievement_id);
    }

    public boolean checkIfAchievementHasBeenCompleted(User user, int achievement_id) {
        return DatabaseFactory.getAchievementUserService().checkIfAchievementHasBeenCompleted(user, achievement_id);
    }

    private boolean setAchievement(User user, AchievementUser achievementUser, int achievement_id, double progress) {
        achievementUser.setUser(user);
        achievementUser.setProgress(progress);
        if (DatabaseFactory.getAchievementService().checkIfAchievementExists(achievement_id)) {
            achievementUser.setAchievement(DatabaseFactory.getAchievementService().findById(achievement_id));
            DatabaseFactory.getAchievementUserService().saveOrUpdate(achievementUser);
            return true;
        }
        return false;
    }

    private AchievementUser getAchievementUser(User user, int achievement_id) {
        return DatabaseFactory.getAchievementUserService().getSingleAchievementUser(user, achievement_id);
    }

    public User getUser(String token) {
        return DatabaseFactory.getAuthenticationService().getAuthenticationIfTokenExists(token).getUser();
    }

    public User getEmailUser(String email) {
        return DatabaseFactory.getUserService().getUserIfExists(email);
    }
}
