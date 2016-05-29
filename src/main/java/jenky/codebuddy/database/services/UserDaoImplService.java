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
 * Created by joost on 27-5-2016.
 */
@Service
public class UserDaoImplService implements UserDaoManager {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public void persist(User user) {
        userDao.persist(user);
    }
}
