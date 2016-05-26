package jenky.codebuddy;

import jenky.codebuddy.database.dao.ProductDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by joost on 24-5-2016.
 */
public class TestclassForDAO {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        ProductDao dao = (ProductDao) context.getBean("myProductDao");
        System.out.println(" = " + dao.loadProductsByCategory("joost1235@hotmail.com"));

    }
}