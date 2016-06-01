package jenky.codebuddy.database.metric;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.Metric;

import java.util.List;

/**
 * This interface specifies specific methodes for MetricDao
 */
public interface MetricDao extends GenericDao<Metric, Integer> {
    public List<Metric> getAllMetrics();

    public boolean checkIfMetricExists(String metric);

    public void saveMetric(Metric metric);
}
