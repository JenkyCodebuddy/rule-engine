package jenky.codebuddy.database.itemuser;

import jenky.codebuddy.database.generic.GenericService;
import jenky.codebuddy.models.entities.ItemUser;

/**
 * Specifices the specific methods for ItemService.
 */
public interface ItemUserService extends GenericService<ItemUser, Integer> {
    public void addItemUser(ItemUser itemUser);
}
