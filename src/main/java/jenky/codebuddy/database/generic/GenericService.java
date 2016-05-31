package jenky.codebuddy.database.generic;

import jenky.codebuddy.models.entities.User;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Fabian on 29-5-2016.
 */
public interface GenericService<T, Id extends Serializable> {

    public void add(T entity);

    public void update(T entity);

    public void saveOrUpdate(T entity);

    public T findById(int id);

    public void delete(T entity);

    public List<T> findAll();

}
