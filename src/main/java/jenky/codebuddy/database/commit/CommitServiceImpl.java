package jenky.codebuddy.database.commit;

import jenky.codebuddy.database.generic.GenericServiceImpl;
import jenky.codebuddy.models.entities.Commit;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer for UserCommit. Extends GenericService and implements CommitService.
 */
@Service
public class CommitServiceImpl extends GenericServiceImpl<Commit, Integer> implements CommitService {

    private CommitDao commitDao;

    /**
     * Injected by Spring
     * @param commitDao
     */
    @Autowired
    public CommitServiceImpl(@Qualifier("commitDaoImpl") CommitDao commitDao) {
        this.commitDao = commitDao;
    }

    public CommitServiceImpl(){

    }

    /**
     * Ask commitDao to get all the commits
     * Transaction is managed by Spring
     * @return List cof commits
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Commit> getCommits(){
        return commitDao.getCommits();
    }

    /**
     * Ask commitDao to save te commit
     * Transaction is managed by Spring
     * @param commit
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveCommit(Commit commit) {
        commitDao.saveCommit(commit);
    }

    /**
     * Ask commitDao to get the commits from the user
     * Transaction is managed by Spring
     * @param user_id
     * @return List<Commit> from the user otherwise null
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Commit> getCommitsFromUser(int user_id) {
        Hibernate.initialize(commitDao.getCommitsFromUser(user_id));
        return commitDao.getCommitsFromUser(user_id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public double getUserCommitCount(int user_id) {
        return commitDao.getUserCommitCount(user_id);
    }
}
