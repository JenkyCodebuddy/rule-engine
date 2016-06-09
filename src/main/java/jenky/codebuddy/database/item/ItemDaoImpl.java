package jenky.codebuddy.database.item;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.Achievement;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Persistence of ItemDao. Inherits GenericDao and implements the ItemDao interface.
 */
@Repository
public class ItemDaoImpl extends GenericDaoImpl<Item, Integer> implements ItemDao {

    /**
     * Return all the items
     * @return List of Items
     */
    @Override
    public List<Item> getAllItems() {
        return super.findAll();
    }

    @Override
    public List<Item> getEquippedItemsFromUser(int user_id) {
        String hql = "SELECT item.id, item.name, item.image, item.type " +
                "FROM Item item " +
                "INNER JOIN item.itemusers as item_has_users " +
                "WHERE item_has_users.user= :user_id AND item_has_users.equipped = true";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("user_id",user_id);
        List<Item> equippedItems = query.list();
        return equippedItems;
    }
}
