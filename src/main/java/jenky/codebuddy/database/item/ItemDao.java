package jenky.codebuddy.database.item;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.User;

import java.util.List;

/**
 * This interface specifies the specific methods an ItemDao must have.
 */
public interface ItemDao extends GenericDao<Item, Integer> {
    public List<Item> getAllItems();
}
