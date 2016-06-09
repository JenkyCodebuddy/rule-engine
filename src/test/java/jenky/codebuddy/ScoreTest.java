package jenky.codebuddy;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.database.score.ScoreServiceImpl;
import jenky.codebuddy.models.entities.Score;
import jenky.codebuddy.models.entities.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Fabian on 9-6-2016.
 */
public class ScoreTest extends GenericDaoImpl<Score, Integer> {

    private final String TESTEMAIL = "joost1235@hotmail.com";
    private ApplicationContext context;
    ScoreServiceImpl scoreService;
    Score testScore;
    User testuser;

    public ScoreTest() {
        setContext(new ClassPathXmlApplicationContext("spring.xml"));
        this.scoreService = (ScoreServiceImpl) getContext().getBean("scoreServiceImpl");
        testScore = new Score();
        testScore.setUser(testuser);
        testuser = new User();
        testuser.setEmail(TESTEMAIL);
    }

    public static void main(String[] args) {

    }

    @Test
    @Transactional
    public void saveScore() throws Exception {
        scoreService.save(testScore);
    }

    @Test
    @Transactional
    public void getPreviousScores()throws Exception{
        List<Score> scores = scoreService.getPreviousScores(TESTEMAIL);
        assertEquals(testScore.getClass(), scores.get(1).getClass());
    }

    @Test
    @Transactional
    public void deleteTestScore()throws Exception{
        scoreService.delete(testScore);
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
