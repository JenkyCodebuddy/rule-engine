package jenky.codebuddy.database.score;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.Score;

import java.util.List;

/**
 * This interface specifies the specific methods ScoreDao must have.
 */
public interface ScoreDao extends GenericDao<Score, Integer> {
    public List<Score> getAllScores();

    public void save(Score score);

}
