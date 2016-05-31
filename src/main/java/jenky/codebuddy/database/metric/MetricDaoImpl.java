package jenky.codebuddy.database.metric;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.Metric;
import jenky.codebuddy.models.entities.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by joost on 30-5-2016.
 */
@Repository
public class MetricDaoImpl extends GenericDaoImpl<Metric, Integer> implements MetricDao {

    @Override
    public List<Metric> getAllMetrics() {
        return super.findAll();
    }

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
    public void saveMetric(Metric metric){
        super.add(metric);
    }
}
