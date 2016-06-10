package jenky.codebuddy;

import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.database.score.ScoreServiceImpl;
import jenky.codebuddy.models.entities.Score;
import jenky.codebuddy.models.entities.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Fabian on 9-6-2016.
 */
public class ScoreTest extends GenericDaoImpl<Score, Integer> {

    private final String TESTEMAIL = "joost1235@hotmail.com";
    private final Integer TESTID = 1;

    private ApplicationContext context;
    private ScoreServiceImpl scoreService;
    private Score testScore;

    public ScoreTest() {
        this.context = (new ClassPathXmlApplicationContext("spring.xml"));
        this.scoreService = (ScoreServiceImpl) getContext().getBean("scoreServiceImpl");
        testScore = new Score();
        User testuser = new User();
        testuser.setEmail(TESTEMAIL);
        testScore.setUser(testuser);
    }

    @Test
    @Transactional
    @Rollback
    public void saveScore() throws Exception {
        scoreService.save(testScore);
    }

    @Test
    @Transactional
    public void getPreviousScores()throws Exception{
        assertNotNull(scoreService.getPreviousScores(TESTEMAIL));
    }

    @Test
    @Transactional
    public void deleteTestScore()throws Exception{
        scoreService.delete(testScore);
    }

    @Test
    @Transactional
    public void getAverageScore()throws Exception{
        assertNotNull(scoreService.getAvgScoreFromUser(TESTID));
    }

    @Test
    @Transactional
    public void getTotalScoreFromUser()throws Exception{
        assertNotNull(scoreService.getTotalScoreFromUser(TESTID));
    }

    @Test
    @Transactional
    public void getScoresFromProject()throws Exception{
        List<Score> scores = scoreService.getScoresFromProject(TESTID);
        assertNotNull(scores);
    }

    @Test
    @Transactional
    public void checkIfUserHasScores()throws Exception{
        boolean check = scoreService.checkIfUserHasScores(TESTID);
        assertNotNull(check);
    }


    private ApplicationContext getContext() {
        return context;
    }

    private void setContext(ApplicationContext context) {
        this.context = context;
    }

    public ScoreServiceImpl getScoreService() {
        return scoreService;
    }

    public void setScoreService(ScoreServiceImpl scoreService) {
        this.scoreService = scoreService;
    }
}
