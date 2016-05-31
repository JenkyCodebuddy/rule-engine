package jenky.codebuddy.database.score;

import jenky.codebuddy.database.generic.GenericServiceImpl;
import jenky.codebuddy.models.entities.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by joost on 30-5-2016.
 */
@Service
public class ScoreServiceImpl extends GenericServiceImpl<Score, Integer> implements ScoreService {

    private ScoreDao scoreDao;

    @Autowired
    public ScoreServiceImpl(@Qualifier("scoreDaoImpl") ScoreDao scoreDao) {
        this.scoreDao = scoreDao;
    }

    public ScoreServiceImpl(){

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Score> getAllScores(){
        return scoreDao.getAllScores();
    }
}
