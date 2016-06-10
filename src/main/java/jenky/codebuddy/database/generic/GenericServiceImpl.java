package jenky.codebuddy.database.generic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Implements the GenericService interfaces and is responsible for sending the requests to the ScoreDao.
 * This is the service layer. This does class does not has a direct connection to the database.
 */
@Service
public abstract class GenericServiceImpl<T, Id extends Serializable> implements GenericService<T, Id> {

    private GenericDao<T, Id> genericDao;

    @Autowired
    public GenericServiceImpl(GenericDao<T, Id> genericDao){
        this.genericDao = genericDao;
    }

    public GenericServiceImpl(){

    }

    /**
     * Asks the genericDao to save given entity.
     * Transaction management done by Spring.
     * @param entity
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void add(T entity) {
        genericDao.add(entity);
    }

    /**
     * Asks the genericDao to save the given entity if it's new or update it.
     * Transaction management done by Spring.
     * @param entity
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdate(T entity) {
        genericDao.saveOrUpdate(entity);
    }

    /**
     * Asks the genericDao to update given entity.
     * Transaction management done by Spring.
     * @param entity
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(T entity) {
        genericDao.saveOrUpdate(entity);
    }

    /**
     * Asks the genericDao to find and return all the entities matching the id.
     * Transaction management done by Spring.
     * @param id
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public T findById(int id) {
        return genericDao.findById(id);
    }

    /**
     * Asks the genericDao to delete the given entity.
     * Transaction management done by Spring.
     * @param entity
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(T entity) {
        genericDao.delete(entity);
    }

    /**
     * Asks the genericDao to find and return all the matching entities.
     * Transaction management done by Spring.
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<T> findAll() {
        return genericDao.findAll();
    }

}
