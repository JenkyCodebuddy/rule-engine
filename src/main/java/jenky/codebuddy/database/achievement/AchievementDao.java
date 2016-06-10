package jenky.codebuddy.database.achievement;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.Achievement;

import java.util.List;

/**
 * Specific methods for achievement. This interface inherits GenericDao interface
 */

public interface AchievementDao extends GenericDao<Achievement> {
    public List<Achievement> getAchievements();

    public double getAchievementCountFromUser(int user_id);

    public List<Achievement> getAchievementsFromUser(int user_id);
}
