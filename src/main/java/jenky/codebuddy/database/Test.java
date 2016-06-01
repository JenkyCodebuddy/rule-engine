package jenky.codebuddy.database;

import jenky.codebuddy.database.achievement.AchievementServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by joost on 31-5-2016.
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        AchievementServiceImpl achievementService =  (AchievementServiceImpl) context.getBean("achievementServiceImpl");
        achievementService.getAchievements();
    }
}
