package jenky.codebuddy.database.services;

import jenky.codebuddy.database.dao.UserDaoImpl;
import jenky.codebuddy.models.entities.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by joost on 27-5-2016.
 */
public class UserDaoImplService {

    private UserDaoImpl userDao;
    private SessionFactory sessionFactory;

    @Transactional
    public void persist(User userDao){
        sessionFactory.getCurrentSession().persist(userDao);
    }

    @Autowired
    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public UserDaoImpl getUserDao() {
        return userDao;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
