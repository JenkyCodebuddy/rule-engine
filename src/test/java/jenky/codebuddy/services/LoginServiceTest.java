package jenky.codebuddy.services;

import jenky.codebuddy.TestInfo;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.rest.Response;
import jenky.codebuddy.token.models.Token;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Joost on 20-8-2016.
 */
public class LoginServiceTest {

    private User user;
    private LoginService loginService;

    @Before
    public void setup(){
        user = DatabaseFactory.getUserService().getUserIfExists(TestInfo.TESTEMAIL);
        loginService = new LoginService();
    }
    @Test
    public void login() throws Exception {
        Response response = loginService.login(user.getEmail(), user.getPassword());
        assertEquals(200, response.getResponseCode());
    }

    @Test
    public void generateToken() throws Exception {
        Token token = loginService.generateToken(user.getEmail());
        assertNotNull(token);
    }
}