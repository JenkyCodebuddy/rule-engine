package jenky.codebuddy.database.achievement;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.Achievement;

import java.util.List;

/**
 * Specific methods for achievement. This interface inherits GenericDao interface
 */

public interface AchievementDao extends GenericDao<Achievement, Integer> {
    /**
     * @return List<Achievement>
     */
    public List<Achievement> getAchievements();

    /**
     * @param user_id id of the user
     * @return int
     */
    public double getAchievementCountFromUser(int user_id);

    /**
     * @param user_id id of the user
     * @return
     */
    public List<Achievement> getAchievementsFromUser(int user_id);
}
