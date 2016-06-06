package jenky.codebuddy.database.item;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Persistence of ItemDao. Inherits GenericDao and implements the ItemDao interface.
 */
@Repository
public class ItemDaoImpl extends GenericDaoImpl<Item, Integer> implements ItemDao {

    /**
     * Return all the items
     * @return List of Items
     */
    @Override
    @Transactional
    public List<Item> getAllItems() {
        return super.findAll();
    }
}
