package jenky.codebuddy.database.dao;

import jenky.codebuddy.models.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by joost on 27-5-2016.
 */
@Component
public class UserDaoImplService {

    private UserDaoImpl productDao;

    public UserDaoImpl getProductDao() {
        return productDao;
    }

    @Autowired
    public void setProductDao(UserDaoImpl productDao) {
        this.productDao = productDao;
    }

    public List<User> fetchAllUsers(){
        return getProductDao().selectAll();
    }
}
