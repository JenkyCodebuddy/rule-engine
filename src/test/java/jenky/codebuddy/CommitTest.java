package jenky.codebuddy;

import jenky.codebuddy.database.authentication.AuthenticationServiceImpl;
import jenky.codebuddy.database.commit.CommitServiceImpl;
import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.services.DatabaseFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;


/**
 * Created by Fabian on 10-6-2016.
 */
public class CommitTest {

    private Commit commit;

    @Before
    public void setUp() {
        this.commit = new Commit();
        this.commit.setCreated_at(TestInfo.TESTDATE);
    }

    @Test
    @Transactional
    public void getAllCommits() throws Exception{
        assertNotNull(DatabaseFactory.getCommitService().getCommits());
    }

    @Test
    @Transactional
    @Rollback
    public void saveCommit() throws Exception{
        DatabaseFactory.getCommitService().saveCommit(this.commit);
    }

    @Test
    @Transactional
    public void getCommitsFromUser(){
        assertNotNull(DatabaseFactory.getCommitService().getCommitsFromUser(TestInfo.TESTID));
    }




}

