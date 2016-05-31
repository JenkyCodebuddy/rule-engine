package jenky.codebuddy.database.achievement;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.Achievement;
import jenky.codebuddy.models.entities.Commit;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by joost on 30-5-2016.
 */
@Repository
public class AchievementDaoImpl extends GenericDaoImpl<Achievement, Integer> implements AchievementDao {

    @Override
    public List<Achievement> getAchievements() {
        List<Achievement> achievements = super.findAll();
        return achievements;
    }
}
