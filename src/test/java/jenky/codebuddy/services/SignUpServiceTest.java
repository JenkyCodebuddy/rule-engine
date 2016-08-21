package jenky.codebuddy.services;

import jenky.codebuddy.TestInfo;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.rest.Response;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Joost on 20-8-2016.
 */
public class SignUpServiceTest {

    private User user;
    private SignUpService signUpService;
    private Response response;

    @Before
    public void setup(){
        user = DatabaseFactory.getUserService().getUserIfExists(TestInfo.TESTEMAIL);
        signUpService = new SignUpService();
    }
    @Test
    public void signUpNewUser() throws Exception {
        response = signUpService.signUpNewUser("signuptest@hotmail.com");
        assertEquals(200, response.getResponseCode());
    }

    @Test
    public void checkVerificationCode() throws Exception {
        response = signUpService.checkVerificationCode(DatabaseFactory.getUserService().getUserIfExists("signuptest@hotmail.com").getVerfication().getCode(), "signuptestpassword");
        assertEquals(200, response.getResponseCode());
    }

    @AfterClass
    public static void removeTestUser(){
        DatabaseFactory.getUserService().deleteUser(DatabaseFactory.getUserService().getUserIfExists("signuptest@hotmail.com"));
    }
}