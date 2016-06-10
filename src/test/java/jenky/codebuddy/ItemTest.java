package jenky.codebuddy;

import jenky.codebuddy.database.commit.CommitServiceImpl;
import jenky.codebuddy.database.item.ItemServiceImpl;
import jenky.codebuddy.services.DatabaseFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;


/**
 * Created by Fabian on 10-6-2016.
 */
public class ItemTest {

    @Test
    @Transactional
    public void getEquippedItemsFromUser() throws Exception{
        assertNotNull(DatabaseFactory.getItemService().getEquippedItemsFromUser(TestInfo.TESTID));
    }

}
