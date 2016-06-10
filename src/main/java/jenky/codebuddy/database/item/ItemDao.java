package jenky.codebuddy.database.item;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.Item;

import java.util.List;

/**
 * This interface specifies the specific methods an ItemDao must have.
 */
public interface ItemDao extends GenericDao<Item> {
    public List<Item> getAllItems();

    public List<Item> getEquippedItemsFromUser(int user_id);

    public boolean checkIfItemExists(int item_id);

    public Item getItemIfExists(int item_id);
}
