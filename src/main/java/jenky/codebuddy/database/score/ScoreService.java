package jenky.codebuddy.database.score;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.Score;

import java.util.List;

/**
 * THis itnerface specifies specific methods ScoreService must have.
 */
public interface ScoreService extends GenericService<Score, Integer> {
    public List<Score> getAllScores();

    public List<Score> getScoresFromUserGroupedByCommit(int user_id);
}
