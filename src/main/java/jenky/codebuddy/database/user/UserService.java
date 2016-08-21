package jenky.codebuddy.database.user;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.User;

import java.util.List;

/**
 * This interface specifies the methods UserService must have.
 */
public interface UserService extends GenericService<User, Integer> {
    /**
     * @return List of all users
     */
    public List<User> getAllUsers();

    /**
     * @param user
     */
    public void save(User user);

    /**
     * @param user
     * @return boolean
     */
    public boolean checkIfUserExists(String user);

    /**
     * @param email
     * @return User otherwise null
     */
    public User getUserIfExists(String email);

    /**
     * @param user
     */
    public void saveOrUpdate(User user);

    public void deleteUser(User user);

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

    public double countUsersFromProject(int project_id);

    public int getJenkyCoinsFromUser(int user_id);

    public User getUserWithHighestMetricScoreForProject(String metric, String projectName);
}
