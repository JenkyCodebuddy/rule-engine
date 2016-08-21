package jenky.codebuddy.database.itemuser;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.ItemUser;

/**
 * Specifices the specific methods for ItemService.
 */
public interface ItemUserService extends GenericService<ItemUser, Integer> {
    /**
     * @param itemUser
     */
    public void addItemUser(ItemUser itemUser);

    public void updateItemUser(ItemUser itemUser);

    public void deleteItemUser(ItemUser itemUser);
}
