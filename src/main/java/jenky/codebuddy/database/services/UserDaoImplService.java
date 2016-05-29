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
@Component
public class UserDaoImplService {

    private UserDaoImpl userDao;
    private SessionFactory sessionFactory;


    public UserDaoImpl getUserDao() {
        return userDao;
    }

    @Autowired
    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public void persist(UserDaoImpl userDao){
        sessionFactory.getCurrentSession().persist(userDao);
    }


}
