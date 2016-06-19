package jenky.codebuddy.services;

import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.Project;
import jenky.codebuddy.models.entities.User;
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
     * Saves or gets the project if it exists. Always returns a project.
     * If it exists the projects get an updated time.
     * @param projectname
     * @return
     */
    public Project saveOrGetProjectIfExists(String projectname);

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
     * Created a usercommitmodel from the headers
     * @param headers From the CI server
     * @return Map containing info about the committer
     */
    public UserCommit createUserCommitModel(Map<String, String> headers);

    /**
     * extract projectname from address like http://github.com/company/project.git
     * Splits at the fourth / and removes the last four characters (.git)
     * @param url
     * @return
     */
    public String filterRegex(String url);

    /**
     * Generates tips for the user if needed
     * @param user
     */
    public void generateTips(User user, String messageId, String projectName);

}
