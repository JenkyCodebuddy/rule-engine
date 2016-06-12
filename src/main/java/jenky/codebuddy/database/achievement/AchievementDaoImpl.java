package jenky.codebuddy.database.achievement;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.Achievement;
import org.hibernate.Query;
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

    /**
     * @param user_id id of the user
     * @return int
     */
    @Override
    public double getAchievementCountFromUser(int user_id){
        String hql = "SELECT COUNT(achievement_has_users.user) FROM Achievement achievement " +
                "INNER JOIN achievement.achievementusers as achievement_has_users " +
                "WHERE achievement_has_users.user =:user_id AND achievement_has_users.progress = 100";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("user_id",user_id);
        long result = (long) query.uniqueResult();
        return result;
    }

    /**
     *
     * @param user_id id of the user
     * @return List<Achievement>
     */
    @Override
    public List<Achievement> getAchievementsFromUser(int user_id){
        String hql = "FROM Achievement achievement " +
                "LEFT JOIN FETCH achievement.achievementusers as achievement_has_users " +
                "WHERE achievement_has_users.user =:user_id AND achievement_has_users.progress = 100";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("user_id",user_id);
        List<Achievement> achievements = query.list();
        return achievements;
    }
}
