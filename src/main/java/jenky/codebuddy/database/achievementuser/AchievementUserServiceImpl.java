package jenky.codebuddy.database.achievementuser;

import jenky.codebuddy.database.generic.GenericServiceImpl;
import jenky.codebuddy.models.entities.AchievementUser;
import jenky.codebuddy.models.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service layer of Achievement. Inherits GenericService and implements the AuthenticationService interface
 */
@Service
public class AchievementUserServiceImpl extends GenericServiceImpl<AchievementUser, Integer> implements AchievementUserService {

    private AchievementUserDao achievementUserDao;

    /**
     * Injected by Spring
     * @param achievementUserDao
     */
    @Autowired
    public AchievementUserServiceImpl(@Qualifier("achievementUserDaoImpl") AchievementUserDao achievementUserDao) {
        this.achievementUserDao = achievementUserDao;
    }

    public AchievementUserServiceImpl(){

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdate(AchievementUser achievementUser){
        achievementUserDao.saveOrUpdate(achievementUser);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean checkIfAchievementHasBeenGranted(User user, int achievement_id) {
        return achievementUserDao.checkIfAchievementHasBeenGranted(user, achievement_id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean checkIfAchievementHasBeenCompleted(User user, int achievement_id) {
        return achievementUserDao.checkIfAchievementHasBeenCompleted(user, achievement_id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public AchievementUser getSingleAchievementUser(User user, int achievement_id) {
        return achievementUserDao.getSingleAchievementUser(user, achievement_id);
    }
}
