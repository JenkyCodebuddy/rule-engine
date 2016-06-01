package jenky.codebuddy.database.achievement;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.Achievement;
import jenky.codebuddy.models.entities.Commit;

import java.util.List;

/**
 * Specific methods for achievement. This interface inherits GenericDao interface
 */

public interface AchievementDao extends GenericDao<Achievement, Integer> {
    public List<Achievement> getAchievements();
}
