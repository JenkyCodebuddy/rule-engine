package jenky.codebuddy.database.user;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.User;

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
