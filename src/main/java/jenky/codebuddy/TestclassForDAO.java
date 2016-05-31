package jenky.codebuddy;

import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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

        //This is temporary for testing purposes
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
                    //GenericDatabaseService genericDatabaseService = (GenericDatabaseService) context.getBean("genericDatabaseService");
                    //UserDaoImplService userDaoImplService = (UserDaoImplService)context.getBean("userDaoImplService");

                    User u = new User();
                    u.setEmail("test" + rand.nextInt());
                    u.setPassword("test");
                    u.setCreated_at(new Date());
                    u.setDeleted_at(new Date());
                    u.setUpdated_at(new Date());
                    u.setJenkycoins(1);

                    Commit c = new Commit();
                    c.setCreated_at(new Date());

                    /*Company bla = new Company();
                    bla.setCreated_at(new Date());*/
                   // genericDatabaseService.persist(c);
                }

            }
        }).start();

    }
}