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
 * Service layer of Score. Inherits the GenericService and implements the ScoreUserServiceImpl interface.
 */
@Service
public class ScoreServiceImpl extends GenericServiceImpl<Score, Integer> implements ScoreService {

    private ScoreDao scoreDao;

    /**
     * Injected by Spring
     * @param scoreDao
     */
    @Autowired
    public ScoreServiceImpl(@Qualifier("scoreDaoImpl") ScoreDao scoreDao) {
        this.scoreDao = scoreDao;
    }

    public ScoreServiceImpl(){

    }

    /**
     * Asks the scoreDao to save the given score.
     * Transaction management done by Spring.
     * @param score
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(Score score) {
        scoreDao.save(score);
    }

    /**
     * Asks the scoreDao to delete the given score.
     * Transaction management done by Spring.
     * @param score
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Score score) {
        scoreDao.delete(score);
    }

    /**
     * Asks the scoreDao to get all the scores.
     * Transaction management done by Spring.
     * @return List containig all the scores.
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Score> getAllScores(){
        return scoreDao.getAllScores();
    }

    /**
     * Asks the scoreDao to return tbe previous scores.
     * Transaction management done by Spring.
     * @param userEmail
     * @return list of previous scores
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<Score> getPreviousScores(String userEmail) {
        return scoreDao.getPreviousScores(userEmail);
    }

    /**
     * @param user_id
     * @return int
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public double getAvgScoreFromUser(int user_id){
        return scoreDao.getAvgScoreFromUser(user_id);
    }

    /**
     * @param user_id
     * @return int
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public double getTotalScoreFromUser(int user_id){
        return scoreDao.getTotalScoreFromUser(user_id);
    }

    /**
     * @param project_id
     * @return List of scores from the given project
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Object> getScoresFromProject(int project_id){
        return scoreDao.getScoresFromProject(project_id);
    }

    /**
     * @param project_id
     * @return boolean
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean checkIfUserHasScores(int project_id){
        return scoreDao.checkIfUserHasScores(project_id);
    }

}
