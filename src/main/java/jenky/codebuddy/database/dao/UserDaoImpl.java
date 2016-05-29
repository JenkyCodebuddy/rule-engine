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
 * Implements the UserDao interface. This is part of the Data Acces Object design pattern.
 * This class is managed by Spring en gets a SessionFactory injected.
 * This class is responsible for executing the requested actions on the database.
 */
@Repository
public class UserDaoImpl implements UserDao {

    /**
     * Injected by Spring
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Takes as input an user and saved it with help from the sessionFactory.
     * @param user
     */
    public void persist(User user){
        sessionFactory.getCurrentSession().save(user);
    }
}
