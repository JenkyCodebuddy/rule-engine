package jenky.codebuddy.services;

import jenky.codebuddy.models.rest.Items;
import jenky.codebuddy.models.rest.Response;

/**
 * Created by Fabian on 12-6-2016.
 */
public interface UserShopService {
    /**
     *
     * @return List of Items
     */
    Items getAllItems();

    /**
     *
     * @param token
     * @param itemId
     * @return Reponse
     */
    Response buyItemForUser(String token, int itemId);
}
