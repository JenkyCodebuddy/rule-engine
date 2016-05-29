package jenky.codebuddy.database.services;

import jenky.codebuddy.database.dao.UserDao;
import jenky.codebuddy.database.dao.UserDaoImpl;
import jenky.codebuddy.models.entities.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implements the UserDaoManager interfaces and is responsible for sending the requests to the UserDao.
 * This is the service layer. This does class does not has a direct connection to the database.
 */
@Service
public class UserDaoImplService implements UserDaoManager {

    /**
     * Injected by Spring
     */
    @Autowired
    private UserDao userDao;

    /**
     * Takes as input an User object and sends it to the userDao object to be persisted.
     * This method is marked as transactional, the transactions are therefore managed by Spring.
     * @param user
     */
    @Transactional
    @Override
    public void persist(User user) {
        userDao.persist(user);
    }
}
