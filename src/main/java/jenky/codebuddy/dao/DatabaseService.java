package jenky.codebuddy.dao;

import java.util.List;


public class DatabaseService<T> {

    private static Dao dao;

    public DatabaseService(Class<T> type) {
        dao = new Dao(type);
    }

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

    public boolean checkIfRecordExists(String column, String value){
        dao.openCurrentSession();
        boolean result = dao.checkIfRecordExists(column,value);
        dao.closeCurrentSession();
        return result;
    }

    public T getRecordIfExists(String column, String value){
        dao.openCurrentSession();
        T entity = (T) dao.getRecordIfExists(column,value);
        dao.closeCurrentSession();
        return entity;
    }

    public Dao genericDao() {
        return dao;
    }
}