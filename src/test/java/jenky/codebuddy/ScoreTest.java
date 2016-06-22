package jenky.codebuddy;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jenky.codebuddy.database.score.ScoreService;
import jenky.codebuddy.database.score.ScoreServiceImpl;
import jenky.codebuddy.models.entities.Score;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.gson.SonarResponse;
import jenky.codebuddy.services.DatabaseFactory;
import jenky.codebuddy.services.MessagingService;
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
    private MessagingService messagingService;
    private ScoreUserService scoreUserService;
    private User testUser;

    @Before
    public void setUp() {
        this.scoreUserService = new ScoreUserServiceImpl();
        this.testScore = new Score();
        this.messagingService = new MessagingService();
        this.testUser = DatabaseFactory.getUserService().getUserIfExists(TestInfo.TESTEMAIL);
        //testUser.setUser_id(962);
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
       // assertNotNull(DatabaseFactory.getScoreService().getScoresFromProject(TestInfo.TESTID));
    }

    @Test
    @Transactional
    public void checkIfUserHasScores()throws Exception{
        assertNotNull(DatabaseFactory.getScoreService().checkIfUserHasScores(TestInfo.TESTID));
    }

    @Test
    public void sendPush(){
        messagingService.sendPush("test" , "test", "Results are in, check your profile!", "1000", "cM6L9vKZx4Y:APA91bG9DxVbwXUjOx9Ag50tl0TRhxvcpLepq-f4PKF34h20NY9LCyMU5WBm4Q8Dgln30uwX5hNuxgXC_XT3QGEIPGswwzC1qsUWozh0C-pecnbANtTqGPX3sK_m_8SwFPR_PE5NZukJ");
    }

    @Test
    public void generateTip(){
        scoreUserService.generateTips(testUser, testUser.getMessageToken(), "rule-engine");
    }
}
