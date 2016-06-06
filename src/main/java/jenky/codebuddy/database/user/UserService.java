package jenky.codebuddy.database.user;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.Project;
import jenky.codebuddy.models.entities.User;

import java.util.List;

/**
 * This interface specifies the methods ProjectSerivce must have.
 */
public interface UserService extends GenericService<User, Integer> {
    public List<User> getAllUsers();

    public void save(User user);

    public boolean checkIfUserExists(String user);

    public User getUserIfExists(String email);

    public void saveOrUpdate(User user);

}
