package jenky.codebuddy.database.score;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.Score;

import java.util.List;

/**
 * Created by joost on 30-5-2016.
 */
public interface ScoreDao extends GenericDao<Score, Integer> {
    public List<Score> getAllScores();
}
