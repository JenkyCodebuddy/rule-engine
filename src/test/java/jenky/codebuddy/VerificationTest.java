package jenky.codebuddy;

import jenky.codebuddy.models.entities.Verification;
import jenky.codebuddy.services.DatabaseFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;


/**
 * Created by Fabian on 10-6-2016.
 */
public class VerificationTest {

    Verification verification;

    @Before
    public void setUp(){
        this.verification = new Verification();
        this.verification.setCode(TestInfo.TESTTOKEN);
    }

    @Test
    @Transactional
    public void checkIfVerificationExists() throws Exception{
        assertNotNull(DatabaseFactory.getVerificationService().checkIfVerificationExists(TestInfo.TESTTOKEN));
    }

    @Test
    @Transactional
    @Rollback
    public void saveVerification() throws Exception{
        DatabaseFactory.getVerificationService().addNewVerfication(this.verification);
    }
}
