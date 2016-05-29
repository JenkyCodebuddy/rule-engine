package jenky.codebuddy.database.dao;

import jenky.codebuddy.database.services.UserDaoImplService;
import jenky.codebuddy.models.entities.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.nio.file.Paths;
import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by joost on 26-5-2016.
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void persist(User user){
        sessionFactory.getCurrentSession().save(user);
    }
}
