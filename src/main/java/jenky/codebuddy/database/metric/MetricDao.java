package jenky.codebuddy.database.metric;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.Metric;

import java.util.List;

/**
 * Created by joost on 30-5-2016.
 */
public interface MetricDao extends GenericDao<Metric, Integer> {
    public List<Metric> getAllMetrics();

    public boolean checkIfMetricExists(String metric);

    public void saveMetric(Metric metric);
}
