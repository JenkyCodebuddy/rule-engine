package jenky.codebuddy;

import jenky.codebuddy.database.user.UserServiceImpl;
import jenky.codebuddy.models.entities.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;


/**
 * Created by Fabian on 10-6-2016.
 */
public class UserTest {

    private ApplicationContext context;
    private UserServiceImpl userService;

    public UserTest() throws Exception {
        this.context = new ClassPathXmlApplicationContext("spring.xml");
        this.userService = (UserServiceImpl) getContext().getBean("userServiceImpl");
    }

    @Test
    @Transactional
    public void getAllUsers() throws Exception{
        assertNotNull(userService.getAllUsers());
    }

    @Test
    @Transactional
    public void checkIfUserExists() throws Exception{
        assertNotNull(userService.checkIfUserExists(TestInfo.TESTEMAIL));
    }

    @Test
    @Transactional
    public void getUserIfExists() throws Exception{
        assertNotNull(userService.getUserIfExists(TestInfo.TESTEMAIL));
    }

    @Test
    @Transactional
    @Rollback
    public void setPasswordForUser(){
        userService.setPasswordForUser(TestInfo.TESTPASSWORD, TestInfo.TESTEMAIL, TestInfo.TESTDATE);
    }

    public ApplicationContext getContext() throws Exception{
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    public UserServiceImpl getUserService() {
        return userService;
    }

    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }
}
