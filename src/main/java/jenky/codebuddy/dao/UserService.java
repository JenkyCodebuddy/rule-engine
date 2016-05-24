package jenky.codebuddy.dao;

import jenky.codebuddy.models.databaseModels.User;

import java.util.List;

/**
 * Created by joost on 24-5-2016.
 */
public class UserService {

    private static Dao userDao;

    public UserService() {
        userDao = new Dao(User.class);
    }

    public void persist(User user) {
        userDao.openCurrentSessionwithTransaction();
        userDao.persist(user);
        userDao.closeCurrentSessionwithTransaction();
    }

    public void update(User user) {
        userDao.openCurrentSessionwithTransaction();
        userDao.update(user);
        userDao.closeCurrentSessionwithTransaction();
    }

    public User findById(int id) {
        userDao.openCurrentSession();
        User user = (User) userDao.findById(id);
        userDao.closeCurrentSession();
        return user;
    }

    public void delete(int id) {
        userDao.openCurrentSessionwithTransaction();
        User user = (User) userDao.findById(id);
        userDao.delete(user);
        userDao.closeCurrentSessionwithTransaction();
    }

    public List<User> findAll() {
        userDao.openCurrentSession();
        List<User> users = userDao.findAll();
        userDao.closeCurrentSession();
        return users;
    }

    public boolean checkIfRecordExists(String column, String value){
        userDao.openCurrentSession();
        userDao.checkIfRecordExists(column,value);
        userDao.closeCurrentSession();
        return true;
    }

    public Dao userDao() {
        return userDao;
    }
}
