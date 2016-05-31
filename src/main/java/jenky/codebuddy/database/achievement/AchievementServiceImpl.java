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
 * Created by joost on 30-5-2016.
 */
@Service
public class AchievementServiceImpl extends GenericServiceImpl<Achievement, Integer> implements AchievementService {

    private AchievementDao achievementDao;

    @Autowired
    public AchievementServiceImpl(@Qualifier("achievementDaoImpl") AchievementDao achievementDao) {
        this.achievementDao = achievementDao;
    }

    public AchievementServiceImpl(){

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Achievement> getAchievements(){
        return achievementDao.getAchievements();
    }
}
