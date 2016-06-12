package jenky.codebuddy.database.score;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.Score;

import java.util.List;

/**
 * This interface specifies the specific methods ScoreDao must have.
 */
public interface ScoreDao extends GenericDao<Score, Integer> {
    /**
     * @return List of all scores
     */
    public List<Score> getAllScores();

    /**
     * @param score
     */
    public void save(Score score);

    /**
     * @param score
     */
    public void delete(Score score);

    /**
     * @param userEmail
     * @return previous scores of the user otherwise empty set of scores
     */
    public List<Score> getPreviousScores(String userEmail);

    /**
     * @param user_id
     * @return int
     */
    public double getAvgScoreFromUser(int user_id);

    /**
     * @param user_id
     * @return int
     */
    public double getTotalScoreFromUser(int user_id);

    /**
     * @param project_id
     * @return int
     */
    public List<Object> getScoresFromProject(int project_id);

    /**
     * @param user_id
     * @return boolean
     */
    public boolean checkIfUserHasScores(int user_id);
}
