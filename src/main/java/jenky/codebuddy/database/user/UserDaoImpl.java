package jenky.codebuddy.database.user;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.Authentication;
import jenky.codebuddy.models.entities.Project;
import jenky.codebuddy.models.entities.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Perisstence of VerificationDao. Inherits GenericDaoImpl and implements VerificationDao interface
 */
@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements UserDao {

    /**
     * Get all the projects
     * @return List containing all the projects.
     */

    @Override
    public List<User> getAllUsers() {
        return super.findAll();
    }

    /**
     * Check if the given project exists or not.
     * @param email
     * @return true or false
     */
    @Override
    public boolean checkIfUserExists(String email) {
        String hql = "FROM User u WHERE u.email = :user_email";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("user_email",email);
        Optional<User> result = Optional.ofNullable((User) query.uniqueResult());
        return (result.isPresent());
    }

    @Override
    public User getUserIfExists(String email) {
        String hql = "FROM User u WHERE u.email = :user_email";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("user_email",email);
        Optional<User> result = Optional.ofNullable((User) query.uniqueResult());
        return(result.get());
    }

    @Override
    public void setPasswordForUser(String password, String userEmail, Date updatedAt) {
        String hql = "UPDATE User u SET password =:password, updated_at =:updated_at WHERE u.email =:user_email";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("password",password);
        query.setParameter("updated_at",updatedAt);
        query.setParameter("user_email",userEmail);
        query.executeUpdate();
    }


    /**
     * Saves the project.
     * @param user
     */
    @Override
    public void save(User user) {
        super.add(user);
    }

    /**
     * Saves or updates the user.
     * @param user
     */
    @Override
    public void saveOrUpdate(User user) {
        super.saveOrUpdate(user);
    }
}
