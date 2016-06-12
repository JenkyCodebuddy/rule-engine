package jenky.codebuddy.database.generic;

import java.io.Serializable;
import java.util.List;

/**
 * Generic interface. This interface defines methods that all entities must have.
 * @param <T>
 * @param <Id>
 */
public interface GenericDao<T, Id extends Serializable> {

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