package jenky.codebuddy.database.achievement;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.Achievement;

import java.util.List;

/**
 * Specfic methods for the service Achievement. Inherits GenericService interface
 */
public interface AchievementService extends GenericService<Achievement, Integer> {
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
     * @return Achievement fromt the user
     */
    public List<Achievement> getAchievementsFromUser(int user_id);

    public double getProgressFromAchievement(int achievement_id, int user_id);

    public Achievement findById(int achievement_id);

    public boolean checkIfAchievementExists(int achievement_id);

}
