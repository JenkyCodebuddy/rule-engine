package jenky.codebuddy;

import jenky.codebuddy.database.metric.MetricServiceImpl;
import jenky.codebuddy.models.entities.Metric;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;


/**
 * Created by Fabian on 10-6-2016.
 */
public class MetricTest {

    private ApplicationContext context;
    private MetricServiceImpl metricService;

    Metric metric;

    public MetricTest(){
        this.context = (new ClassPathXmlApplicationContext("spring.xml"));
        this.metricService = (MetricServiceImpl) getContext().getBean("metricServiceImpl");
        this.metric = new Metric();
        metric.setName(TestInfo.TESTMETRIC);
    }

    @Test
    @Transactional
    @Rollback
    public void saveMetric() throws Exception{
        metricService.saveMetric(this.metric);
    }

    @Test
    @Transactional
    public void checkIfMetricExsists(){
        assertNotNull(metricService.checkIfMetricExists(TestInfo.TESTMETRIC));
    }

    public ApplicationContext getContext() {
        return context;
    }

}
