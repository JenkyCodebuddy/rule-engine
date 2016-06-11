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
     * Parses the headers for the different model builders
     * @param headers
     */
    public void parseHeaders(Map<String, String> headers);

    /**
     * @param metricsDataInputModel contains calculated scores
     * @param sonarResponse contains the SonarQube scores
     * @param userCommit contains commit info
     */
    public void saveUserScore(Score metricsDataInputModel, SonarResponse sonarResponse, UserCommit userCommit);

    /**
     * @param userCommit contains info about the commit
     * @return
     */
    public Commit createCommit(UserCommit userCommit);

    /**
     * This is created because the names the scorecalculator library uses
     * are different then sonar uses
     * @param name sonar name
     * @param metricsDataInputModel contains the calculated scores
     * @return calculated score for the sonar metric
     */
    public double getScoreByName(String name, Score metricsDataInputModel);

    /**
     * @param email
     * @return
     */
    public List<jenky.codebuddy.models.entities.Score> getPreviousScores(String email);

    /**
     * Parses the headers to githubinfo map
     * @param headers From the CI server
     * @return Map containing info about the committer
     */
    public Map<String, String> createGithubUserInfoMap(Map<String, String> headers);

    }
