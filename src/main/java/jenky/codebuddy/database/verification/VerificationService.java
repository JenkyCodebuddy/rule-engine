package jenky.codebuddy.database.verification;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.Verification;



public interface VerificationService extends GenericService<Verification, Integer> {
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
    public void updateVerification(Verification verification);

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
