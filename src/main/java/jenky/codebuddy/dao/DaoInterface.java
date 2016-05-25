package jenky.codebuddy.dao;

import jenky.codebuddy.models.databaseModels.User;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface DaoInterface<T, Id extends Serializable> {

    public void persist(T entity);

    public void update(T entity);

    public T findById(int id);

    public void delete(T entity);

    public List<T> findAll();

    public boolean checkIfRecordExists(String column, T value);

    public T getRecordIfExists(String column, T value);
}