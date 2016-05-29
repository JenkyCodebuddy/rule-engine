package jenky.codebuddy;

import jenky.codebuddy.models.rest.CompleteResult;

/**
 * Created by joost on 26-5-2016.
 */
public class BusinessLogicDB {

    //DatabaseServiceFactory factory = new DatabaseServiceFactory();
    public BusinessLogicDB() {

    }

    public void updateMetricTable(String metric) {
//        MetricService metricService = (MetricService) new ServiceFactory().getService("MetricService");
//        if (!metricService.checkIfRecordExists("name", metric)) {
//            Metric m = new Metric();
//            m.setCreated_at(new Date());
//            m.setName(metric);
//            metricService.persist(m);
//        }
    }

    public void storeCompleteResultModel(CompleteResult completeResult) {
        /*UserService userService = factory.getDatabaseService("User");*/
    }
}

