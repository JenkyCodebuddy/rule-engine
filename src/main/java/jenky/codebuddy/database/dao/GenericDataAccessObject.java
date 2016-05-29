package jenky.codebuddy.database.dao;

import jenky.codebuddy.models.entities.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

/**
 * Created by joost on 24-5-2016.
 */
@Repository
public class GenericDataAccessObject<T, Id extends Serializable> implements DataAccessObjectInterface<T, Id> {
    private Class<T> type;

    @Autowired
    private SessionFactory sessionFactory;

    public GenericDataAccessObject(Class<T> type) {
    }

    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

    @Transactional
    public void persist(T entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    @Transactional
    public void update(T entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Transactional
    public T findById(final int id) {
        return (T)  sessionFactory.getCurrentSession().get(type, id);
    }

    @Transactional
    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Transactional
    public List<T> findAll() {
        Criteria crit =  sessionFactory.getCurrentSession().createCriteria(type);
        return crit.list();
    }

    @Transactional
    public T getRecordIfExists(String column, T value){
        Criteria crit =  sessionFactory.getCurrentSession().createCriteria(type)
                .add(Restrictions.eq(column,value));
        Optional<Criteria> cr = Optional.ofNullable(crit);
        return (T) cr.get().uniqueResult();
    }

    @Transactional
    public boolean checkIfRecordExists(String column, T value){
        Criteria crit =  sessionFactory.getCurrentSession().createCriteria(type)
                .add(Restrictions.eq(column,value));
        crit.list();
        if(crit.list().isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }
}
