package jenky.codebuddy.database;

import jenky.codebuddy.database.achievement.AchievementServiceImpl;
import jenky.codebuddy.database.authentication.AuthenticationService;
import jenky.codebuddy.database.authentication.AuthenticationServiceImpl;
import jenky.codebuddy.database.commit.CommitServiceImpl;
import jenky.codebuddy.database.company.CompanyServiceImpl;
import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.database.generic.GenericServiceImpl;
import jenky.codebuddy.database.item.ItemServiceImpl;
import jenky.codebuddy.database.metric.MetricServiceImpl;
import jenky.codebuddy.database.project.ProjectServiceImpl;
import jenky.codebuddy.database.score.ScoreServiceImpl;
import jenky.codebuddy.database.user.UserServiceImpl;
import jenky.codebuddy.database.verification.VerificationServiceImpl;
import jenky.codebuddy.models.entities.Authentication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.Serializable;

/**
 * Created by joost on 2-6-2016.
 */
public class ServiceImplFactory{

    private ApplicationContext context;
    public ServiceImplFactory() {
        setContext(context = new ClassPathXmlApplicationContext("spring.xml"));
    }

    public GenericServiceImpl getServiceImpl(String serviceImplName){
        switch(serviceImplName){
            case "achievementServiceImpl": return (AchievementServiceImpl) getContext().getBean("achievementServiceImpl");
            case "authenticationServiceImpl": return (AuthenticationServiceImpl) getContext().getBean("authenticationServiceImpl");
            case "commitServiceImpl": return (CommitServiceImpl) getContext().getBean("commitServiceImpl");
            case "companyServiceImpl": return (CompanyServiceImpl) getContext().getBean("companyServiceImpl");
            case "itemServiceImpl": return (ItemServiceImpl) getContext().getBean("itemServiceImpl");
            case "metricServiceImpl": return (MetricServiceImpl) getContext().getBean("metricServiceImpl");
            case "projectServiceImpl": return (ProjectServiceImpl) getContext().getBean("projectServiceImpl");
            case "scoreServiceImpl": return (ScoreServiceImpl) getContext().getBean("scoreServiceImpl");
            case "userServiceImpl": return (UserServiceImpl) getContext().getBean("userServiceImpl");
            case "verificationServiceImpl": return (VerificationServiceImpl) getContext().getBean("verificationServiceImpl");
            default: return null;
        }
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }
}
