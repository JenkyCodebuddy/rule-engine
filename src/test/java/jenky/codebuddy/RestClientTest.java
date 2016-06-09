package jenky.codebuddy;

import jenky.codebuddy.models.rest.UserCommit;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
        UserCommit c = new UserCommit("Joost","joost1235@hotmail.com","master","asdasd12113ui1h3ir","cyka/TestclassForDAO/idi/nahui");
        assertNotEquals(c.getEmail(),"");
    }
}

