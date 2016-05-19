/*
package jenky.codebuddy;

import org.hibernate.validator.constraints.URL;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.HttpURLConnection;

public class RestClientTest {
    RestClient noCredentialsClient = new RestClient("","");
    RestClient wrongCredentialsClient = new RestClient("foo","bar");
    RestClient sonarqubeCredentialsClient = new RestClient("admin","CC4c96d");
    RestClient githubCredentialsClient = new RestClient("CodebuddyTest","dekatkrabtdekrullenvandetrap");
    public static void main(String[] args) {
        RestClientTest t = new RestClientTest();
        try{
            t.testGet();
        }
        catch(Exception e){

        }
    }


    @Test
    public void testGet() throws Exception {
        assertEquals(200, noCredentialsClient.getStatus());
        assertEquals(200, wrongCredentialsClient.getStatus());
        assertEquals(200, sonarqubeCredentialsClient.getStatus());
        assertEquals(200, githubCredentialsClient.getStatus());
    }

    @Test
    public void testPost() throws Exception {

    }
}
*/
