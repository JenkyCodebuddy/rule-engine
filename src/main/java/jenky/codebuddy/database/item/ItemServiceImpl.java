package jenky.codebuddy.database.item;

import jenky.codebuddy.database.generic.GenericServiceImpl;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by joost on 30-5-2016.
 */
@Service
public class ItemServiceImpl extends GenericServiceImpl<Item, Integer> implements ItemService {

    private ItemDao itemDao;

    @Autowired
    public ItemServiceImpl(@Qualifier("itemDaoImpl") ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public ItemServiceImpl(){

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Item> getAllItems() {
        return itemDao.getAllItems();
    }
}
