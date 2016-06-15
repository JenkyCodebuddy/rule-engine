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
     * @param password
     * @param userEmail
     * @param updatedAt
     */
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

    /**
     * @param user_id
     * @param amount
     */
    @Override
    public void subtractCoins(int user_id, double amount) {
        String hql = "UPDATE User u SET u.jenkycoins = (u.jenkycoins - :amount) WHERE u.id = :user_id";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("user_id", user_id);
        query.setDouble("amount",amount);
        query.executeUpdate();
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
}
