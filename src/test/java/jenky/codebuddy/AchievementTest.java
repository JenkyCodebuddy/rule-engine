package jenky.codebuddy;

import jenky.codebuddy.database.achievement.AchievementServiceImpl;
import jenky.codebuddy.models.entities.Achievement;
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


    private ApplicationContext context;
    AchievementServiceImpl achievementService;

    public AchievementTest(){
        this.context = (new ClassPathXmlApplicationContext("spring.xml"));
        this.achievementService = (AchievementServiceImpl) getContext().getBean("achievementServiceImpl");
    }

    @Test
    @Transactional
    public void getAchievements() throws Exception{
        assertNotNull(achievementService.getAchievements());
    }

    @Test
    @Transactional
    public void getAchievementCountFromUser() throws Exception{
        assertNotNull(achievementService.getAchievementCountFromUser(TestInfo.TESTID));
    }

    @Test
    @Transactional
    public void getAchievementFromUser() throws Exception{
        assertSame(Achievement.class, achievementService.getAchievementsFromUser(TestInfo.TESTID));
    }


    public ApplicationContext getContext() {
        return context;
    }
}
