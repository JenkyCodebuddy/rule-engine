package jenky.codebuddy;

import jenky.codebuddy.models.restModels.Commit;
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
            t.testModel();
        }
        catch(Exception e){

        }
    }

    @Test
    public void bla() throws Exception {
        assertEquals(1,1);
    }

    @Test
    public void testModel() throws Exception{
        Commit c = new Commit("Joost","joost1235@hotmail.com","master","asdasd12113ui1h3ir","cyka/TestclassForDAO/idi/nahui");
        assertNotEquals(c.getEmail(),"");
    }
}

