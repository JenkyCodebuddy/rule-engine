package jenky.codebuddy.database.item;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.User;

import java.util.List;

/**
 * Specifices the specific methods for ItemService.
 */
public interface ItemService extends GenericService<Item, Integer> {
    public List<Item> getAllItems();
}
