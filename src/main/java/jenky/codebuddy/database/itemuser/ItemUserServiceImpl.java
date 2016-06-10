package jenky.codebuddy.database.itemuser;

import jenky.codebuddy.database.generic.GenericServiceImpl;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.ItemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer of Item. Inherits the GenericService and implements the ItemService
 */
@Service
public class ItemUserServiceImpl extends GenericServiceImpl<ItemUser> implements ItemUserService {

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
     * Asks itemDao to find and return all the items
     * Transaction management done by Spring.
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addItemUser(ItemUser itemUser) {
        itemUserDao.addItemUser(itemUser);
    }
}
