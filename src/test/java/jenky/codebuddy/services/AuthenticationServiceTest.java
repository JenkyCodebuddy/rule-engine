package jenky.codebuddy.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import jenky.codebuddy.TestInfo;
import jenky.codebuddy.models.entities.Authentication;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.token.models.Token;
import org.hibernate.boot.model.relational.Database;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.security.Key;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Joost on 20-8-2016.
 */
public class AuthenticationServiceTest {

    private User user;

    @Before
    public void setUp(){
        user = DatabaseFactory.getUserService().getUserIfExists(TestInfo.TESTEMAIL);
    }

    @Test
    public void checkIfTokenIsValid() throws Exception {
        assertTrue(AuthenticationService.checkIfTokenIsValid(user.getAuthentication().getToken()));
    }

    @Test
    public void stringToKey() throws Exception {
        //not tested on purpose
    }

    @Test
    public void keyToString() throws Exception {
        //not tested on purpose
    }
}