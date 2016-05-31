package jenky.codebuddy.database.generic;

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

    public GenericServiceImpl(GenericDao<T, Id> genericDao){
        this.genericDao = genericDao;
    }

    public GenericServiceImpl(){

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void add(T entity) {
        genericDao.add(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(T entity) {
        genericDao.saveOrUpdate(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrUpdate(T entity) {
        genericDao.saveOrUpdate(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public T findById(int id) {
        return genericDao.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(T entity) {
        genericDao.delete(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<T> findAll() {
        return genericDao.findAll();
    }
    
}
