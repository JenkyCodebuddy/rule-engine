package jenky.codebuddy.services;

import jenky.codebuddy.database.project.ProjectServiceImpl;
import jenky.codebuddy.database.score.ScoreServiceImpl;
import jenky.codebuddy.database.user.UserServiceImpl;
import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.Project;
import jenky.codebuddy.models.entities.Score;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.gson.Metric;
import jenky.codebuddy.models.gson.SonarResponse;
import jenky.codebuddy.models.rest.UserCommit;
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
    private UserCommit userCommit;
    private ApplicationContext context;
    private Map<String, String> sonarToScore = new HashMap<>();


    public SaveScoreService(DataInput metricsDataInputModel, SonarResponse sonarResponse, UserCommit userCommit) {
        setContext(new ClassPathXmlApplicationContext("spring.xml"));
        this.metricsDataInputModel = metricsDataInputModel;
        this.sonarResponse = sonarResponse;
        this.userCommit = userCommit;

        Commit commit = createCommit(userCommit);
        saveUserScore(commit);
    }

    public void saveUserScore(Commit commit){
        Score score = new Score();
        User user = new User();
        UserServiceImpl userService = (UserServiceImpl) getContext().getBean("userServiceImpl");
        ScoreServiceImpl scoreService = (ScoreServiceImpl) getContext().getBean("scoreServiceImpl");
        List<Metric> metricsList = this.sonarResponse.getMsr();
        Set<Score> scores = new HashSet<Score>(0);
        for (Metric aMetricsList : metricsList) {
            score.setUser(userService.getUserIfExists(this.userCommit.getEmail()));
            score.setSonar_value(aMetricsList.getVal());
            score.setScore((int) getScoreByName(aMetricsList.getKey()));
            score.setCommit(commit);
        }

        user.setScores(scores);
        userService.saveOrUpdate(user);
        scoreService.add(score);
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
            case "ncloc": return this.metricsDataInputModel.getLinesOfCode();
            case "sqale_index": return this.metricsDataInputModel.getTechnicalDebt();
            case "duplicated_lines": return this.metricsDataInputModel.getCodeDuplication();
            case "duplicated_lines_density": return this.metricsDataInputModel.getCodeDuplicationDensity();
            case "comment_lines": return this.metricsDataInputModel.getCommentLines();
            case "tests": return this.metricsDataInputModel.getNumberOfTests();
            case "coverage": return this.metricsDataInputModel.getTestCoverage();
            case "test_erros": return this.metricsDataInputModel.getTestErrors();
            case "test_failures": return this.metricsDataInputModel.getTestFailures();
            case "violations": return this.metricsDataInputModel.getCodeViolations();
            case "major_violations": return this.metricsDataInputModel.getMajorViolations();
            case "minor_violations": return this.metricsDataInputModel.getMinorViolations();
            case "critical_violations": return this.metricsDataInputModel.getCriticalViolations();
            case "blocker_violations": return this.metricsDataInputModel.getBlockerViolations();
            default: return -1;
        }
    }


    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }
}
