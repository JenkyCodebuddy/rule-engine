package jenky.codebuddy.database.item;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.User;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
        String hql = "SELECT item.id, item.name, item.image, item.type " +
                "FROM Item item";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        Optional<List<Item>> allItems = Optional.ofNullable(query.list());
        return allItems.get();
    }

    @Override
    public List<Item> getEquippedItemsFromUser(int user_id) {
        String hql = "FROM Item item " +
                "LEFT JOIN FETCH item.itemusers as item_has_users " +
                "WHERE item_has_users.user= :user_id AND item_has_users.equipped = true";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("user_id",user_id);
        Optional<List<Item>> equippedItems = Optional.ofNullable(query.list());
        return equippedItems.get();
    }

    @Override
    public boolean checkIfItemExists(int item_id) {
        String hql = "FROM Item i WHERE i.id= :item_id";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("item_id",item_id);
        Optional<Item> result = Optional.ofNullable((Item) query.uniqueResult());
        return (result.isPresent());
    }

    @Override
    public Item getItemIfExists(int item_id) {
        String hql = "FROM Item i WHERE i.id = :item_id";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("item_id",item_id);
        Optional<Item> result = Optional.ofNullable((Item) query.uniqueResult());
        return(result.get());
    }
}
