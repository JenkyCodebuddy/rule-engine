package jenky.codebuddy;

import jenky.codebuddy.services.DatabaseFactory;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;


/**
 * Created by Fabian on 10-6-2016.
 */
public class UserTest {

    @Test
    @Transactional
    public void getAllUsers() throws Exception{
        assertNotNull(DatabaseFactory.getUserService().getAllUsers());
    }

    @Test
    @Transactional
    public void checkIfUserExists() throws Exception{
        assertNotNull(DatabaseFactory.getUserService().checkIfUserExists(TestInfo.TESTEMAIL));
    }

    @Test
    @Transactional
    public void getUserIfExists() throws Exception{
        assertNotNull(DatabaseFactory.getUserService().getUserIfExists(TestInfo.TESTEMAIL));
    }

    @Test
    @Transactional
    @Rollback
    public void setPasswordForUser(){
        DatabaseFactory.getUserService().setPasswordForUser(TestInfo.TESTPASSWORD, TestInfo.TESTEMAIL, TestInfo.TESTDATE);
    }
}
