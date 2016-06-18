package jenky.codebuddy.database.achievementuser;

import jenky.codebuddy.database.generic.GenericServiceImpl;
import jenky.codebuddy.models.entities.Achievement;
import jenky.codebuddy.models.entities.AchievementUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer of Achievement. Inherits GenericService and implements the AuthenticationService interface
 */
@Service
public class AchievementUserServiceImpl extends GenericServiceImpl<AchievementUser, Integer> implements AchievementUserService {

    private AchievementUserDao achievementDao;

    /**
     * Injected by Spring
     * @param achievementDao
     */
    @Autowired
    public AchievementUserServiceImpl(@Qualifier("achievementUserDaoImpl") AchievementUserDao achievementDao) {
        this.achievementDao = achievementDao;
    }

    public AchievementUserServiceImpl(){

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdate(AchievementUser achievementUser){
        achievementDao.saveOrUpdate(achievementUser);
    }
}
