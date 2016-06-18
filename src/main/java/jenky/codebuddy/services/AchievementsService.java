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
        User user = DatabaseFactory.getAuthenticationService().getAuthenticationIfTokenExists(token).getUser();
        int achievement_id = 1;
        int progress = 100;
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

    private Response setAchievement(User user, AchievementUser achievementUser, int achievement_id, int progress) {
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
}