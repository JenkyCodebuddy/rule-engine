package jenky.codebuddy.database.score;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.Project;
import jenky.codebuddy.models.entities.Score;
import jenky.codebuddy.models.entities.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

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

    /**
     * Saves the project.
     * @param score
     */
    @Override
    public void save(Score score) {
        super.add(score);
    }

    public List<Score> getPreviousScores(String userEmail){
        List<Score> scores = new ArrayList<Score>();
        String hql = "select max(s.commit.id) from Score s WHERE s.user.email = :userEmail";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("userEmail",userEmail);
        int result = (int) query.uniqueResult();
        System.out.println("result = " + result);
        if(result > 0){
            String hql2 = "from Score s WHERE s.commit.id = :commitId";
            Query query2 = getSessionFactory().getCurrentSession().createQuery(hql2);
            query2.setParameter("commitId", result);
            scores = query2.list();
        }
        return  scores;
    }

    @Override
    public double getAvgScoreFromUser(int user_id){
        String hql = "SELECT avg(score.score) FROM Score score INNER JOIN User user ON user.id = score.user WHERE user.id= :user_id GROUP BY user.id";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("user_id",user_id);
        OptionalDouble result = OptionalDouble.of((double) query.uniqueResult());
        return result.getAsDouble();
    }

    @Override
    public double getTotalScoreFromUser(int user_id){
        String hql = "SELECT sum(score.score) FROM Score score INNER JOIN User user ON user.id = score.user WHERE user.id= :user_id GROUP BY user.id";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("user_id",user_id);
        OptionalLong result =  OptionalLong.of((long) query.uniqueResult());
        return result.getAsLong();
    }

    @Override
    public List<Score> getScoresFromProject(int project_id) {
        String hql = "SELECT score.user.email, sum(score.score), (SELECT item.type FROM Item item) FROM Score score INNER JOIN score.commit as commits INNER JOIN commits.project as projects WHERE projects.id =:project_id GROUP BY score.user";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("project_id",project_id);
        Optional<List<Score>> result = Optional.ofNullable((List<Score>) query.list());
        return result.get();
    }
}
