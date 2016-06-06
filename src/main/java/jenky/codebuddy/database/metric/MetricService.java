package jenky.codebuddy.database.metric;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.Metric;

import java.util.List;

/**
 * This interface specifices the specific methods MetricService must have.
 */
public interface MetricService extends GenericService<Metric, Integer> {
    public List<Metric> getAllMetrics();

    public boolean checkIfMetricExists(String metric);

    public Metric getMetricIfExists(String metricName);


    public void saveMetric(Metric metric);
}
