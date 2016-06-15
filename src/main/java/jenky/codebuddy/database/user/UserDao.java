package jenky.codebuddy.database.user;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.User;

import java.util.Date;
import java.util.List;

/**
 * Defines the specific methods UserDao must have.
 */
public interface UserDao extends GenericDao<User, Integer> {
    /**
     * @return List of all users
     */
    public List<User> getAllUsers();

    /**
     * @param user
     */
    public void save(User user);

    /**
     * @param userEmail
     * @return boolean
     */
    public boolean checkIfUserExists(String userEmail);

    /**
     * @param userEmail
     * @return User otherwise null
     */
    public User getUserIfExists(String userEmail);

    /**
     * @param user
     */
    public void saveOrUpdate(User user);

    /**
     * @param password
     * @param email
     * @param updatedAt
     */
    public void setPasswordForUser(String password, String email, Date updatedAt);

    /**
     * @param user_id
     * @param item_id
     * @return boolean
     */
    public boolean checkIfUserDoesntHaveItem(int user_id, int item_id);

    /**
     * @param user_id
     * @param amount
     * @return boolean
     */
    public boolean checkIfUserHasEnoughCoins(int user_id, double amount);

    /**
     * @param user_id
     * @param amount
     */
    public void subtractCoins(int user_id, double amount);

    public double countUsersFromProject(int project_id);
}
