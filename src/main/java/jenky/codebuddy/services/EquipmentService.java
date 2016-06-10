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

    public EquipmentService(){
    }

    public Equipment returnEquipmentFromUser(String token){
        User user = DatabaseFactory.getAuthenticationService().getAuthenticationIfTokenExists(token).getUser();
        List<Item> equippedItems = DatabaseFactory.getItemService().getEquippedItemsFromUser(user.getUser_id());
        return new Equipment(equippedItems,200);
    }
}
