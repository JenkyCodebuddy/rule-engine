package jenky.codebuddy;

import jenky.codebuddy.database.dao.GenericDataAccessObject;
import jenky.codebuddy.database.dao.UserDaoImplService;
import jenky.codebuddy.database.services.GenericDatabaseService;
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

        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        GenericDatabaseService p = (GenericDatabaseService) context.getBean("genericDatabaseService");
        p.findAll();
        //System.out.println("list = " + list);

    }
}