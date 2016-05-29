package jenky.codebuddy;

import jenky.codebuddy.database.services.UserDaoImplService;
import jenky.codebuddy.models.entities.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.style.ToStringCreator;

import java.util.Date;
import java.util.Random;

/**
 * Created by joost on 24-5-2016.
 */
public class TestclassForDAO {
    public static void main(String[] args) {

        System.out.println("************** BEGINNING PROGRAM **************");

        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Random rand = new Random();

        //met deze class kan je testen of die max 4 users error is opgelost
        //probeer de goede sessie te pakken in de persist method in userservice.class
        new Thread(new Runnable() {
            @Override
            public void run() {
                int wachTijd = (int) (Math.random() * 7);
                try {
                    Thread.sleep(wachTijd * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int x = 100 ; x < 1100; x++) {
                    System.out.println("x = " + rand.nextInt());
                    UserDaoImplService userDaoImplService = (UserDaoImplService)context.getBean("userDaoImplService");
                    User u = new User();
                    u.setEmail("test" + rand.nextInt());
                    u.setPassword("test");
                    u.setCreated_at(new Date());
                    u.setDeleted_at(new Date());
                    u.setUpdated_at(new Date());
                    u.setJenkycoins(1);
                    userDaoImplService.persist(u);
                }

            }
        }).start();

    }
}