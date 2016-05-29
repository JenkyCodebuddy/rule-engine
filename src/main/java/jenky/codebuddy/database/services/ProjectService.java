package jenky.codebuddy.database.services;

import jenky.codebuddy.database.dao.GenericDataAccessObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by joost on 29-5-2016.
 */
public class ProjectService<T> {
    private GenericDataAccessObject dao;

    public void persist(T entity) {
        dao.openCurrentSessionwithTransaction();
        dao.persist(entity);
        dao.closeCurrentSessionwithTransaction();
    }

    public void update(T entity) {
        dao.openCurrentSessionwithTransaction();
        dao.update(entity);
        dao.closeCurrentSessionwithTransaction();
    }

    public T findById(int id) {
        dao.openCurrentSession();
        T entity = (T) dao.findById(id);
        dao.closeCurrentSession();
        return entity;
    }

    public void delete(int id) {
        dao.openCurrentSessionwithTransaction();
        T entity = (T) dao.findById(id);
        dao.delete(entity);
        dao.closeCurrentSessionwithTransaction();
    }

    public List<T> findAll() {
        dao.openCurrentSession();
        List<T> users = dao.findAll();
        dao.closeCurrentSession();
        return users;
    }

    public boolean checkIfRecordExists(String column, T value){
        dao.openCurrentSession();
        boolean result = dao.checkIfRecordExists(column,value);
        dao.closeCurrentSession();
        return result;
    }

    public T getRecordIfExists(String column, T value){
        dao.openCurrentSession();
        T entity = (T) dao.getRecordIfExists(column,value);
        dao.closeCurrentSession();
        return entity;
    }

    public GenericDataAccessObject getDao() {
        return this.dao;
    }

    @Autowired
    public void setDao(GenericDataAccessObject dao) {
        this.dao = dao;
    }

    public GenericDataAccessObject genericDao() {
        return dao;
    }
}
