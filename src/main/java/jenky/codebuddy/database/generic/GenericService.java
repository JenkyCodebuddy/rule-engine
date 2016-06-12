package jenky.codebuddy.database.generic;

import java.io.Serializable;
import java.util.List;

/**
 * This interface specifies the specific methods the GenericService must have.
 */
public interface GenericService<T, Id extends Serializable> {

    /**
     * Saves the entity
     * @param entity
     */
    public void add(T entity);

    /**
     * Updates the entity
     * @param entity
     */
    public void update(T entity);

    /**
     * Either saves or updates the entity depending on if it's new.
     * @param entity
     */
    public void saveOrUpdate(T entity);

    /**
     * Return the entity if found by the given id
     * @param id
     * @return entity or null
     */
    public T findById(int id);

    /**
     * Deletes the entity
     * @param entity
     */
    public void delete(T entity);

    /**
     * Returns all the entities matching the criteria
     * @return entity
     */
    public List<T> findAll();

}
