package jenky.codebuddy.services;

import jenky.codebuddy.database.item.ItemServiceImpl;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.rest.Items;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by joost on 6-6-2016.
 */
public class ShopService {

    private ApplicationContext context;

    public ShopService() {
        this.context = new ClassPathXmlApplicationContext("spring.xml");
    }

    public Items getAllItems(){
        ItemServiceImpl itemService = (ItemServiceImpl) getContext().getBean("itemServiceImpl");
        List<Item> allItems = itemService.getAllItems();
        Items allItemsModel = new Items();
        allItemsModel.setItems(allItems);
        return allItemsModel;
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }
}
