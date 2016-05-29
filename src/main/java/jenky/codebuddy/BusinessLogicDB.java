package jenky.codebuddy;

import javafx.application.Application;
import jenky.codebuddy.database.services.MetricService;
import jenky.codebuddy.database.services.ServiceFactory;
import jenky.codebuddy.database.services.UserService;
import jenky.codebuddy.models.entities.Metric;
import jenky.codebuddy.models.rest.CompleteResult;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by joost on 26-5-2016.
 */
public class BusinessLogicDB {

    //DatabaseServiceFactory factory = new DatabaseServiceFactory();
    public BusinessLogicDB() {

    }

    public void updateMetricTable(String metric) {
        MetricService metricService = (MetricService) new ServiceFactory().getService("MetricService");
        if (!metricService.checkIfRecordExists("name", metric)) {
            Metric m = new Metric();
            m.setCreated_at(new Date());
            m.setName(metric);
            metricService.persist(m);
        }
    }

    public void storeCompleteResultModel(CompleteResult completeResult) {
        /*UserService userService = factory.getDatabaseService("User");*/
    }
}

