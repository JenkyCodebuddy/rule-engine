package jenky.codebuddy;

import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Date;

/**
 * Created by Fabian on 10-6-2016.
 */
public class TestInfo {
    static final String TESTEMAIL = "joost1235@hotmail.com";
    static final String TESTJSON = "[{\"id\":1,\"key\":\"jenky:codebuddy.rule-engine\",\"name\":\"rule-engine\",\"scope\":\"PRJ\",\"qualifier\":\"TRK\",\"date\":\"2016-06-10T20:31:27+0200\",\"creationDate\":\"2016-04-07T19:28:16+0200\",\"lname\":\"rule-engine\",\"version\":\"1.0\",\"description\":\"\",\"msr\":[{\"key\":\"ncloc\",\"val\":3900.0,\"frmt_val\":\"3,900\"},{\"key\":\"comment_lines\",\"val\":333.0,\"frmt_val\":\"333\"},{\"key\":\"comment_lines_density\",\"val\":7.9,\"frmt_val\":\"7.9%\"},{\"key\":\"complexity\",\"val\":428.0,\"frmt_val\":\"428\"},{\"key\":\"tests\",\"val\":2.0,\"frmt_val\":\"2\"},{\"key\":\"test_errors\",\"val\":0.0,\"frmt_val\":\"0\"},{\"key\":\"test_failures\",\"val\":0.0,\"frmt_val\":\"0\"},{\"key\":\"coverage\",\"val\":0.5,\"frmt_val\":\"0.5%\"},{\"key\":\"duplicated_lines\",\"val\":297.0,\"frmt_val\":\"297\"},{\"key\":\"duplicated_lines_density\",\"val\":5.1,\"frmt_val\":\"5.1%\"},{\"key\":\"violations\",\"val\":362.0,\"frmt_val\":\"362\"},{\"key\":\"blocker_violations\",\"val\":0.0,\"frmt_val\":\"0\"},{\"key\":\"critical_violations\",\"val\":8.0,\"frmt_val\":\"8\"},{\"key\":\"major_violations\",\"val\":74.0,\"frmt_val\":\"74\"},{\"key\":\"minor_violations\",\"val\":279.0,\"frmt_val\":\"279\"},{\"key\":\"sqale_index\",\"val\":3086.0,\"frmt_val\":\"6d 3h\"}]}]";
    static final String TESTPROJECT = "test engine";
    static final String TESTPASSWORD = "test";
    static final String TESTMETRIC = "test metric";
    static final Key TESTKEY = MacProvider.generateKey();

    static String TESTTOKEN = "124v24vrd45g345346b5y45y45";
    static String TESTKEYSTRING = "rO0ABXNyAB9qYXZheC5jcnlwdG8uc3BlYy5TZWNyZXRLZXlTcGVjW0cLZuIwYU0CAAJMAAlhbGdvcml0aG10ABJMamF2YS9sYW5nL1N0cmluZztbAANrZXl0AAJbQnhwdAAKSG1hY1NIQTUxMnVyAAJbQqzzF/gGCFTgAgAAeHAAAABAEAv3R4h9zOk0YWiGZQIQyq7qEh8DcDIn0ETve4gwmZWW8EqQcirZRsjMFPTOGj+nXCJHwCA815PRoAayIt9Wtw==";
    static Date TESTDATE = new Date();
    static Integer TESTID = 1;

    TestInfo(){

    }
}
