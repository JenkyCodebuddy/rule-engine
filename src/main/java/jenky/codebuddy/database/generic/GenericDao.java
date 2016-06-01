package jenky.codebuddy.database.generic;

import java.io.Serializable;
import java.util.List;

/**
 * Generic interface. This interface defines methods that all entities must have.
 * @param <T>
 * @param <Id>
 */
public interface GenericDao<T, Id extends Serializable> {

    public void add(T entity);

    public void update(T entity);

    public void saveOrUpdate(T entity);

    public T findById(int id);

    public void delete(T entity);

    public List<T> findAll();
}