package jenky.codebuddy.database.achievement;

import jenky.codebuddy.database.generic.GenericServiceImpl;
import jenky.codebuddy.models.entities.Achievement;
import jenky.codebuddy.models.entities.Commit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer of Achievement. Inherits GenericService and implements the AchievementService interface
 */
@Service
public class AchievementServiceImpl extends GenericServiceImpl<Achievement, Integer> implements AchievementService {

    private AchievementDao achievementDao;

    /**
     * Injected by Spring
     * @param achievementDao
     */
    @Autowired
    public AchievementServiceImpl(@Qualifier("achievementDaoImpl") AchievementDao achievementDao) {
        this.achievementDao = achievementDao;
    }

    public AchievementServiceImpl(){

    }

    /**
     * Transaction management is handled by Spring.
     * @return list containing achievements
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Achievement> getAchievements(){
        return achievementDao.getAchievements();
    }
}
