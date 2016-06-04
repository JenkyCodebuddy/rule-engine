package jenky.codebuddy.services;

import jenky.codebuddy.database.user.UserServiceImpl;
import jenky.codebuddy.models.entities.Score;
import jenky.codebuddy.models.gson.Metric;
import jenky.codebuddy.models.gson.SonarResponse;
import jenky.codebuddy.models.rest.Commit;
import wildtornado.scocalc.objects.DataInput;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;

/**
 * Created by Fabian on 4-6-2016.
 */
public class SaveScoreService {

    private DataInput metricsDataInputModel;
    private SonarResponse sonarResponse;
    private Commit commit;
    private ApplicationContext context;
    private Map<String, String> sonarToScore = new HashMap<>();

    public SaveScoreService(DataInput metricsDataInputModel, SonarResponse sonarResponse, Commit commit) {
        setContext(new ClassPathXmlApplicationContext("spring.xml"));
        this.metricsDataInputModel = metricsDataInputModel;
        this.sonarResponse = sonarResponse;
        this.commit = commit;
    }

    public void saveScore(){
        UserServiceImpl userService = (UserServiceImpl) getContext().getBean("userServiceImpl");
        List<Metric> metricsList = this.sonarResponse.getMsr();
        Set<Score> scores = new HashSet<Score>(0);
        for (Metric aMetricsList : metricsList) {
            Score score = new Score();
            score.setUser(userService.getUserIfExists(this.commit.getEmail()));
            score.setSonar_value(aMetricsList.getVal());
            score.setScore(this.metricsDataInputModel.g);

            this.metricsMap.put(aMetricsList.getKey(), aMetricsList.getVal());
        }
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }
}
