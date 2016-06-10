package jenky.codebuddy;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jenky.codebuddy.database.generic.GenericDaoImpl;
import jenky.codebuddy.database.score.ScoreServiceImpl;
import jenky.codebuddy.models.entities.Score;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.gson.SonarResponse;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Fabian on 9-6-2016.
 */
public class ScoreTest{

    private ApplicationContext context;
    private ScoreServiceImpl scoreService;
    private Score testScore;

    public ScoreTest() {
        this.context = (new ClassPathXmlApplicationContext("spring.xml"));
        this.scoreService = (ScoreServiceImpl) getContext().getBean("scoreServiceImpl");
        testScore = new Score();
        User testuser = new User();
        testuser.setEmail(TestInfo.TESTEMAIL);
        testScore.setUser(testuser);
    }

    @Test
    public void gsonParse() throws Exception{
        Gson gson = new Gson();
        Type sonar = new TypeToken<List<SonarResponse>>(){}.getType();
        List<SonarResponse> sonarResponseList = gson.fromJson(TestInfo.TESTJSON.replaceAll("\\s",""), sonar);
        assertNotNull(sonarResponseList.get(0));
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
        assertNotNull(scoreService.getPreviousScores(TestInfo.TESTEMAIL));
    }

    @Test
    @Transactional
    public void deleteTestScore()throws Exception{
        scoreService.delete(testScore);
    }

    @Test
    @Transactional
    public void getAverageScore()throws Exception{
        assertNotNull(scoreService.getAvgScoreFromUser(TestInfo.TESTID));
    }

    @Test
    @Transactional
    public void getTotalScoreFromUser()throws Exception{
        assertNotNull(scoreService.getTotalScoreFromUser(TestInfo.TESTID));
    }

    @Test
    @Transactional
    public void getScoresFromProject()throws Exception{
        assertNotNull(scoreService.getScoresFromProject(TestInfo.TESTID));
    }

    @Test
    @Transactional
    public void checkIfUserHasScores()throws Exception{
        assertNotNull(scoreService.checkIfUserHasScores(TestInfo.TESTID));
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
