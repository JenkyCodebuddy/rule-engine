package jenky.codebuddy.services;

import jenky.codebuddy.database.achievement.AchievementServiceImpl;
import jenky.codebuddy.models.entities.Achievement;
import jenky.codebuddy.models.rest.Achievements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by joost on 4-6-2016.
 */
public class AchievementsService {

    ApplicationContext context;

    public AchievementsService() {
        setContext(new ClassPathXmlApplicationContext("spring.xml"));
    }

    public Achievements getAllAchievements(){
        AchievementServiceImpl achievementService = (AchievementServiceImpl) getContext().getBean("achievementServiceImpl");
        List<Achievement> allAchievements = achievementService.getAchievements();
        Achievements achievements = new Achievements();
        achievements.setAchievements(allAchievements);
        return achievements;
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }
}
