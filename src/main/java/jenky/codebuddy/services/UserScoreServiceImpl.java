package jenky.codebuddy.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jenky.codebuddy.modelbuilders.CommitModelBuilder;
import jenky.codebuddy.modelbuilders.ScoreModelBuilder;
import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.gson.Metric;
import jenky.codebuddy.models.gson.SonarResponse;
import jenky.codebuddy.models.rest.UserCommit;
import wildtornado.scocalc.objects.Score;

import java.lang.reflect.Type;
import java.util.*;

/**
 * This service has as main function to save the score in the database
 */
public class UserScoreServiceImpl implements UserScoreService {

    public UserScoreServiceImpl() {

    }

    /**
     * Parses the headers and sends it to the appropriate builders. Afterwards saves the models
     * @param headers Reponse from the CI server
     */
    @Override
    public void parseHeaders(Map<String, String> headers){
        Gson gson = new Gson();
        Type sonar = new TypeToken<List<SonarResponse>>(){}.getType();
        List<SonarResponse> sonarResponseList = gson.fromJson(headers.get("sonarquberesponse").replaceAll("\\s",""), sonar);
        SonarResponse sonarResponse = sonarResponseList.get(0);
        Map<String, String> githubInfoMap = createGithubUserInfoMap(headers);
        CommitModelBuilder commitModelBuilder = new CommitModelBuilder(githubInfoMap);
        ScoreModelBuilder scoreModelBuilder = new ScoreModelBuilder(sonarResponse, commitModelBuilder.getUserCommitModel());
        saveUserScore(scoreModelBuilder.getScoreModel(), sonarResponse, commitModelBuilder.getUserCommitModel());;
    }

    /**
     * @param metricsDataInputModel  contains the calculated socre
     * @param sonarResponse  contains the calculated score from sonarqube
     * @param userCommit  contains information about the user who commited this
     */
    @Override
    public void saveUserScore(Score metricsDataInputModel, SonarResponse sonarResponse, UserCommit userCommit){
        Commit commit = createCommit(userCommit);
        List<Metric> metricsList = sonarResponse.getMsr();
        Set<jenky.codebuddy.models.entities.Score> scores = new HashSet<>(0);
        User user = DatabaseFactory.getUserService().getUserIfExists(userCommit.getEmail());
        for (Metric aMetricsList : metricsList) {
            jenky.codebuddy.models.entities.Score score = new jenky.codebuddy.models.entities.Score();
            score.setUser(user);
            score.setSonar_value(aMetricsList.getVal());
            score.setScore((int) getScoreByName(aMetricsList.getKey(), metricsDataInputModel));
            score.setCommit(commit);
            score.setMetric(DatabaseFactory.getMetricService().getMetricIfExists(aMetricsList.getKey()));
            scores.add(score);
            DatabaseFactory.getScoreService().save(score);
        }
        //TODO add to current coins
        user.setJenkycoins(metricsDataInputModel.getCoinsEarned());
        user.setScores(scores);
        user.setUpdated_at(new Date());
        DatabaseFactory.getUserService().saveOrUpdate(user);
    }

    /**
     * Creates a commit using the information from the userCommit
     * @param userCommit contains information about the commiter
     * @return Commit commit
     */
    @Override
    public Commit createCommit(UserCommit userCommit){
        Commit commit = new Commit();
        commit.setBranch(userCommit.getBranch());
        commit.setProject(DatabaseFactory.getProjectService().getProjectIfExists(userCommit.getProjectName()));
        commit.setCreated_at(new Date());
        commit.setSha(userCommit.getSha());
        return commit;
    }

    /**
     * This is created because the names the scorecalculator library uses
     * are different then sonar uses
     * @param name sonar name
     * @param metricsDataInputModel contains the calculated scores
     * @return calculated score for the sonar metric
     */
    @Override
    public double getScoreByName(String name, Score metricsDataInputModel){
        switch (name) {
            case "ncloc": return metricsDataInputModel.getLinesOfCodeScore();
            case "sqale_index": return metricsDataInputModel.getTechnicalDebtScore();
            case "duplicated_lines": return metricsDataInputModel.getCodeDuplicationScore();
            case "coverage": return metricsDataInputModel.getTestCoverageScore();
            case "violations": return metricsDataInputModel.getCodeViolationsScore();
            default: return 0;
        }
    }

    /**
     * @param email of the commiter
     * @return List of previouss scores
     */
    @Override
    public List<jenky.codebuddy.models.entities.Score> getPreviousScores(String email){
        return DatabaseFactory.getScoreService().getPreviousScores(email);
    }

    /**
     * Parses the headers to githubinfo
     * @param headers From the CI server
     * @return Map containing info about the committer
     */
    @Override
    public Map<String, String> createGithubUserInfoMap(Map<String, String> headers){
        Map<String, String> githubInfoMap = new HashMap<>();
        githubInfoMap.put("username",headers.get("username"));
        githubInfoMap.put("email", headers.get("email"));
        githubInfoMap.put("branch", headers.get("branch"));
        githubInfoMap.put("sha", headers.get("sha"));
        githubInfoMap.put("projectName", headers.get("projectname"));
        return githubInfoMap;
    }
}
