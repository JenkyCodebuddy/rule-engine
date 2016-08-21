package jenky.codebuddy.database.achievement;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.Achievement;
import jenky.codebuddy.services.DatabaseFactory;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
                "WHERE achievement_has_users.user =:user_id";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("user_id",user_id);
        List<Achievement> achievements = query.list();
        for(int i = 0; i < achievements.size(); i++){
            Achievement achievement = achievements.get(i);
            achievement.setProgress(DatabaseFactory.getAchievementService().getProgressFromAchievement(achievement.getId(),user_id));
            achievements.set(i,achievement);
        }
        return achievements;
    }

    @Override
    public double getProgressFromAchievement(int achievement_id, int user_id){
        String hql = "SELECT (achievement_has_users.progress) FROM Achievement achievement " +
                "INNER JOIN achievement.achievementusers as achievement_has_users " +
                "WHERE achievement_has_users.achievement =:achievement_id AND achievement_has_users.user =:user_id";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("achievement_id",achievement_id);
        query.setInteger("user_id",user_id);
        double result = (double) query.uniqueResult();
        System.out.println(result);
        return (double) query.uniqueResult();
    }

    @Override
    public Achievement findById(int achievement_id){
        return super.findById(achievement_id);
    }

    @Override
    public boolean checkIfAchievementExists(int achievement_id){
        String hql = "FROM Achievement achievement where achievement.id=:achievement_id";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("achievement_id",achievement_id);
        Optional<Achievement> achievement = Optional.ofNullable((Achievement)query.uniqueResult());
        return achievement.isPresent();
    }

    @Override
    public void saveAchievement(Achievement achievement){
        super.add(achievement);
    }

    @Override
    public void deleteAchievement(Achievement achievement){
        super.delete(achievement);
    }
}
