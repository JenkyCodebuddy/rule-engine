package jenky.codebuddy.database.score;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.Score;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by joost on 30-5-2016.
 */
@Repository
public class ScoreDaoImpl extends GenericDaoImpl<Score, Integer> implements ScoreDao {

    @Override
    public List<Score> getAllScores() {
        List<Score> scores = super.findAll();
        return scores;
    }
}
