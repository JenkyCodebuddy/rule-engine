package jenky.codebuddy.database.commit;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.Commit;

import java.util.List;

/**
 * Specific methods for CommitDao
 */
public interface CommitDao extends GenericDao<Commit, Integer> {
    public List<Commit> getCommits();

    public void saveCommit(Commit commit);
}
