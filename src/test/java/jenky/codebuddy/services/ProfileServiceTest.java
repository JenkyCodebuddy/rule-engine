package jenky.codebuddy.services;

import jenky.codebuddy.TestInfo;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.rest.Profile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Joost on 20-8-2016.
 */
public class ProfileServiceTest {
    private User user;
    private ProfileService profileService;
    @Before
    public void setUp(){
        user = DatabaseFactory.getUserService().getUserIfExists(TestInfo.TESTEMAIL);
        profileService = new ProfileService();
    }
    @Test
    public void returnProfile() throws Exception {
        Profile profile = profileService.returnProfile(user.getAuthentication().getToken());
        assertEquals(420.0, profile.getAvgScore(),0);
        assertEquals(420, profile.getTotalScore(),0);
        assertEquals(1, profile.getProjectCount(),0);
        assertEquals(1, profile.getAchievementCount(),0);
        assertEquals(200, profile.getResponseCode(),0);
    }
}