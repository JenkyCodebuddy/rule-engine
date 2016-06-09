package jenky.codebuddy;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.database.score.ScoreServiceImpl;
import jenky.codebuddy.models.entities.Score;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by Fabian on 9-6-2016.
 */
public class ScoreTest extends GenericDaoImpl<Score, Integer> {

    private ApplicationContext context;
    ScoreServiceImpl scoreService;


    public ScoreTest() {
        setContext(new ClassPathXmlApplicationContext("spring.xml"));
        this.scoreService = (ScoreServiceImpl) getContext().getBean("scoreServiceImpl");

    }

    public static void main(String[] args) {

    }

    @Test
    @Transactional
    public void getAllScore() throws Exception {
        Score testScore = new Score();
        List<Score> scores = scoreService.getAllScores();
        assertEquals(testScore, scores);
    }

    @Test
    @Transactional
    public void saveScore() throws Exception {
        Score testScore = new Score();
        scoreService.save(testScore);
    }

    @Test
    public void testModel() throws Exception{
//        UserCommit c = new UserCommit("Joost","joost1235@hotmail.com","master","asdasd12113ui1h3ir","cyka/TestclassForDAO/idi/nahui");
//        assertNotEquals(c.getEmail(),"");
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    public ScoreServiceImpl getScoreService() {
        return scoreService;
    }

    public void setScoreService(ScoreServiceImpl scoreService) {
        this.scoreService = scoreService;
    }
}
