package jenky.codebuddy.database.verification;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.Verification;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Perisstence of VerificationDao. Inherits GenericDaoImpl and implements VerificationDao interface
 */
@Repository
public class VerificationDaoImpl extends GenericDaoImpl<Verification, Integer> implements VerificationDao {

    /**
     * @param verification
     */
    @Override
    public void addNewVerification(Verification verification) {
        super.add(verification);
    }

    /**
     * @param verification
     */
    @Override
    public void removeVerification(Verification verification) {
        super.delete(verification);
    }

    /**
     * @param verification
     */
    @Override
    public void updateVerficication(Verification verification) {
        super.update(verification);
    }

    /**
     * @param verficationCode
     * @return boolean
     */
    @Override
    public boolean checkIfVerificationExists(String verficationCode){
        String hql = "FROM Verification v WHERE v.code = :verification_code";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("verification_code",verficationCode);
        Optional<Verification> result = Optional.ofNullable((Verification) query.uniqueResult());
        return result.isPresent();
    }

    /**
     * @param verficationCode
     * @return Verification otherwise null
     */
    @Override
    public Verification getVerificationIfExists(String verficationCode){
        String hql = "FROM Verification v WHERE v.code = :verification_code";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("verification_code",verficationCode);
        Optional<Verification> result = Optional.ofNullable((Verification) query.uniqueResult());
        return result.get();
    }

}
