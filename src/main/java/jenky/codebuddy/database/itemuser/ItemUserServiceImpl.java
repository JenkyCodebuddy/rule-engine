package jenky.codebuddy.database.itemuser;

import jenky.codebuddy.database.generic.GenericServiceImpl;
import jenky.codebuddy.models.entities.ItemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service layer of Item. Inherits the GenericService and implements the ItemService
 */
@Service
public class ItemUserServiceImpl extends GenericServiceImpl<ItemUser, Integer> implements ItemUserService {

    private ItemUserDao itemUserDao;

    /**
     * Injected by Spring.
     * @param itemUserDao
     */
    @Autowired
    public ItemUserServiceImpl(@Qualifier("itemUserDaoImpl") ItemUserDao itemUserDao) {
        this.itemUserDao = itemUserDao;
    }

    public ItemUserServiceImpl(){

    }

    /**
     * Transaction is managed by Spring
     * @param itemUser
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addItemUser(ItemUser itemUser) {
        itemUserDao.addItemUser(itemUser);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateItemUser(ItemUser itemUser) {
        itemUserDao.updateItemUser(itemUser);
    }
}
