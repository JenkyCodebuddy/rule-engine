package jenky.codebuddy.services;

import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.rest.Response;
import jenky.codebuddy.token.models.Token;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by joost on 12-6-2016.
 */
public class UserLoginServiceImplTest {

    private static UserLoginService userLoginServiceImpl;
    private static String testEmail;
    private static User testUser;

    @BeforeClass
    public static void setup(){
        userLoginServiceImpl = new UserLoginServiceImpl();
        testEmail = "test@123.com";
        testUser = new User();
        testUser.setPassword("testPassword");
        testUser.setEmail("testEmail");
    }

    @Test
    public void testTokenGenerator(){
        Token t = userLoginServiceImpl.generateToken(testEmail);
        Assert.assertNotNull(t);
        Assert.assertNotNull(t.getId());
        Assert.assertNotNull(t.getKey());
        Assert.assertNotNull(t.getToken());
    }

    @Test
    @Rollback
    @Transactional
    public void testLogin(){
        DatabaseFactory.getUserService().save(testUser);
        Response response = userLoginServiceImpl.login(testUser.getEmail(), testUser.getPassword());
        Assert.assertEquals(response.getResponseCode(),200);
    }
}
