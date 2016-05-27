package jenky.codebuddy;

import jenky.codebuddy.database.dao.UserDaoImplService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by joost on 24-5-2016.
 */
public class TestclassForDAO {
    public static void main(String[] args) {

        System.out.println("************** BEGINNING PROGRAM **************");

        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        UserDaoImplService personService = (UserDaoImplService) context.getBean("userService");
        personService.fetchAllUsers();

    }
}