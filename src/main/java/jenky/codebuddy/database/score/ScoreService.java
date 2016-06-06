package jenky.codebuddy.database.score;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.Score;
import jenky.codebuddy.models.entities.User;

import java.util.List;

/**
 * THis itnerface specifies specific methods ScoreUserService must have.
 */
public interface ScoreService extends GenericService<Score, Integer> {
    public List<Score> getAllScores();

    public void save(Score score);

    public List<Score> getPreviousScores(String userEmail);


}
