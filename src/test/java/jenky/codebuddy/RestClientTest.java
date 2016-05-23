package jenky.codebuddy;

import org.hibernate.validator.constraints.URL;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.HttpURLConnection;

public class RestClientTest {
    public static void main(String[] args) {
        RestClientTest t = new RestClientTest();
        try{
            t.bla();
        }
        catch(Exception e){

        }
    }

    @Test
    public void bla() throws Exception {
        assertEquals(1,1);
    }
}

