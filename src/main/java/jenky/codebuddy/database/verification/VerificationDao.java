package jenky.codebuddy.database.verification;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.entities.Verification;

import java.util.List;

/**
 * Defines the specific methods VerificationDao must have.
 */
public interface VerificationDao extends GenericDao<Verification, Integer> {
    public void addNewVerification(Verification verification);

    public void removeVerification(Verification verification);

    public void updateVerficication(Verification verification);
}
