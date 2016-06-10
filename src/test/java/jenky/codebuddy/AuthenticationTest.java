package jenky.codebuddy;

import jenky.codebuddy.database.authentication.AuthenticationServiceImpl;
import jenky.codebuddy.database.score.ScoreServiceImpl;
import jenky.codebuddy.models.entities.Authentication;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.services.AuthenticationService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Fabian on 10-6-2016.
 */
public class AuthenticationTest {


    private ApplicationContext context;
    private AuthenticationServiceImpl authenticationService;

    private Authentication authentication;

    public AuthenticationTest(){
        this.context = (new ClassPathXmlApplicationContext("spring.xml"));
        this.authenticationService = (AuthenticationServiceImpl) getContext().getBean("authenticationServiceImpl");

        User testUser = new User();
        testUser.setEmail(TestInfo.TESTEMAIL);
        this.authentication = new Authentication();
        this.authentication.setUser(testUser);
    }

    @Test
    @Transactional
    @Rollback
    public void saveAuthentication() throws Exception{
        authenticationService.saveAuthentication(this.authentication);
    }

    @Test
    @Transactional
    public void checkIfAuthenticationForUserExists() throws Exception{
        assertNotNull(authenticationService.checkIfAuthenticationForUserExists(TestInfo.TESTID));
    }

    @Test
    @Transactional
    @Rollback
    public void updateAuthentication() throws Exception{
        authenticationService.updateAuthentication(TestInfo.TESTID, TestInfo.TESTTOKEN, TestInfo.TESTKEY, TestInfo.TESTDATE);
    }

    @Test
    @Transactional
    public void checkifAuthentactionTokenExists() throws Exception{
        assertNotNull(authenticationService.checkIfAuthenticationForUserExists(TestInfo.TESTID));
    }

    @Test
    @Transactional
    public void getAuthenticationTokenIfExists() throws Exception{
        assertNotNull(authenticationService.getAuthenticationIfTokenExists(TestInfo.TESTTOKEN));
    }


    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    public AuthenticationServiceImpl getAuthenticationService() {
        return authenticationService;
    }
}
