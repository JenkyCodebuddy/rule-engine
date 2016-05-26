package jenky.codebuddy.database.dao;

import jenky.codebuddy.models.entities.Achievement;
import jenky.codebuddy.models.entities.User;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import java.util.Collection;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by joost on 26-5-2016.
 */

@Repository
public class ProductDaoImpl implements ProductDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Collection loadProductsByCategory(String email) {
        return this.sessionFactory.getCurrentSession()
                .createQuery("from User user where user.email=?")
                .setParameter(0, email)
                .list();
    }
}
