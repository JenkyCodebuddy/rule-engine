package jenky.codebuddy;

import jenky.codebuddy.database.score.ScoreServiceImpl;
import jenky.codebuddy.models.entities.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by joost on 24-5-2016.
 */
public class TestclassForDAO {
    public static void main(String[] args) {

        System.out.println("************** BEGINNING PROGRAM **************");

        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        ScoreServiceImpl scoreService = (ScoreServiceImpl) context.getBean("scoreServiceImpl");
        User u = new User();
        u.setUser_id(1);
        scoreService.getAvgScoreFromUser(u.getUser_id());


    }
}