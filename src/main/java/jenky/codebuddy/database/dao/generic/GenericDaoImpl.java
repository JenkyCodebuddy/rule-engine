package jenky.codebuddy.database.dao.generic;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by joost on 24-5-2016.
 */
@Repository
public abstract class GenericDaoImpl<T, Id extends Serializable> implements GenericDao<T, Id> {
    protected Class<? extends T> type;

    @Autowired
    private SessionFactory sessionFactory;

    public GenericDaoImpl() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }

    @Override
    public void add(T entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void update(T entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public void saveOrUpdate(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public List<T> findAll() {
        return sessionFactory.getCurrentSession().createCriteria(type).list();
    }

    @Override
    public T findById(final int id) {
        return (T)  sessionFactory.getCurrentSession().get(type, id);
    }



    /*public boolean checkIfRecordExists(String column, T value);

    public T getRecordIfExists(String column, T value);

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
    }*/
}
