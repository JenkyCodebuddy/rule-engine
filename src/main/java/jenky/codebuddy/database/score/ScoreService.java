package jenky.codebuddy.database.score;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.Score;

import java.util.List;

/**
 * Created by joost on 30-5-2016.
 */
public interface ScoreService extends GenericService<Score, Integer> {
    public List<Score> getAllScores();
}
