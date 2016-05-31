package jenky.codebuddy.database.item;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.User;

import java.util.List;

/**
 * Created by joost on 30-5-2016.
 */
public interface ItemService extends GenericService<Item, Integer> {
    public List<Item> getAllItems();
}
