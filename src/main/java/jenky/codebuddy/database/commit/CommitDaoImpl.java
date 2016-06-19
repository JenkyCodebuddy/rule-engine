package jenky.codebuddy.database.commit;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.Project;
import jenky.codebuddy.models.entities.Score;
import jenky.codebuddy.services.DatabaseFactory;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.*;

/**
 * Persistence for UserCommit. Inherits GenericDao and implements the CommitDao interface
 */
@Repository
public class CommitDaoImpl extends GenericDaoImpl<Commit, Integer> implements CommitDao {

    /**
     * Get all the commit
     * @return list containing UserCommit objects
     */
    @Override
    public List<Commit> getCommits() {
        List<Commit> commits = super.findAll();
        return commits;
    }

    /**
     * @param commit
     */
    @Override
    public void saveCommit(Commit commit){
        super.add(commit);
    }

    /**
     * @param user_id
     * @return List<Commit> from the user otherwise null
     */
    @Override
    public List<Commit> getCommitsFromUserForProfile(int user_id){
        String hql = "FROM Commit commit " +
                "LEFT JOIN FETCH commit.project as project " +
                "LEFT JOIN FETCH commit.scores as scores " +
                "WHERE scores.user =:user_id " +
                "GROUP BY commit.id " +
                "ORDER BY commit.id DESC ";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("user_id",user_id);
        query.setMaxResults(3);
        List<Commit> result = query.list();
        for(int i = 0; i<result.size(); i++){
            Commit commit = result.get(i);
            commit.setScore(DatabaseFactory.getScoreService().getScoreFromCommit(result.get(i).getId()));
            Project p = DatabaseFactory.getProjectService().getProjectFromCommit(commit.getId());
            commit.setProjectName(p.getName());
            result.set(i,commit);
        }
        return result;
    }

   public List<Map<String,Double>> getSonarValuesFromLastCommits(int user_id){
        String hql = "FROM Commit commit " +
                "LEFT JOIN FETCH commit.project as project " +
                "INNER JOIN commit.scores as scores " +
                "WHERE scores.user =:user_id " +
                "GROUP BY commit.id " +
                "ORDER BY commit.id DESC ";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("user_id",user_id);
        query.setMaxResults(3);
        Optional<List<Object[]>> result = Optional.ofNullable(query.list());
        if(result.isPresent()) {
            List<Map<String, Double>> sonarValues = new ArrayList<Map<String, Double>>();
            for (Object[] obj : result.get()) {
                Map<String, Double> map = new HashMap<String, Double>();
                for (Score score : ((Commit) obj[0]).getScores()) {
                    map.put(score.getMetric().getName(), score.getSonar_value());
                }
                sonarValues.add(map);
            }
            return sonarValues;
        }
       return null;
   }

    @Override
    public double getUserCommitCount(int user_id) {
        String hql = "SELECT count(commit.id) as commitCount FROM Commit commit INNER JOIN commit.scores as score INNER JOIN score.user as user WHERE user.id=:user_id GROUP BY commit.id";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("user_id",user_id);
        List result =  query.list();
        return result.size();
    }
}
