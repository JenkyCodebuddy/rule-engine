package jenky.codebuddy.database.metric;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.Metric;

import java.util.List;

/**
 * This interface specifices the specific methods MetricService must have.
 */
public interface MetricService extends GenericService<Metric, Integer> {
    /**
     * @return List of metrics
     */
    public List<Metric> getAllMetrics();

    /**
     * @param metric
     * @return boolean
     */
    public boolean checkIfMetricExists(String metric);

    /**
     * @param metricName
     * @return metric if exists otherwise null
     */
    public Metric getMetricIfExists(String metricName);

    /**
     * @param metric
     */
    public void saveMetric(Metric metric);
}
