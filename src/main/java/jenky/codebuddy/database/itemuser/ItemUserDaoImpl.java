package jenky.codebuddy.database.itemuser;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.ItemUser;
import org.springframework.stereotype.Repository;

/**
 * Persistence of ItemUserDao. Inherits GenericDao and implements the ItemUserDao interface.
 */
@Repository
public class ItemUserDaoImpl extends GenericDaoImpl<ItemUser, Integer> implements ItemUserDao {

    /**
     * Return all the items
     * @return List of Items
     */
    @Override
    public void addItemUser(ItemUser itemUser) {
        super.add(itemUser);
    }
}
