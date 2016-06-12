package jenky.codebuddy.services;

import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.ItemUser;
import jenky.codebuddy.models.rest.Equipment;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by joost on 12-6-2016.
 */
public class EquipmentServiceTest {

    private static EquipmentService equipmentService;
    private static Item item1;
    private static Item item2;
    private static Item item3;
    private static Equipment equipment;
    private static String token;
    @BeforeClass
    public static void setup(){
        equipmentService = Mockito.mock(EquipmentService.class);
        item1 = new Item(null, "Iron chestplate", "Body armour", "chestplate.jpeg", 400, new Date(), new Date(), new Date());
        item2 = new Item(null, "Bronze helmet", "Headgear", "helmet.jpeg", 100, new Date(), new Date(), new Date());
        item3 = new Item(null, "Rune boots", "Boots", "runeboots.jpeg", 1337, new Date(), new Date(), new Date());
        equipment = new Equipment(Arrays.asList(item1,item2,item3),200);
        token = "TEST_TOKEN";

        Mockito.when(equipmentService.returnEquipmentFromUser(token)).thenReturn(equipment);
    }

    @Test
    public void testReturnEquipment(){
        Equipment equipment = equipmentService.returnEquipmentFromUser("TEST_TOKEN");
        Assert.assertNotNull(equipment);
        List<Item> items = equipment.getEquipment();
        Assert.assertEquals(3,items.size());
        Item item3 = items.get(2);
        Assert.assertEquals(item3.getName(), "Rune boots");
        Assert.assertEquals(item3.getType(), "Boots");
        Assert.assertEquals(item3.getImage(), "runeboots.jpeg");
        Assert.assertEquals(item3.getPrice(), 1337,0);
    }

}
