package jenky.codebuddy.services;

import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.Project;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.rest.Profile;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by joost on 12-6-2016.
 */
public class ProfileServiceTest {

    private static ProfileService profileService;
    private static Profile profile;
    private static Item item1;
    private static Item item2;
    private static Commit commit1;
    private static Commit commit2;
    private static Project project;
    private static double totalscore;
    private static double avgScore;
    private static double achievementCount;
    private static double projectCount;
    private static String token;

    @BeforeClass
    public static void setup(){
        profileService = Mockito.mock(ProfileService.class);
        //project = new Project(null, null, "ICTLAB PROJECT", new Date(), new Date(), new Date());
        item1 = new Item(null, "Iron chestplate", "Body armour", "chestplate.jpeg", 400, new Date(), new Date(), new Date());
        item2 = new Item(null, "Bronze helmet", "Headgear", "helmet.jpeg", 100, new Date(), new Date(), new Date());
       // commit1 = new Commit(null, project, new Date(), new Date(), new Date(), "3k4jh5ghk3j3","origin/master");
      //  commit2 = new Commit(null, null, new Date(), new Date(), new Date(), "s87d8dsf87", "origin/develop");
        totalscore = 10000;
        avgScore = 300;
        achievementCount = 12;
        projectCount = 3;
        //profile = new Profile(Arrays.asList(item1,item2),Arrays.asList(commit1,commit2),totalscore,avgScore,achievementCount,projectCount, 200);
        token = "TEST_TOKEN";
        Mockito.when(profileService.returnProfile(token)).thenReturn(profile);
                /*double totalScore, double avgScore, double achievementCount, double projectCount,*/
    }

    @Test
    public void testReturnProfile(){
        Profile profile = profileService.returnProfile("TEST_TOKEN");
        Assert.assertNotNull(profile);
        List<Item> equippedItems = profile.getEquippedItems();
        Assert.assertEquals("Iron chestplate", equippedItems.get(0).getName());
        Assert.assertEquals("chestplate.jpeg", equippedItems.get(0).getImage());
        Assert.assertEquals("Headgear", equippedItems.get(1).getType());
        Assert.assertEquals(100, equippedItems.get(1).getPrice(),0);
       // List<Commit> commits = profile.getCommits();
       // Assert.assertEquals("origin/master",commits.get(0).getBranch());
       // Assert.assertEquals("s87d8dsf87", commits.get(1).getSha());
       // Project project = commits.get(0).getProject();
      //  Assert.assertEquals("ICTLAB PROJECT", project.getName());
       // Assert.assertEquals(10000, profile.getTotalScore(),0);
       // Assert.assertEquals(300, profile.getAvgScore(),0);
       // Assert.assertEquals(12, profile.getAchievementCount(),0);
      //  Assert.assertEquals(3, profile.getProjectCount(),0);
    }
}

/* public User(String email, String password, Date created_at, Date updated_at, Date deleted_at, int jenkycoins, Verification verfication, Authentication authentication, Set<Score> scores, Set<AchievementUser> achievements, Set<ItemUser> itemusers) {
      */

/*
        user1 = new User("joost@hoi.nl","mijnpassword",new Date(),new Date(),new Date(), 10000, null, null, null, null, null);
        user2 = new User("chatman@msn.com","ikbenchatman",new Date(),new Date(),new Date(), 42069, null, null, null, null, null);*/