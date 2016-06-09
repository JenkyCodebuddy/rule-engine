package jenky.codebuddy.database.commit;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public void saveCommit(Commit commit){
        super.add(commit);
    }

    @Override
    public List<Commit> getCommitsFromUser(int user_id){
        String hql = "SELECT project.name, commit.branch, commit.created_at, commit.id FROM Commit commit " +
                "INNER JOIN commit.project as project " +
                "INNER JOIN commit.scores as scores " +
                "WHERE scores.user =:user_id " +
                "GROUP BY commit.id " +
                "ORDER BY commit.id DESC ";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("user_id",user_id);
        query.setMaxResults(3);
        List<Commit> result = query.list();
        return result;
    }
}
