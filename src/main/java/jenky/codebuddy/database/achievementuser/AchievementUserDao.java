package jenky.codebuddy.database.achievementuser;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.AchievementUser;
import jenky.codebuddy.models.entities.User;

/**
 * Specific methods for achievement. This interface inherits GenericDao interface
 */

public interface AchievementUserDao extends GenericDao<AchievementUser, Integer> {

    public void saveOrUpdate(AchievementUser achievementUser);

    public boolean checkIfAchievementHasBeenGranted(User user, int achievement_id);

    public boolean checkIfAchievementHasBeenCompleted(User user, int achievement_id);

    public AchievementUser getSingleAchievementUser(User user, int achievement_id);

}
