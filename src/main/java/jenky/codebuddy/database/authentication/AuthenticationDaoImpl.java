package jenky.codebuddy.database.authentication;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.Authentication;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

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
    public void updateAuthentication(int userId, String token, String keyString, Date updated_at) {
        String hql = "update Authentication a set token = :token, auth_key = :keyString, updated_at=:updated_at WHERE a.user.user_id = :userId";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("token",token);
        query.setParameter("keyString",keyString);
        query.setParameter("userId",userId);
        query.setParameter("updated_at",updated_at);
        query.executeUpdate();
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

    /**
     * @param token
     * @return String token otherwise null
     */
    @Override
    public Authentication getAuthenticationIfTokenExists(String token) {
        String hql = "FROM Authentication a WHERE a.token = :token";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("token",token);
        Authentication result = (Authentication) query.uniqueResult();
        if(result != null){
            return result;
        }
        else{
            return null;
        }
    }
}
