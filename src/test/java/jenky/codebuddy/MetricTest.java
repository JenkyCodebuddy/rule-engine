package jenky.codebuddy;

import jenky.codebuddy.models.entities.Metric;
import jenky.codebuddy.services.DatabaseFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;


/**
 * Created by Fabian on 10-6-2016.
 */
public class MetricTest {

    Metric metric;

    @Before
    public void setUp(){
        this.metric = new Metric();
        metric.setName(TestInfo.TESTMETRIC);
    }

    @Test
    @Transactional
    @Rollback
    public void saveMetric() throws Exception{
        DatabaseFactory.getMetricService().saveMetric(this.metric);
    }

    @Test
    @Transactional
    public void checkIfMetricExsists(){
        assertNotNull(DatabaseFactory.getMetricService().checkIfMetricExists(TestInfo.TESTMETRIC));
    }

}
