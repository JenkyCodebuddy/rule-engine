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
 * This class is managed by Spring en gets a SessionFactory injected.
 * This class is responsible for executing the requested actions on the database.
 * @param <T>
 * @param <Id>
 */
@Repository
public class GenericDataAccessObject<T, Id extends Serializable> implements DataAccessObjectInterface<T, Id> {
    private Class<T> type;

    /**
     * injected by Spring
     */
    @Autowired
    private SessionFactory sessionFactory;

    public GenericDataAccessObject() {
    }

    public GenericDataAccessObject(Class<T> type) {
    }

    /**
     *
     * @return type of class
     */
    public Class<T> getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(Class<T> type) {
        this.type = type;
    }

    /**
     * saves the entity in the database
     * @param entity
     */
    public void persist(T entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    /**
     * updates the entity
     * @param entity
     */
    public void update(T entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    /**
     *
     * @param id
     * @return entity with matching id
     */
    public T findById(final int id) {
        return (T)  sessionFactory.getCurrentSession().get(type, id);
    }

    /**
     * deletes the entity from the database
     * @param entity
     */
    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    /**
     *
     * @return all the matching entities of the same type
     */
    public List<T> findAll() {
        Criteria crit =  sessionFactory.getCurrentSession().createCriteria(type);
        return crit.list();
    }

    /**
     *
     * @param column
     * @param value
     * @return the entity if it exists
     */
    public T getRecordIfExists(String column, T value){
        Criteria crit =  sessionFactory.getCurrentSession().createCriteria(type)
                .add(Restrictions.eq(column,value));
        Optional<Criteria> cr = Optional.ofNullable(crit);
        return (T) cr.get().uniqueResult();
    }

    /**
     *
     * @param column
     * @param value
     * @return
     */
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
