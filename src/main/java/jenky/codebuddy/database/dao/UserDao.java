package jenky.codebuddy.database.dao;

import jenky.codebuddy.database.services.UserDaoImplService;
import jenky.codebuddy.models.entities.User;

import javax.persistence.Column;
import java.util.Collection;
import java.util.List;

/**
 * Created by joost on 26-5-2016.
 */
public interface UserDao {

    public void persist(User user);

}
