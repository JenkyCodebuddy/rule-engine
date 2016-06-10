package jenky.codebuddy;

import io.jsonwebtoken.impl.crypto.MacProvider;
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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

import static org.junit.Assert.assertEquals;
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
    public void stringToKey() throws Exception{
        assertEquals(javax.crypto.spec.SecretKeySpec.class,  AuthenticationService.stringToKey(TestInfo.TESTKEYSTRING).getClass());
    }

    @Test
    public void KeyToString() throws Exception{
        System.out.println(AuthenticationService.keyToString(TestInfo.TESTKEY));
        assertEquals(String.class,  AuthenticationService.keyToString(TestInfo.TESTKEY).getClass());
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
        authenticationService.updateAuthentication(TestInfo.TESTID, TestInfo.TESTTOKEN, TestInfo.TESTKEYSTRING, TestInfo.TESTDATE);
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
