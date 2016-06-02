package jenky.codebuddy.database.verification;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.entities.Verification;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Perisstence of VerificationDao. Inherits GenericDaoImpl and implements VerificationDao interface
 */
@Repository
public class VerificationDaoImpl extends GenericDaoImpl<Verification, Integer> implements VerificationDao {

    @Override
    public void addNewVerification(Verification verification) {
        super.add(verification);
    }

    @Override
    public void removeVerification(Verification verification) {
        super.delete(verification);
    }

    @Override
    public void updateVerficication(Verification verification) {
        super.update(verification);
    }

}
