package jenky.codebuddy.database.achievement;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.Achievement;
import jenky.codebuddy.models.entities.Commit;

import java.util.List;

/**
 * Created by joost on 30-5-2016.
 */
public interface AchievementService extends GenericService<Achievement, Integer> {
    public List<Achievement> getAchievements();
}
