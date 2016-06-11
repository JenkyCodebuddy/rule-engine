package jenky.codebuddy.services;

import jenky.codebuddy.database.achievement.AchievementService;
import jenky.codebuddy.database.achievement.AchievementServiceImpl;
import jenky.codebuddy.database.authentication.*;
import jenky.codebuddy.database.authentication.AuthenticationService;
import jenky.codebuddy.database.commit.CommitService;
import jenky.codebuddy.database.commit.CommitServiceImpl;
import jenky.codebuddy.database.company.CompanyService;
import jenky.codebuddy.database.company.CompanyServiceImpl;
import jenky.codebuddy.database.item.ItemService;
import jenky.codebuddy.database.item.ItemServiceImpl;
import jenky.codebuddy.database.itemuser.ItemUserService;
import jenky.codebuddy.database.itemuser.ItemUserServiceImpl;
import jenky.codebuddy.database.metric.MetricService;
import jenky.codebuddy.database.metric.MetricServiceImpl;
import jenky.codebuddy.database.project.ProjectService;
import jenky.codebuddy.database.project.ProjectServiceImpl;
import jenky.codebuddy.database.score.ScoreService;
import jenky.codebuddy.database.score.ScoreServiceImpl;
import jenky.codebuddy.database.user.UserService;
import jenky.codebuddy.database.user.UserServiceImpl;
import jenky.codebuddy.database.verification.VerificationService;
import jenky.codebuddy.database.verification.VerificationServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by joost on 10-6-2016.
 */
public class DatabaseFactory {

    private static AchievementService achievementService;
    private static jenky.codebuddy.database.authentication.AuthenticationService authenticationService;
    private static CommitService commitService;
    private static CompanyService companyService;
    private static ItemService itemService;
    private static ItemUserService itemUserService;
    private static MetricService metricService;
    private static ProjectService projectService;
    private static ScoreService scoreService;
    private static VerificationService verificationService;
    private static UserService userService;
    private static ApplicationContext context;

    private DatabaseFactory() {
    }

    static{
        context = new ClassPathXmlApplicationContext("spring.xml");
        achievementService = (AchievementServiceImpl) context.getBean("achievementServiceImpl");
        authenticationService = (AuthenticationServiceImpl) context.getBean("authenticationServiceImpl");
        commitService = (CommitServiceImpl) context.getBean("commitServiceImpl");
        companyService = (CompanyServiceImpl) context.getBean("companyServiceImpl");
        itemService = (ItemServiceImpl) context.getBean("itemServiceImpl");
        itemUserService = (ItemUserServiceImpl) context.getBean("itemUserServiceImpl");
        metricService = (MetricServiceImpl) context.getBean("metricServiceImpl");
        projectService = (ProjectServiceImpl) context.getBean("projectServiceImpl");
        scoreService = (ScoreServiceImpl) context.getBean("scoreServiceImpl");
        verificationService = (VerificationServiceImpl) context.getBean("verificationServiceImpl");
        userService = (UserServiceImpl) context.getBean("userServiceImpl");
    }

    public static AchievementService getAchievementService() {
        return achievementService;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public static CommitService getCommitService() {
        return commitService;
    }

    public static CompanyService getCompanyService() {
        return companyService;
    }

    public static ItemService getItemService() {
        return itemService;
    }

    public static ItemUserService getItemUserService() {
        return itemUserService;
    }

    public static MetricService getMetricService() {
        return metricService;
    }

    public static ProjectService getProjectService() {
        return projectService;
    }

    public static ScoreService getScoreService() {
        return scoreService;
    }

    public static VerificationService getVerificationService() {
        return verificationService;
    }

    public static UserService getUserService() {
        return userService;
    }
}
