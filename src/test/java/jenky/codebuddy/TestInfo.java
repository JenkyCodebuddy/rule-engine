package jenky.codebuddy;

import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Date;

/**
 * Created by Fabian on 10-6-2016.
 */
public class TestInfo {

    public static final String TESTEMAIL = "testuser";
    public static final String TESTPASSWORD = "test";
    public static final int TESTJENKYCOINS = 10000;

    public static final String TESTACHIEVEMENTDESCRIPTION = "This is a test achievement for testing purposes";
    public static final String TESTACHIEVEMENTNAME= "Test achievement";
    public static final int TESTACHIEVEMENTPROGRESS= 0;

    public static final String TESTPROJECTNAME="Test project";

    public static final int TESTSCORE = 420;

    public static String TESTITEMNAME = "Test item";
    public static String TESTITEMTYPE="Test item type";
    public static String TESTITEMIMAGE="Test item image";
    public static int TESTITEMPRICE= 100;

    public static Date TESTDATE = new Date();
    public static Integer TESTID = 9000;



    TestInfo(){

    }
}
