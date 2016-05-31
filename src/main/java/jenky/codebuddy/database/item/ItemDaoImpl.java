package jenky.codebuddy.database.item;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by joost on 30-5-2016.
 */
@Repository
public class ItemDaoImpl extends GenericDaoImpl<Item, Integer> implements ItemDao {

    @Override
    public List<Item> getAllItems() {
        return super.findAll();
    }
}
