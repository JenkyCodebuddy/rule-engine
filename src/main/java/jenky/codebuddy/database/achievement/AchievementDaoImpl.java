package jenky.codebuddy.database.achievement;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.Achievement;
import jenky.codebuddy.models.entities.Commit;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Persistence for Achievement. This inherits GenericDaoImplementation
 */
@Repository
public class AchievementDaoImpl extends GenericDaoImpl<Achievement, Integer> implements AchievementDao {

    /**
     * Get all the achievements
     * @return List containing achievement objects
     */
    @Override
    public List<Achievement> getAchievements() {
        List<Achievement> achievements = super.findAll();
        return achievements;
    }
}
