package jenky.codebuddy.database.item;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.Item;

import java.util.List;

/**
 * Specifices the specific methods for ItemService.
 */
public interface ItemService extends GenericService<Item, Integer> {
    /**
     * Return all the items
     * @return List of Items
     */
    public List<Item> getAllPurchasableItems(int user_id);

    /**
     * @param user_id
     * @return List of items that are equiped by the user
     */
    public List<Item> getEquippedItemsFromUser(int user_id);

    public List<Item> getAllEquipmentFromUser(int user_id);

    public Item findById(int item_id);

    public void equipItemsForUser(int user_id, int item_id);

    public List<Item> getDefaultItems();
}
