package jenky.codebuddy;

import jenky.codebuddy.database.services.ServiceFactory;
import jenky.codebuddy.database.services.UserDaoImplService;
import jenky.codebuddy.database.services.UserService;
import jenky.codebuddy.models.entities.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by joost on 24-5-2016.
 */
public class TestclassForDAO {
    public static void main(String[] args) {

        System.out.println("************** BEGINNING PROGRAM **************");

        //ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        //met deze class kan je testen of die max 4 users error is opgelost
        //probeer de goede sessie te pakken in de persist method in userservice.class
        for(int x = 0 ; x < 100; x++) {
            UserDaoImplService userDaoImplService = (UserDaoImplService) new ServiceFactory().getService("userDaoImplService");
            User u = new User();
            userDaoImplService.persist(u);
        }
    }
}