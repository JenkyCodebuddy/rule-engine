package jenky.codebuddy.services;

import jenky.codebuddy.database.achievement.AchievementServiceImpl;
import jenky.codebuddy.database.authentication.AuthenticationServiceImpl;
import jenky.codebuddy.models.entities.Achievement;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.rest.Achievements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.jpa.vendor.Database;

import java.util.List;

/**
 * Created by joost on 4-6-2016.
 */
public class AchievementsService {

    public AchievementsService() {
    }

    public Achievements returnAchievements(String token){
        User user = DatabaseFactory.getAuthenticationService().getAuthenticationIfTokenExists(token).getUser();
        List<Achievement> allAchievements = DatabaseFactory.getAchievementService().getAchievementsFromUser(user.getUser_id());
        return new Achievements(allAchievements, 200);
    }
}
