package jenky.codebuddy.services;

import jenky.codebuddy.database.achievement.AchievementServiceImpl;
import jenky.codebuddy.database.authentication.AuthenticationServiceImpl;
import jenky.codebuddy.database.commit.CommitServiceImpl;
import jenky.codebuddy.database.company.CompanyServiceImpl;
import jenky.codebuddy.database.item.ItemServiceImpl;
import jenky.codebuddy.database.itemuser.ItemUserServiceImpl;
import jenky.codebuddy.database.metric.MetricServiceImpl;
import jenky.codebuddy.database.project.ProjectServiceImpl;
import jenky.codebuddy.database.score.ScoreServiceImpl;
import jenky.codebuddy.database.user.UserServiceImpl;
import jenky.codebuddy.database.verification.VerificationServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by joost on 10-6-2016.
 */
public class DatabaseFactory {

    private static AchievementServiceImpl achievementService;
    private static AuthenticationServiceImpl authenticationService;
    private static CommitServiceImpl commitService;
    private static CompanyServiceImpl companyService;
    private static ItemServiceImpl itemService;
    private static ItemUserServiceImpl itemUserService;
    private static MetricServiceImpl metricService;
    private static ProjectServiceImpl projectService;
    private static ScoreServiceImpl scoreService;
    private static VerificationServiceImpl verificationService;
    private static UserServiceImpl userService;
    private static ApplicationContext context;

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

    public static AchievementServiceImpl getAchievementService() {
        return achievementService;
    }

    public static AuthenticationServiceImpl getAuthenticationService() {
        return authenticationService;
    }

    public static CommitServiceImpl getCommitService() {
        return commitService;
    }

    public static CompanyServiceImpl getCompanyService() {
        return companyService;
    }

    public static ItemServiceImpl getItemService() {
        return itemService;
    }

    public static ItemUserServiceImpl getItemUserService() {
        return itemUserService;
    }

    public static MetricServiceImpl getMetricService() {
        return metricService;
    }

    public static ProjectServiceImpl getProjectService() {
        return projectService;
    }

    public static ScoreServiceImpl getScoreService() {
        return scoreService;
    }

    public static VerificationServiceImpl getVerificationService() {
        return verificationService;
    }

    public static UserServiceImpl getUserService() {
        return userService;
    }
}
