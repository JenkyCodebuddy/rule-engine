package jenky.codebuddy.services;

import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.ItemUser;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.rest.Items;
import jenky.codebuddy.models.rest.Response;

import javax.xml.crypto.Data;
import java.util.List;

/**
 * Created by joost on 6-6-2016.
 */
public class ShopService {

    public ShopService() {
    }

    /**
     * @return List of all the items which are not purchased yet
     */
    public Items getAllPurchasableItems(String token){
        User user = DatabaseFactory.getAuthenticationService().getAuthenticationIfTokenExists(token).getUser();
        List<Item> allItems = DatabaseFactory.getItemService().getAllPurchasableItems(user.getUser_id());
        int jenkycoins = DatabaseFactory.getUserService().getJenkyCoinsFromUser(user.getUser_id());
        return new Items(allItems, jenkycoins, 200);
    }

    /**
     * @param token
     * @param itemId
     * @return Response
     */
    public Response buyItemForUser(String token, int itemId){
        User user = DatabaseFactory.getAuthenticationService().getAuthenticationIfTokenExists(token).getUser();
        if(DatabaseFactory.getItemService().checkIfItemExists(itemId)) {
            Item item = DatabaseFactory.getItemService().getItemIfExists(itemId);
            if (DatabaseFactory.getUserService().checkIfUserDoesntHaveItem(user.getUser_id(), itemId)) {
                if (DatabaseFactory.getUserService().checkIfUserHasEnoughCoins(user.getUser_id(), item.getPrice())) {
                    DatabaseFactory.getUserService().subtractCoins(user.getUser_id(), item.getPrice());
                    addItemToUser(user, item);
                    return new Response(200, "Item purchased for user");
                } else {
                    return new Response(400, "Not enough coins");
                }
            } else {
                return new Response(400, "User already has selected item");
            }
        }else {
            return new Response(400, "Item doesn't exist");
        }
    }

    /**
     * @param user
     * @param item
     */
    private void addItemToUser(User user, Item item){
        ItemUser itemUser = new ItemUser();
        itemUser.setUser(user);
        itemUser.setItem(item);
        itemUser.setEquipped(false);
        DatabaseFactory.getItemUserService().addItemUser(itemUser);
    }
}
