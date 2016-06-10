package jenky.codebuddy.services;

import jenky.codebuddy.database.authentication.AuthenticationServiceImpl;
import jenky.codebuddy.database.item.ItemServiceImpl;
import jenky.codebuddy.database.itemuser.ItemUserServiceImpl;
import jenky.codebuddy.database.user.UserServiceImpl;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.ItemUser;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.rest.Items;
import jenky.codebuddy.models.rest.Response;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.jpa.vendor.Database;

import java.util.List;

/**
 * Created by joost on 6-6-2016.
 */
public class ShopService {

    public ShopService() {
    }

    public Items getAllItems(){
        List<Item> allItems = DatabaseFactory.getItemService().getAllItems();
        return new Items(allItems, 200);
    }

    public Response buyItemForUser(String token, int item_id){
        User user = DatabaseFactory.getAuthenticationService().getAuthenticationIfTokenExists(token).getUser();
        if(DatabaseFactory.getItemService().checkIfItemExists(item_id)) {
            Item item = DatabaseFactory.getItemService().getItemIfExists(item_id);
            if (DatabaseFactory.getUserService().checkIfUserDoesntHaveItem(user.getUser_id(), item_id)) {
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

    private void addItemToUser(User user, Item item){
        ItemUser itemUser = new ItemUser();
        itemUser.setUser(user);
        itemUser.setItem(item);
        itemUser.setEquipped(false);
        DatabaseFactory.getItemUserService().addItemUser(itemUser);
    }
}
