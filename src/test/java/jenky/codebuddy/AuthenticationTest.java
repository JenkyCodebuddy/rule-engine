package jenky.codebuddy;

import jenky.codebuddy.models.entities.Authentication;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.services.UserAuthenticationService;
import jenky.codebuddy.services.DatabaseFactory;
import jenky.codebuddy.services.UserLoginServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Fabian on 10-6-2016.
 */
public class AuthenticationTest {

    private Authentication authentication;

    @Before
    public void setUp(){
        User testUser = new User();
        testUser.setEmail(TestInfo.TESTEMAIL);
        this.authentication = new Authentication();
        this.authentication.setUser(testUser);
    }

    @Test
    public void stringToKey() throws Exception{
        assertEquals(javax.crypto.spec.SecretKeySpec.class,  UserAuthenticationService.stringToKey(TestInfo.TESTKEYSTRING).getClass());
    }

    @Test
    public void KeyToString() throws Exception{
        System.out.println(UserAuthenticationService.keyToString(TestInfo.TESTKEY));
        assertEquals(String.class,  UserAuthenticationService.keyToString(TestInfo.TESTKEY).getClass());
    }

    @Test
    public void createJwtToken() throws Exception{
        UserLoginServiceImpl userLoginServiceImpl = new UserLoginServiceImpl();
        assertNotNull(userLoginServiceImpl.generateToken(TestInfo.TESTEMAIL));
    }


    @Test
    @Transactional
    @Rollback
    public void saveAuthentication() throws Exception{
        DatabaseFactory.getAuthenticationService().saveAuthentication(this.authentication);
    }

    @Test
    @Transactional
    public void checkIfAuthenticationForUserExists() throws Exception{
        assertNotNull(DatabaseFactory.getAuthenticationService().checkIfAuthenticationForUserExists(TestInfo.TESTID));
    }

    @Test
    @Transactional
    @Rollback
    public void updateAuthentication() throws Exception{
        DatabaseFactory.getAuthenticationService().updateAuthentication(TestInfo.TESTID, TestInfo.TESTTOKEN, TestInfo.TESTKEYSTRING, TestInfo.TESTDATE);
    }

    @Test
    @Transactional
    public void checkifAuthentactionTokenExists() throws Exception{
        assertNotNull(DatabaseFactory.getAuthenticationService().checkIfAuthenticationForUserExists(TestInfo.TESTID));
    }

    @Test
    @Transactional
    public void getAuthenticationTokenIfExists() throws Exception{
        assertNotNull(DatabaseFactory.getAuthenticationService().getAuthenticationIfTokenExists(TestInfo.TESTTOKEN));
    }

}
