package jenky.codebuddy.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import jenky.codebuddy.TestInfo;
import jenky.codebuddy.models.entities.*;
import jenky.codebuddy.token.models.Token;
import org.hibernate.boot.model.relational.Database;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.security.Key;
import java.util.Date;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        AchievementsServiceTest.class,
        AuthenticationServiceTest.class,
        EquipmentServiceTest.class,
        LoginServiceTest.class,
        ProfileServiceTest.class,
        ProjectsServiceTest.class,
        ShopServiceTest.class,
        SignUpServiceTest.class
})

public class ServiceTestSuite {

    private static User user;
    private static Authentication authentication;
    private static ServiceTestSuite serviceTestSuite;
    private static Achievement achievement;
    private static AchievementUser achievementUser;
    private static Item item;
    private static ItemUser itemUser;
    private static Project project;
    private static Commit commit;
    private static Score score;

    @BeforeClass
    public static void setUp() {
        serviceTestSuite = new ServiceTestSuite();
        serviceTestSuite.createTestUser();
        serviceTestSuite.createTestAuthentication();
        serviceTestSuite.createTestAchievement();
        serviceTestSuite.linkTestAchievementToTestUser();
        serviceTestSuite.createTestItem();
        serviceTestSuite.linkTestItemToUser();
        serviceTestSuite.createTestProject();
        serviceTestSuite.createTestCommit();
        serviceTestSuite.createTestScore();
    }

    @AfterClass
    public static void tearDown() {
        serviceTestSuite.deleteUser();
        serviceTestSuite.deleteAuthentication();
        serviceTestSuite.deleteAchievementUser();
        serviceTestSuite.deleteAchievement();
        serviceTestSuite.deleteItem();
        serviceTestSuite.deleteProject();
        serviceTestSuite.deleteScore();
        serviceTestSuite.deleteCommit();
        serviceTestSuite.deleteItemUser();
    }

    private void createTestUser() {
        user = new User();
        user.setEmail(TestInfo.TESTEMAIL);
        user.setUser_id(TestInfo.TESTID);
        user.setPassword(TestInfo.TESTPASSWORD);
        user.setCreated_at(new Date());
        user.setJenkycoins(TestInfo.TESTJENKYCOINS);
        DatabaseFactory.getUserService().save(user);
    }

    private void createTestAuthentication(){
        Token token = createTestToken();
        authentication = new Authentication();
        authentication.setUser(user);
        authentication.setToken(token.getToken());
        authentication.setAuth_key(AuthenticationService.keyToString(token.getKey()));
        authentication.setCreated_at(TestInfo.TESTDATE);
        DatabaseFactory.getAuthenticationService().saveAuthentication(authentication);
    }

    private void createTestAchievement(){
        achievement = new Achievement();
        achievement.setDescription(TestInfo.TESTACHIEVEMENTDESCRIPTION);
        achievement.setName(TestInfo.TESTACHIEVEMENTNAME);
        achievement.setProgress(TestInfo.TESTACHIEVEMENTPROGRESS);
        achievement.setCreated_at(TestInfo.TESTDATE);
        DatabaseFactory.getAchievementService().saveAchievement(achievement);
    }

    private void createTestProject(){
        project = new Project();
        project.setName(TestInfo.TESTPROJECTNAME);
        DatabaseFactory.getProjectService().addProject(project);
    }

    private void createTestCommit(){
        commit = new Commit();
        commit.setProject(project);
        DatabaseFactory.getCommitService().saveCommit(commit);
    }

    private void createTestScore(){
        score = new Score();
        score.setCommit(commit);
        score.setUser(user);
        score.setScore(TestInfo.TESTSCORE);
        DatabaseFactory.getScoreService().save(score);
    }

    private void linkTestAchievementToTestUser(){
        achievementUser = new AchievementUser();
        achievementUser.setAchievement(achievement);
        achievementUser.setUser(user);
        achievementUser.setProgress(100);
        DatabaseFactory.getAchievementUserService().saveOrUpdate(achievementUser);
    }

    private Token createTestToken(){
        Token token = new Token();
        Key key = MacProvider.generateKey();
        token.setToken(Jwts.builder().setSubject(TestInfo.TESTEMAIL).signWith(SignatureAlgorithm.HS512, key).compact());
        token.setKey(key);
        token.setId(TestInfo.TESTEMAIL);
        return token;
    }

    private void createTestItem(){
        item = new Item();
        item.setName(TestInfo.TESTITEMNAME);
        item.setType(TestInfo.TESTITEMTYPE);
        item.setImage(TestInfo.TESTITEMIMAGE);
        item.setPrice(TestInfo.TESTITEMPRICE);
        item.setCreated_at(TestInfo.TESTDATE);
        DatabaseFactory.getItemService().saveItem(item);
    }

    private void linkTestItemToUser(){
        itemUser = new ItemUser();
        itemUser.setItem(item);
        itemUser.setUser(user);
        DatabaseFactory.getItemUserService().updateItemUser(itemUser);
    }

    private void deleteUser(){
        DatabaseFactory.getUserService().deleteUser(user);
    }

    private void deleteAuthentication(){
        DatabaseFactory.getAuthenticationService().deleteAuthentication(authentication);
    }

    private void deleteAchievement(){
        DatabaseFactory.getAchievementService().deleteAchievement(achievement);
    }

    private void deleteAchievementUser(){
        DatabaseFactory.getAchievementUserService().deleteAchievementUser(achievementUser);
    }

    private void deleteItem(){
        DatabaseFactory.getItemService().deleteItem(item);
    }

    private void deleteItemUser(){
        DatabaseFactory.getItemUserService().deleteItemUser(itemUser);
    }

    private void deleteScore(){
        DatabaseFactory.getScoreService().delete(score);
    }

    private void deleteCommit(){
        DatabaseFactory.getCommitService().deleteCommit(commit);
    }

    private void deleteProject(){
        DatabaseFactory.getProjectService().deleteProject(project);
    }
}
