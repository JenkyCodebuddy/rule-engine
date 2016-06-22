package jenky.codebuddy.database.achievementuser;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.AchievementUser;
import jenky.codebuddy.models.entities.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Persistence for Achievement. This inherits GenericDaoImplementation
 */
@Repository
public class AchievementUserDaoImpl extends GenericDaoImpl<AchievementUser, Integer> implements AchievementUserDao {

    public void saveOrUpdate(AchievementUser achievementUser){
        super.saveOrUpdate(achievementUser);
    }

    @Override
    public boolean checkIfAchievementHasBeenGranted(User user, int achievement_id){
        String hql = "FROM AchievementUser achievement_has_user WHERE achievement_has_user.achievement =:achievement_id and achievement_has_user.user=:user_id";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("achievement_id",achievement_id);
        query.setInteger("user_id",user.getUser_id());
        Optional<AchievementUser> achievementUser = Optional.ofNullable((AchievementUser)query.uniqueResult());
        return achievementUser.isPresent();
    }

    @Override
    public boolean checkIfAchievementHasBeenCompleted(User user, int achievement_id){
        String hql = "FROM AchievementUser achievement_has_user WHERE achievement_has_user.achievement =:achievement_id and achievement_has_user.user=:user_id and achievement_has_user.progress=:progress";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("achievement_id",achievement_id);
        query.setInteger("user_id",user.getUser_id());
        query.setInteger("progress",100);
        Optional<AchievementUser> achievementUser = Optional.ofNullable((AchievementUser)query.uniqueResult());
        return achievementUser.isPresent();
    }

    @Override
    public AchievementUser getSingleAchievementUser(User user, int achievement_id){
        String hql = "FROM AchievementUser achievement_has_user WHERE achievement_has_user.achievement =:achievement_id and achievement_has_user.user=:user_id";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("achievement_id",achievement_id);
        query.setInteger("user_id",user.getUser_id());
        Optional<AchievementUser> result = Optional.ofNullable((AchievementUser) query.uniqueResult());
        return result.get();
    }

}
