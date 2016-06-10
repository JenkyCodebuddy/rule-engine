package jenky.codebuddy.database.achievement;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.Achievement;

import java.util.List;

/**
 * Specfic methods for the service Achievement. Inherits GenericService interface
 */
public interface AchievementService extends GenericService<Achievement, Integer> {
    public List<Achievement> getAchievements();

    public double getAchievementCountFromUser(int user_id);

    public List<Achievement> getAchievementsFromUser(int user_id);
}
