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
 * Created by joost on 30-5-2016.
 */
@Service
public class MetricServiceImpl extends GenericServiceImpl<Metric, Integer> implements MetricService {

    private MetricDao metricDao;

    @Autowired
    public MetricServiceImpl(@Qualifier("metricDaoImpl") MetricDao metricDao) {
        this.metricDao = metricDao;
    }

    public MetricServiceImpl(){

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Metric> getAllMetrics() {
        return metricDao.getAllMetrics();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public boolean checkIfMetricExists(String metric) {
        return metricDao.checkIfMetricExists(metric);
    }

    @Override
    @Transactional
    public void saveMetric(Metric metric){
        metricDao.add(metric);
    }
}
