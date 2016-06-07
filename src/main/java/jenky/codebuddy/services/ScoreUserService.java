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
public class ScoreUserService {

    private Score metricsDataInputModel;
    private SonarResponse sonarResponse;
    private UserCommit userCommit;
    private ApplicationContext context;

    UserServiceImpl userService;
    ScoreServiceImpl scoreService;
    MetricServiceImpl metricService;



    public ScoreUserService (){
        setContext(new ClassPathXmlApplicationContext("spring.xml"));
        this.userService = (UserServiceImpl) getContext().getBean("userServiceImpl");
        this.scoreService = (ScoreServiceImpl) getContext().getBean("scoreServiceImpl");
        this.metricService = (MetricServiceImpl) getContext().getBean("metricServiceImpl");
    }
    public ScoreUserService(Score metricsDataInputModel, SonarResponse sonarResponse, UserCommit userCommit) {
        setContext(new ClassPathXmlApplicationContext("spring.xml"));
        this.metricsDataInputModel = metricsDataInputModel;
        this.sonarResponse = sonarResponse;
        this.userCommit = userCommit;

        this.userService = (UserServiceImpl) getContext().getBean("userServiceImpl");
        this.scoreService = (ScoreServiceImpl) getContext().getBean("scoreServiceImpl");
        this.metricService = (MetricServiceImpl) getContext().getBean("metricServiceImpl");

        Commit commit = createCommit(userCommit);
        saveUserScore(commit);
    }

    private void saveUserScore(Commit commit){

        List<Metric> metricsList = this.sonarResponse.getMsr();
        Set<jenky.codebuddy.models.entities.Score> scores = new HashSet<>(0);
        User user = userService.getUserIfExists(this.userCommit.getEmail());
        for (Metric aMetricsList : metricsList) {
            jenky.codebuddy.models.entities.Score score = new jenky.codebuddy.models.entities.Score();
            score.setUser(user);
            score.setSonar_value(aMetricsList.getVal());
            score.setScore((int) getScoreByName(aMetricsList.getKey()));
            score.setCommit(commit);
            score.setMetric(metricService.getMetricIfExists(aMetricsList.getKey()));
            scores.add(score);
            scoreService.save(score);
        }
        user.setJenkycoins(10);
        user.setScores(scores);
        user.setUpdated_at(new Date());
        userService.saveOrUpdate(user);
    }

    private Commit createCommit(UserCommit userCommit){
        ProjectServiceImpl projectService = (ProjectServiceImpl) getContext().getBean("projectServiceImpl");
        Commit commit = new Commit();
        commit.setBranch(userCommit.getBranch());
        commit.setProject(projectService.getProjectIfExists(userCommit.getProjectName()));
        commit.setCreated_at(new Date());
        commit.setSha(userCommit.getSha());

        return commit;
    }

    private double getScoreByName(String name){
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

    public List<jenky.codebuddy.models.entities.Score> getPreviousScores(String email){
        ScoreServiceImpl scoreService = (ScoreServiceImpl) getContext().getBean("scoreServiceImpl");
        return scoreService.getPreviousScores(email);
    }

    public Map<String, String> createGithubUserInfoMap(Map<String, String> headers){
        Map githubInfoMap = new HashMap<String, String>();
        githubInfoMap.put("username",headers.get("username"));
        githubInfoMap.put("email", headers.get("email"));
        githubInfoMap.put("branch", headers.get("branch"));
        githubInfoMap.put("sha", headers.get("sha"));
        githubInfoMap.put("projectName", headers.get("projectname"));
        return githubInfoMap;
    }




    private ApplicationContext getContext() {
        return context;
    }

    private void setContext(ApplicationContext context) {
        this.context = context;
    }
}
