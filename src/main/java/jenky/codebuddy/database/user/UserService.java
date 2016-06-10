package jenky.codebuddy.database.user;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.User;

import java.util.Date;
import java.util.List;

/**
 * This interface specifies the methods ProjectSerivce must have.
 */
public interface UserService extends GenericService<User, Integer> {
    public List<User> getAllUsers();

    public void save(User user);

    public boolean checkIfUserExists(String user);

    public User getUserIfExists(String email);

    public void setPasswordForUser(String password, String email, Date updatedAt);

    public void saveOrUpdate(User user);

    public boolean checkIfUserDoesntHaveItem(int user_id, int item_id);

    public boolean checkIfUserHasEnoughCoins(int user_id, double amount);

    public void subtractCoins(int user_id, double amount);

}
