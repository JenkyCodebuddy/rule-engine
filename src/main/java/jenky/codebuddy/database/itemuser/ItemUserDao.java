package jenky.codebuddy.database.itemuser;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.ItemUser;

import java.util.List;

/**
 * This interface specifies the specific methods an ItemUserDao must have.
 */
public interface ItemUserDao extends GenericDao<ItemUser, Integer> {
    public void addItemUser(ItemUser itemUser);
}
