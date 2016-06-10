package jenky.codebuddy.database.verification;

import jenky.codebuddy.database.generic.GenericServiceImpl;
import jenky.codebuddy.models.entities.Verification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service layer of Profile. Inherits the GenericService and implements the VerificationService interface.
 */
@Service
public class VerificationServiceImpl extends GenericServiceImpl<Verification, Integer> implements VerificationService {

    private VerificationDao verificationDao;

    @Autowired
    public VerificationServiceImpl(@Qualifier("verificationDaoImpl") VerificationDao verificationDao) {
        this.verificationDao = verificationDao;
    }

    public VerificationServiceImpl(){

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addNewVerfication(Verification verification) {
        verificationDao.addNewVerification(verification);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeVerification(Verification verification) {
        verificationDao.removeVerification(verification);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateVerification(Verification verification) {
        verificationDao.updateVerficication(verification);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public boolean checkIfVerificationExists(String verificationCode){
        return verificationDao.checkIfVerificationExists(verificationCode);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Verification getVerificationIfExists(String verificationCode){
        return verificationDao.getVerificationIfExists(verificationCode);
    }
}
