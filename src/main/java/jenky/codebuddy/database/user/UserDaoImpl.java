package jenky.codebuddy.database.user;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Perisstence of VerificationDao. Inherits GenericDaoImpl and implements VerificationDao interface
 */
@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements UserDao {

    /**
     * @return List of all the users
     */
    @Override
    public List<User> getAllUsers() {
        return super.findAll();
    }

    /**
     * Check if the given user exists or not.
     * @param email
     * @return true or false
     */
    @Override
    public boolean checkIfUserExists(String email) {
        String hql = "FROM User u WHERE u.email = :user_email";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("user_email",email);
        Optional<User> result = Optional.ofNullable((User) query.uniqueResult());
        return result.isPresent();
    }

    /**
     * @param email
     * @return
     */
    @Override
    public User getUserIfExists(String email) {
        String hql = "FROM User u WHERE u.email = :user_email";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("user_email",email);
        Optional<User> result = Optional.ofNullable((User) query.uniqueResult());
        return result.get();
    }

    /**
     * @param user_id
     * @param item_id
     * @return User otherwise null
     */
    @Override
    public boolean checkIfUserDoesntHaveItem(int user_id, int item_id) {
        String hql = "FROM User u INNER JOIN u.itemusers as item_has_user WHERE item_has_user.user = :user_id AND item_has_user.item = :item_id";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("user_id",user_id);
        query.setInteger("item_id",item_id);
        Optional<List<User>> result = Optional.ofNullable(query.list());
        return result.get().isEmpty();
    }

    /**
     * @param user_id
     * @param amount
     * @return
     */
    @Override
    public boolean checkIfUserHasEnoughCoins(int user_id, double amount) {
        String hql = "SELECT u.jenkycoins FROM User u WHERE u.id = :user_id";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("user_id",user_id);
        Optional result = Optional.ofNullable(query.uniqueResult());
        int userCurrency = (int)result.get();
        return ((userCurrency - amount) >= 0) ? true : false;
    }

    @Override
    public int getJenkyCoinsFromUser(int user_id) {
        String hql = "SELECT u.jenkycoins FROM User u WHERE u.id = :user_id";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("user_id",user_id);
        Optional result = Optional.ofNullable(query.uniqueResult());
        return (int)result.get();
    }

    @Override
    public double countUsersFromProject(int project_id) {
        String hql = "SELECT COUNT(DISTINCT user.id) FROM User user INNER JOIN user.scores as score INNER JOIN score.commit as commit INNER JOIN commit.project as project WHERE project.id =:project_id GROUP BY project.id";/*INNER JOIN Commit commit ON commit.id = score.commit INNER JOIN Project project ON project.id = commit.project WHERE project.id =:project_id*/
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("project_id",project_id);
        return (long) query.uniqueResult();
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

    @Override
    public User getUserWithHighestMetricScoreForProject(String metricName, String projectName) {
        String hql = "FROM User user " +
                "       LEFT JOIN FETCH user.scores as score " +
                "       LEFT JOIN FETCH score.commit as commit " +
                "       LEFT JOIN FETCH score.metric as metric " +
                "       LEFT JOIN FETCH commit.project as project " +
                "           WHERE project.name =:projectName " +
                "               AND metric.name =:metricName " +
                "               AND score.score = " +
                "                   (SELECT max(score.score) FROM Score score " +
                "                       INNER JOIN score.metric as metric " +
                "                       INNER JOIN score.commit as commit " +
                "                       INNER JOIN commit.project as project " +
                "                           WHERE project.name =:projectName " +
                "                               AND metric.name =:metricName) " +
                "                                   GROUP BY score.id";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("metricName",metricName);
        query.setParameter("projectName",projectName);
        Optional<List<User>> result = Optional.ofNullable(query.list());
        return result.isPresent() && result.get().size() == 1 ? result.get().get(0) : null;
    }
}
