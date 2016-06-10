package jenky.codebuddy.database.user;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.User;

import java.util.Date;
import java.util.List;

/**
 * Defines the specific methods VerificationDao must have.
 */
public interface UserDao extends GenericDao<User, Integer> {
    public List<User> getAllUsers();

    public void save(User user);

    public boolean checkIfUserExists(String userEmail);

    public User getUserIfExists(String userEmail);

    public void saveOrUpdate(User user);


    public void setPasswordForUser(String password, String email, Date updatedAt);

}
