package jenky.codebuddy.database.metric;

import jenky.codebuddy.database.generic.GenericServiceImpl;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.Metric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer of Metric. Inherits the GenericService and implements the MetricService
 */
@Service
public class MetricServiceImpl extends GenericServiceImpl<Metric, Integer> implements MetricService {

    private MetricDao metricDao;

    /**
     * Injected by Spring
     * @param metricDao
     */
    @Autowired
    public MetricServiceImpl(@Qualifier("metricDaoImpl") MetricDao metricDao) {
        this.metricDao = metricDao;
    }

    public MetricServiceImpl(){

    }

    /**
     * Asks metricDao to find and return all the metrics.
     * Transaction management done by Spring.
     * @return List of found metrics
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Metric> getAllMetrics() {
        return metricDao.getAllMetrics();
    }

    /**
     * Asks metricDao to find if the given metric exists.
     * Transaction management done by Spring.
     * @param metric
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public boolean checkIfMetricExists(String metric) {
        return metricDao.checkIfMetricExists(metric);
    }

    /**
     * Asks metricDao to save the given Metric.
     * Transaction management done by Spring.
     * @param metric
     */
    @Override
    @Transactional
    public void saveMetric(Metric metric){
        metricDao.add(metric);
    }

    /**
     * Asks the projectDao to return if the given project exists or not.
     * Transaction management done by Spring.
     * @param metricName
     * @return true or false
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Metric getMetricIfExists(String metricName) {
        return metricDao.getMetricIfExists(metricName);
    }
}
