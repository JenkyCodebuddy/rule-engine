package jenky.codebuddy.services;

import jenky.codebuddy.TestInfo;
import jenky.codebuddy.models.entities.Achievement;
import jenky.codebuddy.models.entities.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Joost on 20-8-2016.
 */
public class AchievementsServiceTest {

    private AchievementsService achievementsService;
    private User user;
    @Before
    public void setup(){
        user = DatabaseFactory.getUserService().getUserIfExists(TestInfo.TESTEMAIL);
        achievementsService = new AchievementsService();
    }
    @Test
    public void returnAchievements() throws Exception {
        achievementsService.returnAchievements(user.getAuthentication().getToken());
    }
}