package jenky.codebuddy.services;

import jenky.codebuddy.database.authentication.AuthenticationServiceImpl;
import jenky.codebuddy.database.item.ItemServiceImpl;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.rest.Equipment;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by joost on 9-6-2016.
 */
public class EquipmentService {

    private ApplicationContext context;
    private AuthenticationServiceImpl authenticationService;
    private ItemServiceImpl itemService;

    public EquipmentService() {
        setContext(new ClassPathXmlApplicationContext("spring.xml"));
        setItemService((ItemServiceImpl) getContext().getBean("itemServiceImpl"));
        setAuthenticationService((AuthenticationServiceImpl) getContext().getBean("authenticationServiceImpl"));
    }

    public Equipment returnEquipmentFromUser(String token){
        User user = getAuthenticationService().getAuthenticationIfTokenExists(token).getUser();
        List<Item> equippedItems = itemService.getEquippedItemsFromUser(user.getUser_id());
        return new Equipment(equippedItems);
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
}
