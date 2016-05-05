package jenky.codebuddy;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by joost on 5-5-2016.
 */
public class httpRequestTest {
    httpRequest http = new httpRequest();
    String user = http.username;
    String password = http.password;

    @Test
    public void testConnection(){
        try{
            assertNotNull(http.sendPost());
        }
        catch(Exception e){

        }
    }
    @Test
    public void checkCredentialsNotEmpty(){
        assertNotEquals(user, "");
        assertNotEquals(password, "");
    }

    @Test
    public void checkCredentialsValid(){
        assertEquals(user, "admin");
        assertEquals(password, "CC4d96");
    }
}