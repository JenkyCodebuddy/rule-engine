package jenky.codebuddy.services;

import jenky.codebuddy.database.achievement.AchievementServiceImpl;
import jenky.codebuddy.database.authentication.AuthenticationServiceImpl;
import jenky.codebuddy.models.entities.Achievement;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.rest.Achievements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by joost on 4-6-2016.
 */
public class AchievementsService {

    ApplicationContext context;
    AchievementServiceImpl achievementService;
    AuthenticationServiceImpl authenticationService;

    public AchievementsService() {
        setContext(new ClassPathXmlApplicationContext("spring.xml"));
        setAchievementService((AchievementServiceImpl) getContext().getBean("achievementServiceImpl"));
        setAuthenticationService((AuthenticationServiceImpl) getContext().getBean("authenticationServiceImpl"));
    }

    public Achievements returnAchievements(String token){
        User user = getUserWithToken(token);
        List<Achievement> allAchievements = achievementService.getAchievementsFromUser(user.getUser_id());
        return new Achievements(allAchievements);
    }

    private User getUserWithToken(String token) {
        return getAuthenticationService().getAuthenticationIfTokenExists(token).getUser();
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    public AchievementServiceImpl getAchievementService() {
        return achievementService;
    }

    public void setAchievementService(AchievementServiceImpl achievementService) {
        this.achievementService = achievementService;
    }

    public AuthenticationServiceImpl getAuthenticationService() {
        return authenticationService;
    }

    public void setAuthenticationService(AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }
}
