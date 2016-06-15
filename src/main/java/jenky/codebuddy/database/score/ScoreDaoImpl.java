package jenky.codebuddy.database.score;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.Score;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.services.DatabaseFactory;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Persistence layer of ScoreDao. Inherets GenericDao and implements the ScoreDao interface.
 */
@Repository
public class ScoreDaoImpl extends GenericDaoImpl<Score, Integer> implements ScoreDao {

    @PostConstruct
    public void init(){
        super.setType(Score.class);
    }

    /**
     * Get all the scores
     * @return List containing scores
     */
    @Override
    public List<Score> getAllScores() {
        return super.findAll();
    }

    /**
     * Saves the score.
     * @param score
     */
    @Override
    public void save(Score score) {
        super.add(score);
    }

    /**
     * Deletes the score.
     * @param score
     */
    @Override
    public void delete(Score score) {
        super.delete(score);
    }

    /**
     * @param userEmail
     * @return List of previous scores otherwise empty set of scores
     */
    public List<Score> getPreviousScores(String userEmail){
        List<Score> scores = new ArrayList<Score>();
        String hql = "select max(s.commit.id) from Score s WHERE s.user.email = :userEmail";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("userEmail",userEmail);
        Optional result = Optional.ofNullable(query.uniqueResult());
        if(result.isPresent()){
            String hql2 = "from Score s WHERE s.commit.id = :commitId";
            Query query2 = getSessionFactory().getCurrentSession().createQuery(hql2);
            query2.setParameter("commitId", result.get());
            scores = query2.list();
        }
        return scores;
    }

    /**
     * @param user_id
     * @return int
     */
    @Override
    public double getAvgScoreFromUser(int user_id){
        String hql = "SELECT avg(score.score) FROM Score score INNER JOIN User user ON user.id = score.user WHERE user.id= :user_id GROUP BY user.id";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("user_id",user_id);
        Optional result = Optional.ofNullable(query.uniqueResult());
        return result.isPresent() ? (double)result.get() : null;
    }

    /**
     * @param user_id
     * @return int
     */
    @Override
    public double getTotalScoreFromUser(int user_id){
        String hql = "SELECT sum(score.score) FROM Score score INNER JOIN User user ON user.id = score.user WHERE user.id= :user_id GROUP BY user.id";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("user_id",user_id);
        Optional result = Optional.ofNullable(query.uniqueResult());
        return result.isPresent() ? (double)result.get() : null;
    }

    @Override
    public double getTotalScoreFromUserForProject(int user_id, int project_id){
        String hql = "SELECT sum(score.score) FROM Score score INNER JOIN User user ON user.id = score.user INNER JOIN Commit commit ON commit.id = score.commit INNER JOIN Project project ON project.id = commit.project WHERE user.id= :user_id AND project.id = :project_id GROUP BY user.id";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("user_id",user_id);
        query.setParameter("project_id",project_id);
        Optional result = Optional.ofNullable(query.uniqueResult());
        return result.isPresent() ? (double)result.get() : null;
    }

    /**
     * @param project_id
     * @return int
     */
    @Override
    public List<User> getScoresFromProject(int project_id) {
        String hql = "SELECT score FROM Score score LEFT JOIN FETCH score.commit as commits LEFT JOIN FETCH commits.project as projects WHERE projects.id =:project_id GROUP BY score.user";

        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("project_id",project_id);
        Optional<List<Score>> listWithScores = Optional.ofNullable((List<Score>) query.list());
        List<User> allUsersWithEquipment = new ArrayList<User>();
        for(int i = 0; i < listWithScores.get().size(); i++){
            User u = listWithScores.get().get(i).getUser();
            u.setPassword(null);
            u.setEquipment(DatabaseFactory.getItemService().getEquippedItemsFromUser(u.getUser_id()));
            u.setTotalScore(DatabaseFactory.getScoreService().getTotalScoreFromUserForProject(u.getUser_id(), project_id));
            allUsersWithEquipment.add(u);
        }
        return allUsersWithEquipment;
    }

    /**
     * @param user_id
     * @return boolean
     */
    @Override
    public boolean checkIfUserHasScores(int user_id) {
        String hql = "SELECT score.id FROM Score score WHERE score.user =:user_id";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("user_id",user_id);
        query.setMaxResults(1);
        boolean result = query.list().isEmpty();
        return result ? false: true;
    }

    @Override
    public double getScoreFromCommit(int commit_id) {
        String hql = "SELECT sum(score.score) FROM Score score INNER JOIN Commit commit on commit.id = score.commit WHERE commit.id = :commit_id GROUP BY commit.id";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("commit_id",commit_id);
        Optional result = Optional.ofNullable(query.uniqueResult());
        return result.isPresent() ? (double)result.get() : null;
    }
}
