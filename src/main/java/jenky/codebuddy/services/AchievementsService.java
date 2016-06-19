package jenky.codebuddy.services;

import jenky.codebuddy.models.entities.Achievement;
import jenky.codebuddy.models.entities.AchievementUser;
import jenky.codebuddy.models.entities.User;
import jenky.codebuddy.models.rest.Achievements;
import jenky.codebuddy.models.rest.Response;
import org.hibernate.boot.model.relational.Database;

import java.util.List;

/**
 * Created by joost on 4-6-2016.
 */
public class AchievementsService {

    public AchievementsService() {
    }

    /**
     * @param token
     * @return Achievement
     */
    public Achievements returnAchievements(String token){
        User user = DatabaseFactory.getAuthenticationService().getAuthenticationIfTokenExists(token).getUser();
        List<Achievement> allAchievements = DatabaseFactory.getAchievementService().getAchievementsFromUser(user.getUser_id());
        return new Achievements(allAchievements, 200);
    }

    public Response tenCommitAchievement(String token) {
        int achievement_id = 1;
        int x = 10;
        return xCommitAchievement(token, achievement_id, x);
    }

    public Response hundredCommitAchievement(String token) {
        int achievement_id = 2;
        int x = 100;
        return xCommitAchievement(token, achievement_id, x);
    }

    public Response thousandCommitAchievement(String token) {
        int achievement_id = 3;
        int x = 1000;
        return xCommitAchievement(token, achievement_id, x);
    }

    public Response xCommitAchievement(String token, int achievement_id, int x) {
        User user = getUser(token);
        double amount = DatabaseFactory.getCommitService().getUserCommitCount(user.getUser_id());
        int progress = (int) Math.floor(amount / x);
        return attemptAchievement(user, achievement_id, progress);
    }

    public Response savingUpAchievement(String token) {
        int achievement_id = 4;
        int x = 1000;
        return xCoinsAchievement(token, achievement_id, x);
    }

    public Response moneyBankAchievement(String token) {
        int achievement_id = 5;
        int x = 1000;
        return xCoinsAchievement(token, achievement_id, x);
    }

    public Response richBoiAchievement(String token) {
        int achievement_id = 6;
        int x = 1000;
        return xCoinsAchievement(token, achievement_id, x);
    }

    private Response xCoinsAchievement(String token, int achievement_id, int x) {
        User user = getUser(token);
        double amount = DatabaseFactory.getUserService().getJenkyCoinsFromUser(user.getUser_id());
        int progress = (int) Math.floor(amount / x);
        return attemptAchievement(user, achievement_id, progress);
    }

    public Response yourFirstProjectAchievement(String token) {
        int achievement_id = 7;
        int x = 1;
        return xProjectsAchievement(token, achievement_id, x);
    }

    public Response busyBodyAchievement(String token) {
        int achievement_id = 8;
        int x = 5;
        return xProjectsAchievement(token, achievement_id, x);
    }

    public Response projectFreakProjectAchievement(String token) {
        int achievement_id = 9;
        int x = 10;
        return xProjectsAchievement(token, achievement_id, x);
    }

    private Response xProjectsAchievement(String token, int achievement_id, int x) {
        User user = getUser(token);
        double amount = DatabaseFactory.getProjectService().getProjectCountFromUser(user.getUser_id());
        int progress = (int) Math.floor(amount / x);
        return attemptAchievement(user, achievement_id, progress);
    }

    public Response highScoreAchievement(String token) {
        int achievement_id = 10;
        int x = 1000000;
        return xScoreAchievement(token, achievement_id, x);
    }

    private Response xScoreAchievement(String token, int achievement_id, int x) {
        User user = getUser(token);
        double amount = DatabaseFactory.getScoreService().getTotalScoreFromUser(user.getUser_id());
        int progress = (int) Math.floor(amount / x);
        return attemptAchievement(user, achievement_id, progress);
    }

    private Response attemptAchievement(User user, int achievement_id, int progress){
        if(checkIfAchievementHasAlreadyBeenGranted(user, achievement_id)) {
            if(checkIfAchievementHasBeenCompleted(user, achievement_id)) {
                return new Response(400, "Achievement has already been completed");
            }
            AchievementUser achievementUser = getAchievementUser(user, achievement_id);
            return setAchievement(user, achievementUser, achievement_id, progress);
        }
        return setAchievement(user, new AchievementUser(), achievement_id, progress);
    }

    private boolean checkIfAchievementHasAlreadyBeenGranted(User user, int achievement_id) {
        return DatabaseFactory.getAchievementUserService().checkIfAchievementHasBeenGranted(user, achievement_id);
    }

    private boolean checkIfAchievementHasBeenCompleted(User user, int achievement_id) {
        return DatabaseFactory.getAchievementUserService().checkIfAchievementHasBeenCompleted(user, achievement_id);
    }

    private Response setAchievement(User user, AchievementUser achievementUser, int achievement_id, double progress) {
        achievementUser.setUser(user);
        achievementUser.setProgress(progress);
        if(DatabaseFactory.getAchievementService().checkIfAchievementExists(achievement_id)){
            achievementUser.setAchievement(DatabaseFactory.getAchievementService().findById(achievement_id));
            DatabaseFactory.getAchievementUserService().saveOrUpdate(achievementUser);
            return new Response(200, "Achievement status updated");
        }
        return new Response(400, "Achievement doesn't exist");
    }

    private AchievementUser getAchievementUser(User user, int achievement_id) {
        return DatabaseFactory.getAchievementUserService().getSingleAchievementUser(user, achievement_id);
    }

    private User getUser(String token) {
        return DatabaseFactory.getAuthenticationService().getAuthenticationIfTokenExists(token).getUser();
    }
}