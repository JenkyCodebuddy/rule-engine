package jenky.codebuddy.services;

import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.rest.Equipment;
import jenky.codebuddy.models.rest.Response;

import java.util.List;
import java.util.Map;

/**
 * Created by joost on 9-6-2016.
 */
public class EquipmentService {

    public EquipmentService(){
    }

    /**
     * @param token
     * @return Equipment
     */
    public Equipment returnEquipmentFromUser(String token){
        User user = DatabaseFactory.getAuthenticationService().getAuthenticationIfTokenExists(token).getUser();
        List<Item> equippedItems = DatabaseFactory.getItemService().getAllEquipmentFromUser(user.getUser_id());
        return new Equipment(equippedItems,200);
    }

    public Response equipItemsForUser(String token, Map newItems){
        User user = DatabaseFactory.getAuthenticationService().getAuthenticationIfTokenExists(token).getUser();
        user.getItemusers().forEach((itemUser)-> {
            itemUser.setEquipped(false);
            DatabaseFactory.getItemUserService().updateItemUser(itemUser);
        });
        newItems.forEach((key,value)->{
            DatabaseFactory.getItemService().equipItemsForUser(user.getUser_id(),Integer.parseInt((String)value));
        });
        return new Response(200, "Items equipped");
    }
}
