package jenky.codebuddy.database.item;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.models.entities.Item;
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
    public List<Item> getAllPuchasableItems(int user_id) {
        String hql = "FROM Item item WHERE NOT EXISTS (from ItemUser item_has_user WHERE item_has_user.user = :user_id AND item_has_user.item = item.id)";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("user_id",user_id);
        Optional<List<Item>> allItems = Optional.ofNullable(query.list());
        return allItems.get();
    }

    /**
     * @param user_id
     * @return
     */
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
    public List<Item> getAllEquipmentFromUser(int user_id) {
        String hql = "FROM Item item " +
                "LEFT JOIN FETCH item.itemusers as item_has_users " +
                "WHERE item_has_users.user= :user_id ORDER BY item_has_users.equipped DESC ";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("user_id",user_id);
        Optional<List<Item>> equippedItems = Optional.ofNullable(query.list());
        return equippedItems.get();
    }

    /**
     * @param item_id
     * @return
     */
    @Override
    public boolean checkIfItemExists(int item_id) {
        String hql = "FROM Item i WHERE i.id= :item_id";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("item_id",item_id);
        Optional<Item> result = Optional.ofNullable((Item) query.uniqueResult());
        return result.isPresent();
    }

    /**
     * @param item_id
     * @return
     */
    @Override
    public Item getItemIfExists(int item_id) {
        String hql = "FROM Item i WHERE i.id = :item_id";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter("item_id",item_id);
        Optional<Item> result = Optional.ofNullable((Item) query.uniqueResult());
        return result.get();
    }

    @Override
    public void equipItemsForUser(int user_id, int item_id) {
        String hql = "UPDATE ItemUser item_has_user SET equipped = true WHERE  item_has_user.user =:user_id AND item_has_user.item =:item_id";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        query.setInteger("user_id",user_id);
        query.setInteger("item_id",item_id);
        query.executeUpdate();
    }

    @Override
    public List<Item> getDefaultItems() {
        String hql = "FROM Item item WHERE item.id = 1 OR item.id = 2 OR item.id = 3 OR item.id = 4";
        Query query = getSessionFactory().getCurrentSession().createQuery(hql);
        List<Item> defaultItems = (List<Item>)query.list();
        return defaultItems;
    }

    @Override
    public void saveItem(Item item){
        super.add(item);
    }

    @Override
    public void deleteItem(Item item) {
        super.delete(item);
    }
}
