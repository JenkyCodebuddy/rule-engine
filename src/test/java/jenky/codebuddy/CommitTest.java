package jenky.codebuddy;

import jenky.codebuddy.database.authentication.AuthenticationServiceImpl;
import jenky.codebuddy.database.commit.CommitServiceImpl;
import jenky.codebuddy.models.entities.Commit;
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

    private ApplicationContext context;
    private CommitServiceImpl commitService;

    Commit commit;

    public CommitTest() {
        this.context = (new ClassPathXmlApplicationContext("spring.xml"));
        this.commitService = (CommitServiceImpl) getContext().getBean("commitServiceImpl");

        this.commit = new Commit();
        this.commit.setCreated_at(TestInfo.TESTDATE);
    }

    @Test
    @Transactional
    public void getAllCommits() throws Exception{
        assertNotNull(commitService.getCommits());
    }

    @Test
    @Transactional
    @Rollback
    public void saveCommit() throws Exception{
        commitService.saveCommit(this.commit);
    }

    @Test
    @Transactional
    public void getCommitsFromUser(){
        assertNotNull(commitService.getCommitsFromUser(TestInfo.TESTID));
    }



    public ApplicationContext getContext() {
        return context;
    }



}

