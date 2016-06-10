package jenky.codebuddy.database.metric;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.Metric;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Persistent for MetricDao
 */
@Repository
public class MetricDaoImpl extends GenericDaoImpl<Metric, Integer> implements MetricDao {

    /**
     * Get all the metrics
     * @return List of metrics
     */
    @Override
    public List<Metric> getAllMetrics() {
        return super.findAll();
    }

    /**
     * Checks if a certain metric exists
     * @param metric
     * @return true or false
     */
    @Override
    public boolean checkIfMetricExists(String metric) {
        String hql = "FROM Metric m WHERE m.name = :metric";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("metric",metric);
        Metric result = (Metric) query.uniqueResult();
        if(result != null){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Metric getMetricIfExists(String metricName) {
        String hql = "FROM Metric m WHERE m.name = :metric";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("metric",metricName);
        Metric result = (Metric) query.uniqueResult();
        if(result != null){
            return result;
        }
        else{
            return null;
        }
    }

    /**
     * Saves the given metric
     * @param metric
     */
    @Override
    public void saveMetric(Metric metric){
        super.add(metric);
    }
}
