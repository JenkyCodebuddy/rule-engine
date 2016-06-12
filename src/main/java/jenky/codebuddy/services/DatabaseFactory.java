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
 * Returns the requested Service
 */
public class DatabaseFactory {

    private static final ApplicationContext context;

    private DatabaseFactory() {
    }

    static{
        context = new ClassPathXmlApplicationContext("spring.xml");
    }

    public static AchievementService getAchievementService() {
        return (AchievementServiceImpl) context.getBean("achievementServiceImpl");
    }

    public static AuthenticationService getAuthenticationService() {
        return (AuthenticationServiceImpl) context.getBean("authenticationServiceImpl");
    }

    public static CommitService getCommitService() {
        return (CommitServiceImpl) context.getBean("commitServiceImpl");
    }

    public static CompanyService getCompanyService() {
        return (CompanyServiceImpl) context.getBean("companyServiceImpl");
    } //etc..

    public static ItemService getItemService() {
        return (ItemServiceImpl) context.getBean("itemServiceImpl");
    }

    public static ItemUserService getItemUserService() {
        return (ItemUserServiceImpl) context.getBean("itemUserServiceImpl");
    }

    public static MetricService getMetricService() {
        return (MetricServiceImpl) context.getBean("metricServiceImpl");
    }

    public static ProjectService getProjectService() {
        return (ProjectServiceImpl) context.getBean("projectServiceImpl");
    }

    public static ScoreService getScoreService() {
        return (ScoreServiceImpl) context.getBean("scoreServiceImpl");
    }

    public static VerificationService getVerificationService() {
        return (VerificationServiceImpl) context.getBean("verificationServiceImpl");
    }

    public static UserService getUserService() {
        return (UserServiceImpl) context.getBean("userServiceImpl");
    }
}
