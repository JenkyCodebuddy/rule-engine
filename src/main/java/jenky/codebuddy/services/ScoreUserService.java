package jenky.codebuddy.services;

import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.gson.Metric;
import jenky.codebuddy.models.gson.SonarResponse;
import jenky.codebuddy.models.rest.UserCommit;
import wildtornado.scocalc.objects.Score;

import java.util.*;

/**
 * Created by Fabian on 4-6-2016.
 */
public class ScoreUserService {

    private Score metricsDataInputModel;
    private SonarResponse sonarResponse;
    private UserCommit userCommit;

    public ScoreUserService (){
    }
    public ScoreUserService(Score metricsDataInputModel, SonarResponse sonarResponse, UserCommit userCommit) {
        this.metricsDataInputModel = metricsDataInputModel;
        this.sonarResponse = sonarResponse;
        this.userCommit = userCommit;
        Commit commit = createCommit(userCommit);
        saveUserScore(commit);
    }

    private void saveUserScore(Commit commit){
        List<Metric> metricsList = this.sonarResponse.getMsr();
        Set<jenky.codebuddy.models.entities.Score> scores = new HashSet<>(0);
        User user = DatabaseFactory.getUserService().getUserIfExists(this.userCommit.getEmail());
        for (Metric aMetricsList : metricsList) {
            jenky.codebuddy.models.entities.Score score = new jenky.codebuddy.models.entities.Score();
            score.setUser(user);
            score.setSonar_value(aMetricsList.getVal());
            score.setScore((int) getScoreByName(aMetricsList.getKey()));
            score.setCommit(commit);
            score.setMetric(DatabaseFactory.getMetricService().getMetricIfExists(aMetricsList.getKey()));
            scores.add(score);
            DatabaseFactory.getScoreService().save(score);
        }
        //TODO add to current coins
        user.setJenkycoins(this.metricsDataInputModel.getCoinsEarned());
        user.setScores(scores);
        user.setUpdated_at(new Date());
        DatabaseFactory.getUserService().saveOrUpdate(user);
    }

    private Commit createCommit(UserCommit userCommit){
        Commit commit = new Commit();
        commit.setBranch(userCommit.getBranch());
        commit.setProject(DatabaseFactory.getProjectService().getProjectIfExists(userCommit.getProjectName()));
        commit.setCreated_at(new Date());
        commit.setSha(userCommit.getSha());
        return commit;
    }

    private double getScoreByName(String name){
        switch (name) {
            case "ncloc": return this.metricsDataInputModel.getLinesOfCodeScore();
            case "sqale_index": return this.metricsDataInputModel.getTechnicalDebtScore();
            case "duplicated_lines": return this.metricsDataInputModel.getCodeDuplicationScore();
            case "coverage": return this.metricsDataInputModel.getTestCoverageScore();
            case "violations": return this.metricsDataInputModel.getCodeViolationsScore();
            default: return 0;
        }
    }

    public List<jenky.codebuddy.models.entities.Score> getPreviousScores(String email){
        return DatabaseFactory.getScoreService().getPreviousScores(email);
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
}
