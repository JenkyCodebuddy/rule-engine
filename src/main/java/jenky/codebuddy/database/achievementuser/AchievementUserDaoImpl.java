package jenky.codebuddy.database.achievementuser;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.Achievement;
import jenky.codebuddy.models.entities.AchievementUser;
import jenky.codebuddy.services.DatabaseFactory;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Persistence for Achievement. This inherits GenericDaoImplementation
 */
@Repository
public class AchievementUserDaoImpl extends GenericDaoImpl<AchievementUser, Integer> implements AchievementUserDao {

    public void saveOrUpdate(AchievementUser achievementUser){
        super.saveOrUpdate(achievementUser);
    }

}
