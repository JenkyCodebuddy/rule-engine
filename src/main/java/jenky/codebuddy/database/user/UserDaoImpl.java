package jenky.codebuddy.database.user;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.Project;
import jenky.codebuddy.models.entities.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        User result = (User) query.uniqueResult();
        return (result != null);
    }

    @Override
    public User getUserIfExists(String email) {
        String hql = "FROM User u WHERE u.email = :user_email";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("user_email",email);
        User result = (User) query.uniqueResult();
        if(result != null){
            return result;
        }
        else{
            return null;
        }
    }

    /**
     * Saves the project.
     * @param user
     */
    @Override
    public void save(User user) {
        super.add(user);
    }
}
