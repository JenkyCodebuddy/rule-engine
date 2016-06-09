package jenky.codebuddy.database.item;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.Item;

import java.util.List;

/**
 * Specifices the specific methods for ItemService.
 */
public interface ItemService extends GenericService<Item, Integer> {
    public List<Item> getAllItems();

    public List<Item> getEquippedItemsFromUser(int user_id);
}
