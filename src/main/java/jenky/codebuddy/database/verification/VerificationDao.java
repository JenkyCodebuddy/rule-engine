package jenky.codebuddy.database.verification;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.Verification;

/**
 * Defines the specific methods VerificationDao must have.
 */
public interface VerificationDao extends GenericDao<Verification, Integer> {
    /**
     * @param verification
     */
    public void addNewVerification(Verification verification);

    /**
     * @param verification
     */
    public void removeVerification(Verification verification);

    /**
     * @param verification
     */
    public void updateVerficication(Verification verification);

    /**
     * @param verificationCode
     * @return boolean
     */
    public boolean checkIfVerificationExists(String verificationCode);

    /**
     * @param verificationCode
     * @return Verification otherwise null
     */
    public Verification getVerificationIfExists(String verificationCode);
}
