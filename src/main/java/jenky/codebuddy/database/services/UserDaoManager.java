package jenky.codebuddy.database.services;

import jenky.codebuddy.models.entities.User;

/**
 * Created by Fabian on 29-5-2016.
 */
public interface UserDaoManager {
    public void persist(User user);
}
