package jenky.codebuddy;

import jenky.codebuddy.database.services.DatabaseServiceFactory;
import jenky.codebuddy.database.services.GenericDatabaseService;
import jenky.codebuddy.models.entities.Metric;
import jenky.codebuddy.models.rest.CompleteResult;

import java.util.Date;

/**
 * Created by joost on 26-5-2016.
 */
public class BusinessLogicDB {

    DatabaseServiceFactory factory = new DatabaseServiceFactory();

    public BusinessLogicDB() {

    }

    public void updateMetricTable(String metric) {
        GenericDatabaseService metricService = new DatabaseServiceFactory().getDatabaseService("Metric");
        if (!metricService.checkIfRecordExists("name", metric)) {
            Metric m = new Metric();
            m.setCreated_at(new Date());
            m.setName(metric);
            metricService.persist(m);
        }
    }

    public void storeCompleteResultModel(CompleteResult completeResult) {
        GenericDatabaseService userService = factory.getDatabaseService("User");
    }
}

