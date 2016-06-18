package jenky.codebuddy.database.achievementuser;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.Achievement;
import jenky.codebuddy.models.entities.AchievementUser;

import java.util.List;

/**
 * Specific methods for achievement. This interface inherits GenericDao interface
 */

public interface AchievementUserDao extends GenericDao<AchievementUser, Integer> {

    public void saveOrUpdate(AchievementUser achievementUser);

}
