package jenky.codebuddy.database.dao;

import java.io.Serializable;
import java.util.List;

public interface DataAccessObjectInterface<T, Id extends Serializable> {

    public void persist(T entity);

    public void update(T entity);

    public T findById(int id);

    public void delete(T entity);

    public List<T> findAll();

    public boolean checkIfRecordExists(String column, T value);

    public T getRecordIfExists(String column, T value);
}