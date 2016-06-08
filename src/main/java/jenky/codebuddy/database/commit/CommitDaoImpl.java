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
        String hql = "FROM Commit commit " +
                "INNER JOIN Project project " +
                "ON commit.project = project.id " +
                "INNER JOIN Score score " +
                "ON commit.id = score.commit.id " +
                "WHERE score.user.id = :user_id " +
                "GROUP BY commit.id " +
                "order by commit.id DESC";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
       // query.setParameter("user_id",user_id);
        List<Commit> result = query.list();
        return result;
    }
}
