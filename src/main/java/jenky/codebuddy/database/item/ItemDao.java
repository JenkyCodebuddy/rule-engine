package jenky.codebuddy.database.item;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.User;

import java.util.List;

/**
 * Created by joost on 30-5-2016.
 */
public interface ItemDao extends GenericDao<Item, Integer> {
    public List<Item> getAllItems();
}
