package jenky.codebuddy.database.commit;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.Commit;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by joost on 30-5-2016.
 */
@Repository
public class CommitDaoImpl extends GenericDaoImpl<Commit, Integer> implements CommitDao {

    @Override
    public List<Commit> getCommits() {
        List<Commit> commits = super.findAll();
        return commits;
    }

    @Override
    public void saveCommit(Commit commit){
        super.add(commit);
    }
}
