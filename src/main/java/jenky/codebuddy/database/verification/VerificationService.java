package jenky.codebuddy.database.verification;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.Verification;



public interface VerificationService extends GenericService<Verification> {
    public void addNewVerfication(Verification verification);

    public void removeVerification(Verification verification);

    public void updateVerification(Verification verification);

    public boolean checkIfVerificationExists(String verificationCode);

    public Verification getVerificationIfExists(String verificationCode);
}
