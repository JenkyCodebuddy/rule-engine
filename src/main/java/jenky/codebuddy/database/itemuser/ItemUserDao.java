package jenky.codebuddy.database.itemuser;

import jenky.codebuddy.database.generic.GenericDao;
import jenky.codebuddy.models.entities.ItemUser;

/**
 * This interface specifies the specific methods an ItemUserDao must have.
 */
public interface ItemUserDao extends GenericDao<ItemUser, Integer> {
    /**
     * @param itemUser
     */
    public void addItemUser(ItemUser itemUser);

    public void updateItemUser(ItemUser itemUser);

    public void deleteItemUser(ItemUser itemUser);
}
