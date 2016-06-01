package jenky.codebuddy.database.user;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.Project;
import jenky.codebuddy.models.entities.User;

import java.util.List;

/**
 * Defines the specific methods VerificationDao must have.
 */
public interface UserDao extends GenericDao<User, Integer> {
    public List<User> getAllUsers();

    public void saveUser(User user);

    public boolean checkIfUserExists(String userEmail);

    public User getUserIfExists(String userEmail);
}
