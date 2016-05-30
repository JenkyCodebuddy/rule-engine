package jenky.codebuddy.database.dao.generic.commit;

import jenky.codebuddy.database.dao.generic.GenericServiceImpl;
import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by joost on 30-5-2016.
 */
@Service
public class CommitServiceImpl extends GenericServiceImpl<Commit, Integer> implements CommitService {

    private CommitDao commitDao;

    @Autowired
    public CommitServiceImpl(@Qualifier("commitDaoImpl") CommitDao commitDao) {
        this.commitDao = commitDao;
    }

    public CommitServiceImpl(){

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Commit> getCommits(){
        return commitDao.getCommits();
    }
}
