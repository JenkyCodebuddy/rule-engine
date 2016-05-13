package jenky.codebuddy;

import org.hibernate.validator.constraints.URL;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;

public class RestClientTest {
    private RestClient restclient;

    public RestClientTest(){
        restclient = new RestClient("admin","admin");
    }

    @Test
    public void testURL(){
        String strUrl = "/sonar/api/";

        try {
           restclient.get(strUrl);

        } catch (Exception e) {
            System.err.println("Error creating HTTP connection");
            e.printStackTrace();
            throw e;
        }
    }

}
