package jenky.codebuddy.services;

import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.gson.SonarResponse;
import jenky.codebuddy.models.rest.UserCommit;
import wildtornado.scocalc.objects.Score;

import java.util.List;
import java.util.Map;

/**
 * Defines the business logic the ScoreUserServiceImpl must provide
 */
public interface ScoreUserService {

    /**
     * @param headers post from CI server
     */
    public void parseHeaders(Map<String, String> headers);

    /**
     * @param metricsDataInputModel contains calculated scores
     * @param sonarResponse contains the SonarQube scores
     * @param userCommit contains commit info
     */
    public void saveUserScore(Score metricsDataInputModel, SonarResponse sonarResponse, UserCommit userCommit);

    /**
     * @param userCommit a map containing info about the commit
     * @return Commit containing info about the commit
     */
    public Commit createCommit(UserCommit userCommit);

    /**
     * This is created because the names the scorecalculator library uses
     * are different than sonar
     * @param name sonar name
     * @param metricsDataInputModel contains the calculated scores
     * @return calculated score for the sonar metric
     */
    public double getScoreByName(String name, Score metricsDataInputModel);

    /**
     * @param email of the user
     * @return List<jenky.codebuddy.models.entities.Score> containing the previous score of the user
     */
    public List<jenky.codebuddy.models.entities.Score> getPreviousScores(String email);

    /**
     * Parses the headers to githubinfo map
     * @param headers From the CI server
     * @return Map containing info about the committer
     */
    public Map<String, String> createGithubUserInfoMap(Map<String, String> headers);

    }
