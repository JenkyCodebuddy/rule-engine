package jenky.codebuddy.services;

import jenky.codebuddy.models.entities.*;
import jenky.codebuddy.models.rest.Achievements;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by joost on 12-6-2016.
 */
public class AchievementServiceTest {

    private static UserAchievementsService achievementsServiceImpl;
    private static Achievement achievement1;
    private static Achievement achievement2;
    private static Achievement achievement3;
    private static Achievements allAchievements;
    private static String token;

    @BeforeClass
    public static void setup() {
        achievementsServiceImpl = Mockito.mock(UserAchievementsService.class);
        achievement1 = new Achievement(null, "Commit master", "Get 5 commits", new Date(), new Date(), new Date(), new Date());
        achievement2 = new Achievement(null, "10 Achievements", "Unlock 10 achievements", new Date(), new Date(), new Date(), new Date());
        achievement3 = new Achievement(null, "KING", "You unlocked all achievements", new Date(), new Date(), new Date(), new Date());
        allAchievements = new Achievements(Arrays.asList(achievement1,achievement2,achievement3),200);
        token = "TEST_TOKEN";

        Mockito.when(achievementsServiceImpl.returnAchievements(token)).thenReturn(allAchievements);
    }

    @Test
    public void testReturnAchievements() throws Exception {
        Achievements achievements = achievementsServiceImpl.returnAchievements("TEST_TOKEN");
        Assert.assertNotNull(achievements);
        List<Achievement> achievementList = achievements.getAchievements();
        Assert.assertEquals(3,achievementList.size());
        Achievement testAchievement = achievementList.get(0);
        Assert.assertEquals("Commit master", testAchievement.getName());
        Assert.assertEquals("Get 5 commits", testAchievement.getDescription());
    }
}
