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

    public Response testMethod(String token){
        User user = DatabaseFactory.getAuthenticationService().getAuthenticationIfTokenExists(token).getUser();
        Achievement achievement = new Achievement();
        AchievementUser achievementUser = new AchievementUser();
        achievementUser.setUser(user);
        achievementUser.setAchievement(DatabaseFactory.getAchievementService().findById(1));
        return new Response(200, "In testMethod");
    }
}
