package jenky.codebuddy;

import jenky.codebuddy.database.achievement.AchievementServiceImpl;
import jenky.codebuddy.models.entities.Achievement;
import jenky.codebuddy.services.DatabaseFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

/**
 * Created by Fabian on 10-6-2016.
 */
public class AchievementTest {

    @Test
    @Transactional
    public void getAchievements() throws Exception{
        assertNotNull(DatabaseFactory.getAchievementService().getAchievements());
    }

    @Test
    @Transactional
    public void getAchievementCountFromUser() throws Exception{
        assertNotNull(DatabaseFactory.getAchievementService().getAchievementCountFromUser(TestInfo.TESTID));
    }

    @Test
    @Transactional
    public void getAchievementFromUser() throws Exception{
        assertSame(Achievement.class, DatabaseFactory.getAchievementService().getAchievementsFromUser(TestInfo.TESTID).get(0));
    }
}
