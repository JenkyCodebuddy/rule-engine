package jenky.codebuddy.services;

import jenky.codebuddy.TestInfo;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.ItemUser;
import jenky.codebuddy.models.entities.User;
import org.hibernate.boot.model.relational.Database;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by Joost on 20-8-2016.
 */
public class EquipmentServiceTest {
    private User user;
    private Item item;
    private EquipmentService equipmentService;

    @Before
    public void setUp(){
        user = DatabaseFactory.getUserService().getUserIfExists(TestInfo.TESTEMAIL);
        item = user.getItemusers().iterator().next().getItem();
        equipmentService = new EquipmentService();
    }

    @Test
    public void returnEquipmentFromUser() throws Exception {
        assertNotNull(equipmentService.returnEquipmentFromUser(user.getAuthentication().getToken()));
    }

    @Test
    public void equipItemsForUser() throws Exception {
        user.getItemusers().forEach((itemUser)-> {
            itemUser.setEquipped(true);
            DatabaseFactory.getItemUserService().updateItemUser(itemUser);
        });
        assertEquals(true, user.getItemusers().iterator().next().isEquipped());
    }
}