package jenky.codebuddy.database.services;

import jenky.codebuddy.models.entities.Metric;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by joost on 29-5-2016.
 */
public class ServiceFactory<T> {

    ApplicationContext context;

    public ServiceFactory(){
        setContext(new ClassPathXmlApplicationContext("spring.xml"));
    }

    public Object getService(String serviceClassName){

        Object returnObject = null;
        switch(serviceClassName){
            case "AchievementService": returnObject = context.getBean("achievementService");
            break;
            case "CommitService": returnObject = context.getBean("commitService");
            break;
            case "CompanyService": returnObject = context.getBean("companyService");
            break;
            case "ItemService": returnObject = context.getBean("itemService");
            break;
            case "MetricService": returnObject = context.getBean("metricService");
            break;
            case "ProjectService": returnObject = context.getBean("projectService");
            break;
            case "ScoreService": returnObject = context.getBean("scoreService");
            break;
            case "UserService": returnObject = context.getBean("userService");
            break;

        }
        return returnObject;

    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }
}
