package jenky.codebuddy.services;

import jenky.codebuddy.database.achievement.AchievementServiceImpl;
import jenky.codebuddy.database.authentication.AuthenticationServiceImpl;
import jenky.codebuddy.database.commit.CommitServiceImpl;
import jenky.codebuddy.database.item.ItemServiceImpl;
import jenky.codebuddy.database.project.ProjectServiceImpl;
import jenky.codebuddy.database.score.ScoreServiceImpl;
import jenky.codebuddy.models.entities.Commit;
import jenky.codebuddy.models.entities.Item;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.rest.Profile;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by joost on 4-6-2016.
 */
public class ProfileService {

    String token;
    ApplicationContext context;
    ScoreServiceImpl scoreService;
    CommitServiceImpl commitService;
    AchievementServiceImpl achievementService;
    ProjectServiceImpl projectService;
    AuthenticationServiceImpl authenticationService;
    ItemServiceImpl itemService;

    public ProfileService() {
        setContext(new ClassPathXmlApplicationContext("spring.xml"));
        setAuthenticationService((AuthenticationServiceImpl) getContext().getBean("authenticationServiceImpl"));
        setScoreService((ScoreServiceImpl) getContext().getBean("scoreServiceImpl"));
        setCommitService((CommitServiceImpl) getContext().getBean("commitServiceImpl"));
        setProjectService((ProjectServiceImpl) getContext().getBean("projectServiceImpl"));
        setAchievementService((AchievementServiceImpl) getContext().getBean("achievementServiceImpl"));
        setItemService((ItemServiceImpl) getContext().getBean("itemServiceImpl"));
    }

    public Profile returnProfile(String token) {
        User user = getUserWithToken(token);
        if(scoreService.checkIfUserHasScores(user.getUser_id())) {
            List<Item> items = getEquippedItemsFromUser(user);
            List<Commit> commits = getCommitsFromUser(user);
            double avgScore = getAvgScoreFromUser(user);
            double totalScore = getTotalScoreFromUser(user);
            double achievementCount = getAchievementCountFromUser(user);
            double projectCount = getProjectCountFromUser(user);
            return new Profile(items, commits, avgScore, totalScore, achievementCount, projectCount);
        }
        else{
            return new Profile("User has no scores yet");
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ApplicationContext getContext() {
        return context;
    }

    public void setContext(ApplicationContext context) {
        this.context = context;
    }

    private User getUserWithToken(String token) {
        return getAuthenticationService().getAuthenticationIfTokenExists(token).getUser();
    }

    private List<Item> getEquippedItemsFromUser(User user){
        return getItemService().getEquippedItemsFromUser(user.getUser_id());
    }

    private List<Commit> getCommitsFromUser(User user) {
        return getCommitService().getCommitsFromUser(user.getUser_id());
    }

    private double getAvgScoreFromUser(User user){
        return getScoreService().getAvgScoreFromUser(user.getUser_id());
    }

    private double getTotalScoreFromUser(User user){
        return getScoreService().getTotalScoreFromUser(user.getUser_id());
    }

    private double getAchievementCountFromUser(User user){
        return getAchievementService().getAchievementCountFromUser(user.getUser_id());
    }

    private double getProjectCountFromUser(User user){
        return getProjectService().getProjectCountFromUser(user.getUser_id());
    }

    public ScoreServiceImpl getScoreService() {
        return scoreService;
    }

    public void setScoreService(ScoreServiceImpl scoreService) {
        this.scoreService = scoreService;
    }

    public CommitServiceImpl getCommitService() {
        return commitService;
    }

    public void setCommitService(CommitServiceImpl commitService) {
        this.commitService = commitService;
    }

    public AchievementServiceImpl getAchievementService() {
        return achievementService;
    }

    public void setAchievementService(AchievementServiceImpl achievementService) {
        this.achievementService = achievementService;
    }

    public ProjectServiceImpl getProjectService() {
        return projectService;
    }

    public void setProjectService(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    public AuthenticationServiceImpl getAuthenticationService() {
        return authenticationService;
    }

    public void setAuthenticationService(AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }

    public ItemServiceImpl getItemService() {
        return itemService;
    }

    public void setItemService(ItemServiceImpl itemService) {
        this.itemService = itemService;
    }

    //private double getTotalScoreFrom


}
