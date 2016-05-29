package jenky.codebuddy.database.dao;

import jenky.codebuddy.database.services.UserDaoImplService;
import jenky.codebuddy.models.entities.User;

import javax.persistence.Column;
import java.util.Collection;
import java.util.List;


public interface UserDao {

    public void persist(User user);

}
