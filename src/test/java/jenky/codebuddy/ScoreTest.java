package jenky.codebuddy;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jenky.codebuddy.database.score.ScoreService;
import jenky.codebuddy.database.score.ScoreServiceImpl;
import jenky.codebuddy.models.entities.Score;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.gson.SonarResponse;
import jenky.codebuddy.services.DatabaseFactory;
import jenky.codebuddy.services.ScoreUserService;
import jenky.codebuddy.services.ScoreUserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Fabian on 9-6-2016.
 */
public class ScoreTest{


    private Score testScore;
    private ScoreUserService scoreUserService;

    @Before
    public void setUp() {
        this.testScore = new Score();
        this.scoreUserService = new ScoreUserServiceImpl();
        User testUser = new User();
        testUser.setEmail(TestInfo.TESTEMAIL);
        testScore.setUser(testUser);
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
        DatabaseFactory.getScoreService().save(testScore);
    }

    @Test
    @Transactional
    public void getPreviousScores()throws Exception{
        assertNotNull(DatabaseFactory.getScoreService().getPreviousScores(TestInfo.TESTEMAIL));
    }

    @Test
    @Transactional
    public void deleteTestScore()throws Exception{
        DatabaseFactory.getScoreService().delete(testScore);
    }

    @Test
    @Transactional
    public void getAverageScore()throws Exception{
        assertNotNull(DatabaseFactory.getScoreService().getAvgScoreFromUser(TestInfo.TESTID));
    }

    @Test
    @Transactional
    public void getTotalScoreFromUser()throws Exception{
        assertNotNull(DatabaseFactory.getScoreService().getTotalScoreFromUser(TestInfo.TESTID));
    }

    @Test
    @Transactional
    public void getScoresFromProject()throws Exception{
        assertNotNull(DatabaseFactory.getScoreService().getScoresFromProject(TestInfo.TESTID));
    }

    @Test
    @Transactional
    public void checkIfUserHasScores()throws Exception{
        assertNotNull(DatabaseFactory.getScoreService().checkIfUserHasScores(TestInfo.TESTID));
    }

    @Test
    public void sendPush(){
        scoreUserService.sendPush("test" , "dtr1hEmd3M4:APA91bHprc_uOuaNJbGMUjwuARMJry4acTvXERJMxMVbU0msvPbMFlELEJXRosnpHK7rQ2A-7doNNaDHKg7SNK4f10l_M6-hI5ZzvOUQK_LuylfjEU1OR3UtDm6S0-vsYIbGnDkt9SOJ");
    }
}
