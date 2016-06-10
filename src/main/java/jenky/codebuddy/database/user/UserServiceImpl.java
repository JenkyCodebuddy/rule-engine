package jenky.codebuddy.database.user;

import jenky.codebuddy.database.generic.GenericServiceImpl;
import jenky.codebuddy.models.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Service layer of Profile. Inherits the GenericService and implements the VerificationService interface.
 */
@Service
public class UserServiceImpl extends GenericServiceImpl<User, Integer> implements UserService {

    private UserDao userDao;

    /**
     * Injected by Spring
     * @param userDao
     */
    @Autowired
    public UserServiceImpl(@Qualifier("userDaoImpl") UserDao userDao) {
        this.userDao = userDao;
    }

    public UserServiceImpl(){

    }

    /**
     * Asks the userDao to return all the projects.
     * Transaction management done by Spring.
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    /**
     * Asks the userDao to save the given project.
     * Transaction management done by Spring.
     * @param user
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(User user) {
        userDao.save(user);
    }

    /**
     * Asks the userDao to check if the given project exists or not.
     * Transaction management done by Spring.
     * @param userEmail
     * @return true or false
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public boolean checkIfUserExists(String userEmail) {
        return userDao.checkIfUserExists(userEmail);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User getUserIfExists(String userEmail){
        return userDao.getUserIfExists(userEmail);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void setPasswordForUser(String password, String userEmail, Date updatedAt){
        userDao.setPasswordForUser(password, userEmail, updatedAt);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public boolean checkIfUserDoesntHaveItem(int user_id, int item_id){
        return userDao.checkIfUserDoesntHaveItem(user_id, item_id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public boolean checkIfUserHasEnoughCoins(int user_id, double amount){
        return userDao.checkIfUserHasEnoughCoins(user_id, amount);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public void subtractCoins(int user_id, double amount){
        userDao.subtractCoins(user_id, amount);
    }

    /**
     * Saves or updates the user.
     * @param user
     */
    @Transactional
    @Override
    public void saveOrUpdate(User user) {
        userDao.saveOrUpdate(user);
    }


}
