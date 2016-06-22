package jenky.codebuddy.database.achievementuser;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.AchievementUser;
import jenky.codebuddy.models.entities.User;

/**
 * Specfic methods for the service Achievement. Inherits GenericService interface
 */
public interface AchievementUserService extends GenericService<AchievementUser, Integer> {

    public void saveOrUpdate(AchievementUser achievementUser);

    public boolean checkIfAchievementHasBeenGranted(User user, int achievement_id);

    public boolean checkIfAchievementHasBeenCompleted(User user, int achievement_id);

    public AchievementUser getSingleAchievementUser(User user, int achievement_id);

}
