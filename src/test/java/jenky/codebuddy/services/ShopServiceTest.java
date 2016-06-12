package jenky.codebuddy.services;

import jenky.codebuddy.models.entities.Authentication;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.User;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by joost on 12-6-2016.
 */
public class ShopServiceTest {

    private static ShopService shopService;
    private static User testUser;
    private static Authentication testAuthentication;
    private static Item testItem;

    @BeforeClass
    public static void setup(){
        shopService = new ShopService();
        testAuthentication = new Authentication();
        testAuthentication.setToken("TEST_TOKEN");
        testUser = new User();
        testUser.setEmail("testEmail");
        testUser.setPassword("testPassword");
        testUser.setAuthentication(testAuthentication);
        testItem = new Item();
        testItem.setId(123123123);
    }

    @Test
    public void testBuyItemForUser(){
        shopService.buyItemForUser(testUser.getAuthentication().getToken(),testItem.getId());
    }
}
