package jenky.codebuddy.services;

import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.rest.Equipment;

import java.util.List;

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
        List<Item> equippedItems = DatabaseFactory.getItemService().getEquippedItemsFromUser(user.getUser_id());
        return new Equipment(equippedItems,200);
    }
}
