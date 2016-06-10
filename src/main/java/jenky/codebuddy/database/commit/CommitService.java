package jenky.codebuddy.database.commit;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.Commit;

import java.util.List;

/**
 * Specific methods for the CommitService. Extends the GenericService interface
 */
public interface CommitService extends GenericService<Commit, Integer> {
    public List<Commit> getCommits();

    public void saveCommit(Commit commit);

    public List<Commit> getCommitsFromUser(int user_id);
}
