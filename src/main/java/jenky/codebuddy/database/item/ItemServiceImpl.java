package jenky.codebuddy.database.item;

import jenky.codebuddy.database.generic.GenericServiceImpl;
import jenky.codebuddy.models.entities.Item;
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
public class ItemServiceImpl extends GenericServiceImpl<Item, Integer> implements ItemService {

    private ItemDao itemDao;

    /**
     * Injected by Spring.
     * @param itemDao
     */
    @Autowired
    public ItemServiceImpl(@Qualifier("itemDaoImpl") ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public ItemServiceImpl(){

    }

    /**
     * Asks itemDao to find and return all the items
     * Transaction management done by Spring.
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Item> getAllPurchasableItems(int user_id) {
        return itemDao.getAllPuchasableItems(user_id);
    }

    /**
     * @param user_id
     * @return list of items that are equipped by the user
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Item> getEquippedItemsFromUser(int user_id) {
        return itemDao.getEquippedItemsFromUser(user_id);
    }

    /**
     * @param item_id
     * @return boolean
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public boolean checkIfItemExists(int item_id) {
        return itemDao.checkIfItemExists(item_id);
    }

    /**
     * @param item_id
     * @return gets item if exits
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Item getItemIfExists(int item_id){
        return itemDao.getItemIfExists(item_id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Item> getAllEquipmentFromUser(int user_id) {
        return itemDao.getAllEquipmentFromUser(user_id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void equipItemsForUser(int user_id, int item_id) {
        itemDao.equipItemsForUser(user_id,item_id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Item> getDefaultItems() {
        return itemDao.getDefaultItems();
    }
}
