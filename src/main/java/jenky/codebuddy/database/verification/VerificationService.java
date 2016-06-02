package jenky.codebuddy.database.verification;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.entities.Verification;

import java.util.List;



public interface VerificationService extends GenericService<Verification, Integer> {
    public void addNewVerfication(Verification verification);

    public void removeVerification(Verification verification);

    public void updateVerification(Verification verification);
}
