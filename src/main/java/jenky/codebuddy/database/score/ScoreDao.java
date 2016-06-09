package jenky.codebuddy.database.score;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.Score;
import jenky.codebuddy.models.entities.User;

import java.util.List;

/**
 * This interface specifies the specific methods ScoreDao must have.
 */
public interface ScoreDao extends GenericDao<Score, Integer> {
    public List<Score> getAllScores();

    public void save(Score score);

    public List<Score> getPreviousScores(String userEmail);

    public double getAvgScoreFromUser(int user_id);

    public double getTotalScoreFromUser(int user_id);

    public List<Score> getScoresFromProject(int project_id);

    public boolean checkIfUserHasScores(int user_id);
}
