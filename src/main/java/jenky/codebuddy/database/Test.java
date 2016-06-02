package jenky.codebuddy.database;

import jenky.codebuddy.database.achievement.AchievementServiceImpl;
import jenky.codebuddy.database.authentication.AuthenticationServiceImpl;
import jenky.codebuddy.models.entities.Authentication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by joost on 31-5-2016.
 */
public class Test {
    public static void main(String[] args) {
        ServiceImplFactory serviceImplFactory = new ServiceImplFactory();
        AuthenticationServiceImpl authenticationService = (AuthenticationServiceImpl) serviceImplFactory.getServiceImpl("authenticationServiceImpl");
        authenticationService.getAuthenticationIfTokenExists("adasd");
    }
}
