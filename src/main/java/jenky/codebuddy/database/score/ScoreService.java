package jenky.codebuddy.database.score;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.Score;

import java.util.List;

/**
 * THis itnerface specifies specific methods ScoreUserService must have.
 */
public interface ScoreService extends GenericService<Score, Integer> {
    public List<Score> getAllScores();

    public void save(Score score);

    public void delete(Score score);

    public List<Score> getPreviousScores(String userEmail);

    public double getAvgScoreFromUser(int user_id);

    public double getTotalScoreFromUser(int user_id);

    public List<Object> getScoresFromProject(int project_id);

    public boolean checkIfUserHasScores(int user_id);

}
