package jenky.codebuddy.database.achievementuser;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.Achievement;
import jenky.codebuddy.models.entities.AchievementUser;

import java.util.List;

/**
 * Specfic methods for the service Achievement. Inherits GenericService interface
 */
public interface AchievementUserService extends GenericService<AchievementUser, Integer> {

    public void saveOrUpdate(AchievementUser achievementUser);

}
