package jenky.codebuddy.database.generic;

import java.io.Serializable;
import java.util.List;

/**
 * This interface specifies the specific methods the GenericService must have.
 */
public interface GenericService<T, Id extends Serializable> {

    public void add(T entity);

    public void update(T entity);

    public void saveOrUpdate(T entity);

    public T findById(int id);

    public void delete(T entity);

    public List<T> findAll();

}
