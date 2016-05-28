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
import org.springframework.stereotype.Controller;
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

    private Session currentSession;
    private Class<T> type;
    private Transaction currentTransaction;
    private SessionFactory sessionFactory;

    public GenericDataAccessObject() {
    }

    public GenericDataAccessObject(Class<T> type) {
        this.type = type;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory(){
        return sessionFactory;
    }



    /*public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }*/

    /*public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }*/

    /*public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }*/

    /*public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }
*/
    @Transactional
    public void persist(T entity) {
        getSessionFactory().getCurrentSession().save(entity);
    }
    @Transactional
    public void update(T entity) {
        getSessionFactory().getCurrentSession().update(entity);
    }
    @Transactional
    public T findById(final int id) {
        return (T) getSessionFactory().getCurrentSession().get(type, id);
    }
    @Transactional
    public void delete(T entity) {
        getSessionFactory().getCurrentSession().delete(entity);
    }
    @Transactional
    public List<T> findAll() {
        Criteria crit = getSessionFactory().getCurrentSession().createCriteria(type);
        return crit.list();
    }
    @Transactional
    public T getRecordIfExists(String column, T value){

        Criteria crit = getSessionFactory().getCurrentSession().createCriteria(type)
                .add(Restrictions.eq(column,value));
        Optional<Criteria> cr = Optional.ofNullable(crit);
        return (T) cr.get().uniqueResult();

    }
    @Transactional
    public boolean checkIfRecordExists(String column, T value){
        Criteria crit = getSessionFactory().getCurrentSession().createCriteria(type)
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
