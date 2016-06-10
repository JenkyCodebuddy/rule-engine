package jenky.codebuddy.database.verification;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.Verification;

/**
 * Defines the specific methods VerificationDao must have.
 */
public interface VerificationDao extends GenericDao<Verification> {
    public void addNewVerification(Verification verification);

    public void removeVerification(Verification verification);

    public void updateVerficication(Verification verification);

    public boolean checkIfVerificationExists(String verificationCode);

    public Verification getVerificationIfExists(String verificationCode);
}
