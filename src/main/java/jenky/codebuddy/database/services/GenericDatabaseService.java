package jenky.codebuddy.database.services;

import jenky.codebuddy.database.dao.GenericDataAccessObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * This class is responsible for passing sending the request to the generic dao.
 * This is the service layer. This does class does not has a direct connection to the database.
 * Transactional means that the transaction is managed by Spring
 * @param <T>
 */
@Service
public class GenericDatabaseService<T> {

    /**
     * Injected by Spring
     */
    @Autowired
    private GenericDataAccessObject dao;

    /*
     * @param entity
     */
    @Transactional
    public void persist(T entity) {
        dao.persist(entity);
    }

    /**
     *
     * @param entity
     */
    @Transactional
    public void update(T entity) {
        dao.update(entity);
    }

    /**
     *
     * @param id
     * @return the requested entity
     */
    @Transactional
    public T findById(int id) {
        T entity = (T) dao.findById(id);
        return entity;
    }

    /**
     *
     * @param id
     */
    @Transactional
    public void delete(int id) {
        T entity = (T) dao.findById(id);
        dao.delete(entity);
    }

    /**
     *
     * @return all of the requested entities
     */
    @Transactional
    public List<T> findAll() {
        List<T> users = dao.findAll();
        return users;
    }

    /**
     *
     * @param column
     * @param value
     * @return return a true or false
     */
    @Transactional
    public boolean checkIfRecordExists(String column, T value){
        boolean result = dao.checkIfRecordExists(column,value);
        return result;
    }

    /**
     *
     * @param column
     * @param value
     * @return returns the record if it exists
     */
    @Transactional
    public T getRecordIfExists(String column, T value){
        T entity = (T) dao.getRecordIfExists(column,value);
        return entity;
    }
}