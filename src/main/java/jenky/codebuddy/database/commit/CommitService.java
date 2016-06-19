package jenky.codebuddy.database.commit;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.Commit;

import java.util.List;

/**
 * Specific methods for the CommitService. Extends the GenericService interface
 */
public interface CommitService extends GenericService<Commit, Integer> {
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

    public List<List<Double>> getSonarValuesFromLastCommits(int user_id);

    public double getUserCommitCount(int user_id);
}
