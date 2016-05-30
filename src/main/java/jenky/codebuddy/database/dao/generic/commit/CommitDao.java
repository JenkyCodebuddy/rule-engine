package jenky.codebuddy.database.dao.generic.commit;

import jenky.codebuddy.database.dao.generic.GenericDao;
import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.User;

import java.util.List;

/**
 * Created by joost on 30-5-2016.
 */
public interface CommitDao extends GenericDao<Commit, Integer> {
    public List<Commit> getCommits();
}
