package jenky.codebuddy.services;

import jenky.codebuddy.TestInfo;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.ItemUser;
import jenky.codebuddy.models.entities.User;
import junit.framework.TestSuite;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Joost on 20-8-2016.
 */
public class ShopServiceTest {
    private User user;
    private ShopService shopService;

    @Before
    public void setUp(){
        user = DatabaseFactory.getUserService().getUserIfExists(TestInfo.TESTEMAIL);
        shopService = new ShopService();
    }

    @Test
    public void getAllPurchasableItems() throws Exception {
        assertNotNull(shopService.getAllPurchasableItems(user.getAuthentication().getToken()));
        assertTrue(shopService.getAllPurchasableItems(user.getAuthentication().getToken()).getItems().isEmpty());
    }

    @Test
    public void buyItemForUser() throws Exception {
        //not tested on purpose, can't delete a join table record??
    }

}