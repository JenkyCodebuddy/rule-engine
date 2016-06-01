package jenky.codebuddy.database.score;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.Score;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Persistence layer of ScoreDao. Inherets GenericDao and implements the ScoreDao interface.
 */
@Repository
public class ScoreDaoImpl extends GenericDaoImpl<Score, Integer> implements ScoreDao {

    /**
     * Get all the scores
     * @return List containing scores
     */
    @Override
    public List<Score> getAllScores() {
        List<Score> scores = super.findAll();
        return scores;
    }
}
