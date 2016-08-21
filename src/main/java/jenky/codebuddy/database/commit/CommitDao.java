package jenky.codebuddy.database.commit;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.Commit;

import java.util.List;
import java.util.Map;

/**
 * Specific methods for CommitDao
 */
public interface CommitDao extends GenericDao<Commit, Integer> {

    /**
     * @return List<Commit>
     */
    public List<Commit> getCommits();

    /**
     * @param commit
     */
    public void saveCommit(Commit commit);

    /**
     * @param user_id
     * @return List<Commit> from the user
     */
    public List<Commit> getCommitsFromUserForProfile(int user_id);

    public List<Map<String,Double>> getSonarValuesFromLastCommits(int user_id);

    public double getUserCommitCount(int user_id);

    public void deleteCommit(Commit commit);
}
