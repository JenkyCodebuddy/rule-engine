package jenky.codebuddy.services;

import jenky.codebuddy.database.metric.MetricServiceImpl;
import jenky.codebuddy.database.project.ProjectServiceImpl;
import jenky.codebuddy.database.score.ScoreServiceImpl;
import jenky.codebuddy.database.user.UserServiceImpl;
import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.gson.Metric;
import jenky.codebuddy.models.gson.SonarResponse;
import jenky.codebuddy.models.rest.UserCommit;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import wildtornado.scocalc.objects.Score;

import java.util.*;

/**
 * Created by Fabian on 4-6-2016.
 */
public class SaveScoreService {

    private Score metricsDataInputModel;
    private SonarResponse sonarResponse;
    private UserCommit userCommit;
    private ApplicationContext context;


    public SaveScoreService(Score metricsDataInputModel, SonarResponse sonarResponse, UserCommit userCommit) {
        setContext(new ClassPathXmlApplicationContext("spring.xml"));
        this.metricsDataInputModel = metricsDataInputModel;
        this.sonarResponse = sonarResponse;
        this.userCommit = userCommit;

        Commit commit = createCommit(userCommit);
        saveUserScore(commit);
    }

    public void saveUserScore(Commit commit){
        User user = new User();
        UserServiceImpl userService = (UserServiceImpl) getContext().getBean("userServiceImpl");
        ScoreServiceImpl scoreService = (ScoreServiceImpl) getContext().getBean("scoreServiceImpl");
        MetricServiceImpl metricService = (MetricServiceImpl) getContext().getBean("metricServiceImpl");

        List<Metric> metricsList = this.sonarResponse.getMsr();
        Set<jenky.codebuddy.models.entities.Score> scores = new HashSet<>(0);
        for (Metric aMetricsList : metricsList) {
            jenky.codebuddy.models.entities.Score score = new jenky.codebuddy.models.entities.Score();
            score.setUser(userService.getUserIfExists(this.userCommit.getEmail()));
            score.setSonar_value(aMetricsList.getVal());
            score.setScore((int) getScoreByName(aMetricsList.getKey()));
            score.setCommit(commit);
            score.setMetric(metricService.getMetricIfExists(aMetricsList.getKey()));
            scores.add(score);
            scoreService.save(score);
        }

        user.setJenkycoins(10);
        user.setScores(scores);
        userService.add(user);
    }

    public Commit createCommit(UserCommit userCommit){
        ProjectServiceImpl projectService = (ProjectServiceImpl) getContext().getBean("projectServiceImpl");
        Commit commit = new Commit();
        commit.setBranch(userCommit.getBranch());
        commit.setProject(projectService.getProjectIfExists(userCommit.getProjectName()));
        commit.setCreated_at(new Date());
        commit.setSha(userCommit.getSha());

        return commit;
    }

    public double getScoreByName(String name){
        switch (name) {
            case "ncloc": return this.metricsDataInputModel.getLinesOfCodeScore();
            case "sqale_index": return this.metricsDataInputModel.getTechnicalDebtScore();
            case "duplicated_lines": return this.metricsDataInputModel.getCodeDuplicationScore();
            //case "duplicated_lines_density": return this.metricsDataInputModel.getCodeDuplicationDensityScore();
            //case "comment_lines": return this.metricsDataInputModel.getCommentLinesScore();
            //case "tests": return this.metricsDataInputModel.getNumberOfTestsScore();
            case "coverage": return this.metricsDataInputModel.getTestCoverageScore();
            //case "test_erros": return this.metricsDataInputModel.getTestErrorsScore();
            //case "test_failures": return this.metricsDataInputModel.getTestFailuresScore();
            case "violations": return this.metricsDataInputModel.getCodeViolationsScore();
            //case "major_violations": return this.metricsDataInputModel.getMajorViolationsScore();
            //case "minor_violations": return this.metricsDataInputModel.getMinorViolationsScore();
            //case "critical_violations": return this.metricsDataInputModel.getCriticalViolationsScore();
            //case "blocker_violations": return this.metricsDataInputModel.getBlockerViolationsScore();
            default: return 0;
        }
    }



    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }
}
