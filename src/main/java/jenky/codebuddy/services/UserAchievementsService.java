package jenky.codebuddy.services;

import jenky.codebuddy.models.rest.Achievements;

/**
 * Created by Fabian on 12-6-2016.
 */
public interface UserAchievementsService {
    /**
     * @param token
     * @return String token
     */
    Achievements returnAchievements(String token);
}
