package jenky.codebuddy.services;

import jenky.codebuddy.database.authentication.AuthenticationServiceImpl;
import jenky.codebuddy.database.item.ItemServiceImpl;
import jenky.codebuddy.database.user.UserServiceImpl;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.rest.Items;
import jenky.codebuddy.models.rest.Response;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by joost on 6-6-2016.
 */
public class ShopService {

    private ApplicationContext context;
    private ItemServiceImpl itemService;
    private UserServiceImpl userService;
    private AuthenticationServiceImpl authenticationService;

    public ShopService() {
        setContext(new ClassPathXmlApplicationContext("spring.xml"));
        setItemService((ItemServiceImpl) getContext().getBean("itemServiceImpl"));
        setAuthenticationService((AuthenticationServiceImpl) getContext().getBean("authenticationServiceImpl"));
    }

    public Items getAllItems(){
        List<Item> allItems = getItemService().getAllItems();
        return new Items(allItems);
    }

    public Response buyItemForUser(String token, int item_id){
        User user = getUserWithToken(token);
        if(checkIfItemExists(item_id)) {
            Item item = getItemIfExists(item_id);
            if (checkIfUserAlreadyHasItem(user, item_id)) {
                if (checkIfUserHasEnoughCoins(user, item)) {
                    subtractCoinsFromUser(user, item);
                    addItemToUser(user, item_id);
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

    private boolean checkIfItemExists(int item_id){
        return getItemService().checkIfItemExists(item_id);
    }

    private Item getItemIfExists(int item_id){
        return getItemService().getItemIfExists(item_id);
    }

    private boolean checkIfUserAlreadyHasItem(User user, int item_id){
        return getItemService().checkIfUserAlreadyHasItem(user.getUser_id(), item_id);
    }

    private boolean checkIfUserHasEnoughCoins(User user, Item item){
        return getUserService().checkIfUserHasEnoughCoins(user.getUser_id(), item.getPrice());
    }

    private void subtractCoinsFromUser(User user, Item item){
        getUserService().subtractCoinsFromUser(user.getUser_id(), item.getPrice());
    }

    private void addItemToUser(User user, int item_id){
        getItemService().addItemToUser(user.getUser_id(), item_id);
    }

    private User getUserWithToken(String token) {
        return getAuthenticationService().getAuthenticationIfTokenExists(token).getUser();
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    public ItemServiceImpl getItemService() {
        return itemService;
    }

    public void setItemService(ItemServiceImpl itemService) {
        this.itemService = itemService;
    }

    public AuthenticationServiceImpl getAuthenticationService() {
        return authenticationService;
    }

    public void setAuthenticationService(AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }

    public UserServiceImpl getUserService() {
        return userService;
    }

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
}
