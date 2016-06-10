package jenky.codebuddy;

import jenky.codebuddy.database.commit.CommitServiceImpl;
import jenky.codebuddy.database.item.ItemServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;


/**
 * Created by Fabian on 10-6-2016.
 */
public class ItemTest {
    private ApplicationContext context;
    ItemServiceImpl itemService;

    public ItemTest(){
        this.context = (new ClassPathXmlApplicationContext("spring.xml"));
        this.itemService = (ItemServiceImpl) getContext().getBean("itemServiceImpl");
    }

    @Test
    @Transactional
    public void getEquippedItemsFromUser() throws Exception{
        assertNotNull(this.itemService.getEquippedItemsFromUser(TestInfo.TESTID));
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
}
