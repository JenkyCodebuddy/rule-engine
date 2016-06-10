package jenky.codebuddy.database.generic;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Persistence of GenericDao. This extends Serialiazble and implements the GenericDao interface.
 * @param <T>
 * @param <Id>
 */
@Repository
public abstract class GenericDaoImpl<T> implements GenericDao<T> {
    protected Class<? extends T> type;

    private SessionFactory sessionFactory;

    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    /**
     * Injected by Spring.
     * @param sessionFactory
     */
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     *
     * @return sessionFactory
     */
    public SessionFactory getSessionFactory(){
        return this.sessionFactory;
    }


    /**
     * @param type
     */
    public void setType(Class<T> type) {
        this.type = type;
    }

    /**
     * Saves the entity
     * @param entity
     */
    @Override
    public void add(T entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    /**
     * Updates the entity
     * @param entity
     */
    @Override
    public void update(T entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    /**
     * Either saves or updates the entity depending on if it's new.
     * @param entity
     */
    @Override
    public void saveOrUpdate(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    /**
     * Deletes the entity
     * @param entity
     */
    @Override
    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    /**
     * Returns all the entities matching the criteria
     * @return entity
     */
    @Override
    public List<T> findAll() {
        return sessionFactory.getCurrentSession().createCriteria(type).list();
    }

    /**
     * Return the entity if found by the given id
     * @param id
     * @return entity or null
     */
    @Override
    public T findById(final int id) {
        return (T)  sessionFactory.getCurrentSession().get(type, id);
    }


}
