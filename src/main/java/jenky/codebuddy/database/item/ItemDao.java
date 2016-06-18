package jenky.codebuddy.database.item;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.Item;

import java.util.List;

/**
 * This interface specifies the specific methods an ItemDao must have.
 */
public interface ItemDao extends GenericDao<Item, Integer> {
    public Item findById(int item_id);
    /**
     * Return all the items
     * @return List of Items
     */
    public List<Item> getAllPuchasableItems(int user_id);

    /**
     * @param user_id
     * @return List of items that are equiped by the user
     */
    public List<Item> getEquippedItemsFromUser(int user_id);

    public List<Item> getAllEquipmentFromUser(int user_id);


    public void equipItemsForUser(int user_id, int item_id);

    public List<Item> getDefaultItems();
}
