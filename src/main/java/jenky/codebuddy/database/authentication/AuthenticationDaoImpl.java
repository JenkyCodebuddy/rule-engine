package jenky.codebuddy.database.authentication;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.Authentication;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Persistence for Achievement. This inherits GenericDaoImplementation
 */
@Repository
public class AuthenticationDaoImpl extends GenericDaoImpl<Authentication, Integer> implements AuthenticationDao {

    @Override
    public void saveAuthentication(Authentication authentication) {
        super.add(authentication);
    }

    /**
     * @param userId
     * @return int userId
     */
    @Override
    public boolean checkIfAuthenticationForUserExists(int userId) {
        String hql = "FROM Authentication a WHERE a.user.user_id = :userid";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("userid",userId);
        Authentication result = (Authentication) query.uniqueResult();
        return result!=null;
    }

    /**
     * @param userId
     * @param token
     * @param keyString
     * @param updated_at
     */
    @Override
    public void updateAuthentication(Authentication authentication) {
        super.saveOrUpdate(authentication);
    }

    /**
     * @param token
     * @return boolean
     */
    @Override
    public boolean checkIfTokenExists(String token){
        String hql = "FROM Authentication a WHERE a.token = :token";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("token",token);
        Authentication result = (Authentication) query.uniqueResult();
        return result!=null;
    }

    @Override
    public Authentication getAuthenticationIfTokenExists(String token) {
        String hql = "FROM Authentication a WHERE a.token = :token";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("token",token);
        Optional<Authentication> result = Optional.ofNullable((Authentication) query.uniqueResult());
        return result.isPresent() ? result.get() : null;
    }

    @Override
    public Authentication getAuthenticationIfUserExists(int user_id) {
        String hql = "FROM Authentication a WHERE a.user = :user_id";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("user_id",user_id);
        Optional<Authentication> result = Optional.ofNullable((Authentication) query.uniqueResult());
        return result.isPresent() ? result.get() : null;
    }

    @Override
    public void deleteAuthentication(Authentication authentication){
        super.delete(authentication);
    }
}
