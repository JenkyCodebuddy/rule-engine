package jenky.codebuddy.dao;

import jenky.codebuddy.models.databaseModels.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

/**
 * Created by joost on 24-5-2016.
 */
public class Dao<T, Id extends Serializable> implements DaoInterface<T, Id> {
    private Session currentSession;
    private Class<T> type;
    private Transaction currentTransaction;

    public Dao(Class<T> type) {
        this.type = type;
    }

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure(Paths.get("src","main","resources","hibernate.cfg.xml").toFile());
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Commit.class);
        configuration.addAnnotatedClass(Company.class);
        configuration.addAnnotatedClass(Item.class);
        configuration.addAnnotatedClass(Metric.class);
        configuration.addAnnotatedClass(Project.class);
        configuration.addAnnotatedClass(Score.class);
        configuration.addAnnotatedClass(Achievement.class);


        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }

    public Session getCurrentSession() {
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

    public void persist(T entity) {
        getCurrentSession().save(entity);
    }

    public void update(T entity) {
        getCurrentSession().update(entity);
    }

    public T findById(final int id) {
        return (T) getCurrentSession().get(type, id);
    }

    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    public List<T> findAll() {
        Criteria crit = getCurrentSession().createCriteria(type);
        return crit.list();
    }

    public T getRecordIfExists(String column, T value){

        Criteria crit = getCurrentSession().createCriteria(type)
                .add(Restrictions.eq(column,value));
        Optional<Criteria> cr = Optional.ofNullable(crit);
        return (T) cr.get().uniqueResult();

    }

    public boolean checkIfRecordExists(String column, T value){
        Criteria crit = getCurrentSession().createCriteria(type)
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
